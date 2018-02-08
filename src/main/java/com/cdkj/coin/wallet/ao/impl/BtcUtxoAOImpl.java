/**
 * @Title BtcUtxoAOImpl.java 
 * @Package com.cdkj.coin.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月7日 下午8:33:42 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.coin.wallet.ao.IBtcUtxoAO;
import com.cdkj.coin.wallet.bitcoin.BitcoinOfflineRawTxBuilder;
import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.bitcoin.BtcUtxo;
import com.cdkj.coin.wallet.bitcoin.CtqBtcUtxo;
import com.cdkj.coin.wallet.bitcoin.OfflineTxInput;
import com.cdkj.coin.wallet.bitcoin.OfflineTxOutput;
import com.cdkj.coin.wallet.bitcoin.original.BTCOriginalTx;
import com.cdkj.coin.wallet.bitcoin.util.BtcBlockExplorer;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.IBtcAddressBO;
import com.cdkj.coin.wallet.bo.IBtcUtxoBO;
import com.cdkj.coin.wallet.bo.IChargeBO;
import com.cdkj.coin.wallet.bo.ICollectionBO;
import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.bo.IWithdrawBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.common.AmountUtil;
import com.cdkj.coin.wallet.common.DateUtil;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.common.SysConstants;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.Collection;
import com.cdkj.coin.wallet.domain.Withdraw;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EBtcUtxoRefType;
import com.cdkj.coin.wallet.enums.EBtcUtxoStatus;
import com.cdkj.coin.wallet.enums.EChannelType;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.enums.ECollectionStatus;
import com.cdkj.coin.wallet.enums.EJourBizTypeCold;
import com.cdkj.coin.wallet.enums.EJourBizTypePlat;
import com.cdkj.coin.wallet.enums.EJourBizTypeUser;
import com.cdkj.coin.wallet.enums.ESystemAccount;
import com.cdkj.coin.wallet.enums.EWithdrawStatus;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午8:33:42 
 * @history:
 */
@Service
public class BtcUtxoAOImpl implements IBtcUtxoAO {

    private static final Logger logger = LoggerFactory
        .getLogger(BtcUtxoAOImpl.class);

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IBtcAddressBO btcAddressBO;

    @Autowired
    private IBtcUtxoBO btcUtxoBO;

    @Autowired
    private ICollectionBO collectionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private BtcBlockExplorer btcBlockExplorer;

    @Override
    @Transactional
    public String chargeNotice(CtqBtcUtxo ctqBtcUtxo) {

        BtcAddress btcAddress = btcAddressBO.getBtcAddress(EAddressType.X,
            ctqBtcUtxo.getAddress());
        if (btcAddress == null) {
            throw new BizException("xn6250000", "BTC充值地址不存在");
        }

        // 判断是否已经处理过该交易
        if (chargeBO.isExistOfRefNo(ctqBtcUtxo.getRefNo())) {
            return "";
        }

        Account account = accountBO.getAccountByUser(btcAddress.getUserId(),
            ECoin.BTC.getCode());
        String payGroup = OrderNoGenerater.generate("PG");
        BigDecimal amount = ctqBtcUtxo.getCount();

        // 充值订单落地
        String code = chargeBO.applyOrderOnline(account, payGroup,
            ctqBtcUtxo.getRefNo(), EJourBizTypeUser.AJ_CHARGE.getCode(),
            EChannelType.BTC.getCode() + "充值-来自交易：" + ctqBtcUtxo.getRefNo(),
            amount, EChannelType.BTC, account.getUserId(),
            ctqBtcUtxo.getRefNo());

        // 落地UTXO
        btcUtxoBO.saveBtcUtxo(ctqBtcUtxo, EAddressType.X);

        // 账户加钱
        accountBO.changeAmount(account, amount, EChannelType.BTC,
            ctqBtcUtxo.getRefNo(), payGroup, code,
            EJourBizTypeUser.AJ_CHARGE.getCode(), EChannelType.BTC.getCode()
                    + "充值-来自交易：" + ctqBtcUtxo.getRefNo());

        return code;
    }

    @Override
    @Transactional
    public void withdrawNotice(CtqBtcUtxo ctqBtcUtxo) {

        // 取到UTXO
        BtcUtxo btcUtxo = btcUtxoBO.getBtcUtxo(ctqBtcUtxo.getTxid(),
            ctqBtcUtxo.getVout());

        // 判断是否是正在取现广播中的UTXO
        if (EBtcUtxoStatus.USING.getCode().equals(btcUtxo.getStatus())
                && EBtcUtxoRefType.WITHDRAW.getCode().equals(
                    btcUtxo.getRefType())
                && StringUtils.isNotBlank(btcUtxo.getRefNo())) {

            // 修改UTXO状态
            btcUtxoBO.refreshStatus(btcUtxo, EBtcUtxoStatus.USED);

            // 查询取现订单
            Withdraw withdraw = withdrawBO.getWithdraw(btcUtxo.getRefNo());
            if (withdraw == null) {
                return;
            }
            if (!EWithdrawStatus.Broadcast.getCode().equals(
                withdraw.getStatus())) {
                return;
            }

            // 查询交易详情
            BTCOriginalTx btcOriginalTx = btcBlockExplorer.queryTxHash(withdraw
                .getChannelOrder());
            if (btcOriginalTx == null) {
                return;
            }

            // 取现订单更新
            withdrawBO.payOrder(withdraw, EWithdrawStatus.Pay_YES,
                withdraw.getPayUser(), "广播成功", withdraw.getChannelOrder(),
                withdraw.getChannelOrder(), withdraw.getPayFee());

            Account userAccount = accountBO.getAccount(withdraw
                .getAccountNumber());
            // 取现金额解冻
            userAccount = accountBO.unfrozenAmount(userAccount,
                withdraw.getAmount(),
                EJourBizTypeUser.AJ_WITHDRAW_UNFROZEN.getCode(),
                EJourBizTypeUser.AJ_WITHDRAW_UNFROZEN.getValue(),
                withdraw.getCode());
            // 取现金额扣减
            userAccount = accountBO.changeAmount(
                userAccount,
                withdraw.getAmount().subtract(withdraw.getFee()).negate(),
                EChannelType.BTC,
                withdraw.getChannelOrder(),
                "BTC",
                withdraw.getCode(),
                EJourBizTypeUser.AJ_WITHDRAW.getCode(),
                EJourBizTypeUser.AJ_WITHDRAW.getValue() + "-外部地址："
                        + withdraw.getPayCardNo());
            if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
                // 取现手续费扣减
                userAccount = accountBO.changeAmount(userAccount, withdraw
                    .getFee().negate(), EChannelType.BTC, withdraw
                    .getChannelOrder(), "BTC", withdraw.getCode(),
                    EJourBizTypeUser.AJ_WITHDRAWFEE.getCode(),
                    EJourBizTypeUser.AJ_WITHDRAWFEE.getValue());
            }

            // 平台盈亏账户记入取现手续费
            Account sysAccount = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_BTC.getCode());
            if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
                sysAccount = accountBO.changeAmount(sysAccount,
                    withdraw.getFee(), EChannelType.BTC,
                    withdraw.getChannelOrder(), EChannelType.BTC.getCode(),
                    withdraw.getCode(),
                    EJourBizTypePlat.AJ_WITHDRAWFEE.getCode(),
                    EJourBizTypePlat.AJ_WITHDRAWFEE.getValue() + "-外部地址："
                            + withdraw.getPayCardNo());
            }
            // 平台盈亏账户记入取现矿工费
            sysAccount = accountBO.changeAmount(
                sysAccount,
                btcOriginalTx.getFees().negate(),
                EChannelType.BTC,
                withdraw.getChannelOrder(),
                EChannelType.BTC.getCode(),
                withdraw.getCode(),
                EJourBizTypePlat.AJ_WFEE.getCode(),
                EJourBizTypePlat.AJ_WFEE.getValue() + "-外部地址："
                        + withdraw.getPayCardNo());

        }

    }

    @Override
    @Transactional
    public void collection(String chargeCode) {

        // 归集阀值，UTXO大于这个值进行归集
        BigDecimal limit = sysConfigBO
            .getBigDecimalValue(SysConstants.COLLECTION_LIMIT_BTC);
        limit = AmountUtil.toBtc(limit);

        // 获取分发地址的UTXO总额，判断是否满足归集条件
        BigDecimal enableCount = btcUtxoBO
            .getTotalEnableUTXOCount(EAddressType.X);
        if (enableCount.compareTo(limit) < 0) {
            throw new BizException("xn0000", "充值订单" + chargeCode
                    + "触发归集，但UTXO总量未达到归集阀值，无需归集");
        }

        // 获取今日归集地址
        String toAddress = btcAddressBO.getWBtcAddressToday().getAddress();

        // 降序遍历可使用的M类地址UTXO，组装Input
        BitcoinOfflineRawTxBuilder rawTxBuilder = new BitcoinOfflineRawTxBuilder();
        BigDecimal shouldCollectCount = BigDecimal.ZERO;
        List<BtcUtxo> inputBtcUtxoList = new ArrayList<BtcUtxo>();
        int pageNum = 0;
        while (true) {
            BtcUtxo condition = new BtcUtxo();
            condition.setAddressType(EAddressType.X.getCode());
            condition.setStatus(EBtcUtxoStatus.ENABLE.getCode());
            condition.setOrder("count", "decs");
            Paginable<BtcUtxo> pageBtcUtxo = btcUtxoBO.getPaginable(pageNum,
                20, condition);// 默认每次20条
            List<BtcUtxo> list = pageBtcUtxo.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (BtcUtxo utxo : list) {
                    String txid = utxo.getTxid();
                    Integer vout = utxo.getVout();
                    // 应取现总额
                    shouldCollectCount = shouldCollectCount
                        .add(utxo.getCount());
                    BtcAddress btcAddress = btcAddressBO.getBtcAddress(
                        EAddressType.X, utxo.getAddress());
                    // 构造签名交易，输入
                    OfflineTxInput offlineTxInput = new OfflineTxInput(txid,
                        vout, utxo.getScriptPubKey(),
                        btcAddress.getPrivatekey());
                    rawTxBuilder.in(offlineTxInput);
                    inputBtcUtxoList.add(utxo);
                }
            } else {
                break;
            }
            pageNum++;// 不够再遍历
        }
        // 组装Output，设置找零账户
        // 如何估算手续费，先预先给一个size,然后拿这个size进行签名
        // 对签名的数据进行解码，拿到真实大小，然后进行矿工费的修正
        int preSize = BitcoinOfflineRawTxBuilder.calculateSize(rawTxBuilder
            .getSize().intValue(), 1);
        int feePerByte = btcBlockExplorer.getFee();
        // 计算出手续费
        int preFee = preSize * feePerByte;

        // 构造输出，归集无需找零，只要算出矿工费，其余到转到归集地址
        BigDecimal realAmount = shouldCollectCount.subtract(BigDecimal
            .valueOf(preFee));
        OfflineTxOutput offlineTxOutput = new OfflineTxOutput(toAddress,
            AmountUtil.fromBtc(realAmount));
        rawTxBuilder.out(offlineTxOutput);

        logger.info("OTXO总额=" + shouldCollectCount + "，比特币平均费率=" + feePerByte
                + "，预计矿工费=" + preFee + "，预计到账金额=" + realAmount);

        // 归集广播
        try {
            String signResult = rawTxBuilder.offlineSign();
            // 广播
            String trueTxid = btcBlockExplorer.broadcastRawTx(signResult);
            if (trueTxid != null) {

                // 归集记录落地
                String collectionCode = collectionBO.saveCollection(ECoin.BTC,
                    JsonUtil.Object2Json(inputBtcUtxoList), toAddress,
                    realAmount, trueTxid, chargeCode);
                if (CollectionUtils.isNotEmpty(inputBtcUtxoList)) {
                    for (BtcUtxo data : inputBtcUtxoList) {
                        btcUtxoBO.refreshBroadcast(data, EBtcUtxoStatus.USING,
                            EBtcUtxoRefType.COLLECTION, collectionCode);
                    }
                }

            } else {
                throw new BizException(EBizErrorCode.UTXO_COLLECTION_ERROR);
            }
        } catch (Exception e) {
            throw new BizException("-100", e.getMessage());
        }

    }

    @Override
    @Transactional
    public void collectionNotice(CtqBtcUtxo ctqBtcUtxo) {

        // 取到UTXO
        BtcUtxo btcUtxo = btcUtxoBO.getBtcUtxo(ctqBtcUtxo.getTxid(),
            ctqBtcUtxo.getVout());

        // 判断是否是正在归集广播中的UTXO
        if (EBtcUtxoStatus.USING.getCode().equals(btcUtxo.getStatus())
                && EBtcUtxoRefType.COLLECTION.getCode().equals(
                    btcUtxo.getRefType())
                && StringUtils.isNotBlank(btcUtxo.getRefNo())) {

            // 修改UTXO状态
            btcUtxoBO.refreshStatus(btcUtxo, EBtcUtxoStatus.USED);

            // 根据交易hash查询归集记录
            Collection collection = collectionBO.getCollection(btcUtxo
                .getRefNo());

            if (!ECollectionStatus.Broadcast.getCode().equals(
                collection.getStatus())) {
                throw new BizException("xn625000", "交易已处理，请勿重复处理");
            }

            // 查询交易详情
            BTCOriginalTx btcOriginalTx = btcBlockExplorer
                .queryTxHash(collection.getTxHash());
            if (btcOriginalTx == null) {
                return;
            }

            // 归集订单状态更新
            collectionBO.colectionNoticeBTC(collection,
                btcOriginalTx.getFees(), DateUtil.TimeStamp2Date(btcOriginalTx
                    .getBlocktime().toString(), DateUtil.DATA_TIME_PATTERN_1));

            // 平台冷钱包加钱
            Account coldAccount = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_BTC_COLD.getCode());
            BigDecimal amount = collection.getAmount();
            accountBO.changeAmount(coldAccount, amount, EChannelType.BTC,
                btcOriginalTx.getTxid(), EChannelType.BTC.getCode(),
                collection.getCode(), EJourBizTypeCold.AJ_INCOME.getCode(),
                "归集-交易ID：" + btcOriginalTx.getTxid());

            // 平台盈亏账户记入矿工费
            Account sysAccount = accountBO
                .getAccount(ESystemAccount.SYS_ACOUNT_BTC.getCode());
            accountBO.changeAmount(sysAccount,
                btcOriginalTx.getFees().negate(), EChannelType.BTC,
                btcOriginalTx.getTxid(), EChannelType.BTC.getCode(),
                collection.getCode(), EJourBizTypePlat.AJ_MFEE.getCode(),
                "归集-交易ID：" + btcOriginalTx.getTxid());

        }

    }

    @Override
    public Paginable<BtcUtxo> queryBtcUtxoPage(int start, int limit,
            BtcUtxo condition) {
        return btcUtxoBO.getPaginable(start, limit, condition);
    }

    @Override
    @Transactional
    public void depositNotice(CtqBtcUtxo ctqBtcUtxo) {
        // 平台冷钱包减钱
        BigDecimal amount = ctqBtcUtxo.getCount();
        Account coldAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_BTC_COLD.getCode());
        coldAccount = accountBO.changeAmount(coldAccount, amount.negate(),
            EChannelType.BTC, ctqBtcUtxo.getRefNo(),
            EChannelType.BTC.getCode(), ctqBtcUtxo.getRefNo(),
            EJourBizTypeCold.AJ_PAY.getCode(), EChannelType.BTC.getCode()
                    + "定存至取现地址(M):" + ctqBtcUtxo.getAddress());
        // 落地UTXO
        btcUtxoBO.saveBtcUtxo(ctqBtcUtxo, EAddressType.M);
    }

}
