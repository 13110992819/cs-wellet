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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import com.cdkj.coin.wallet.ao.IBtcUtxoAO;
import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.bitcoin.BtcUtxo;
import com.cdkj.coin.wallet.bitcoin.CtqBtcUtxo;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.IBtcAddressBO;
import com.cdkj.coin.wallet.bo.IBtcUtxoBO;
import com.cdkj.coin.wallet.bo.IChargeBO;
import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.bo.IWithdrawBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.common.SysConstants;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.Withdraw;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EChannelType;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.enums.EJourBizTypeCold;
import com.cdkj.coin.wallet.enums.EJourBizTypePlat;
import com.cdkj.coin.wallet.enums.EJourBizTypeUser;
import com.cdkj.coin.wallet.enums.EMAddressStatus;
import com.cdkj.coin.wallet.enums.ESystemAccount;
import com.cdkj.coin.wallet.enums.EWithdrawStatus;
import com.cdkj.coin.wallet.exception.BizException;

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

    // @Autowired
    // private IBtcCollectionBO btcCollectionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

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
        // 根据交易hash查询取现订单
        Withdraw withdraw = withdrawBO.getWithdraw(ctqBtcUtxo.getHash());
        if (withdraw == null) {
            return;
        }
        // 计算矿工费
        BigDecimal gasPrice = new BigDecimal(ctqBtcUtxo.getGasPrice());
        BigDecimal gasUse = new BigDecimal(ctqBtcUtxo.getGas().toString());
        BigDecimal txFee = gasPrice.multiply(gasUse);
        // 取现订单更新
        withdrawBO.payOrder(withdraw, EWithdrawStatus.Pay_YES,
            ctqBtcUtxo.getFrom(), "广播成功", ctqBtcUtxo.getHash(),
            ctqBtcUtxo.getHash(), txFee);
        Account userAccount = accountBO.getAccount(withdraw.getAccountNumber());
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
            ctqBtcUtxo.getHash(),
            "BTC",
            withdraw.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW.getValue() + "-外部地址："
                    + withdraw.getPayCardNo());
        if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
            // 取现手续费扣减
            userAccount = accountBO.changeAmount(userAccount, withdraw.getFee()
                .negate(), EChannelType.BTC, ctqBtcUtxo.getHash(), "BTC",
                withdraw.getCode(), EJourBizTypeUser.AJ_WITHDRAWFEE.getCode(),
                EJourBizTypeUser.AJ_WITHDRAWFEE.getValue());
        }

        // 平台盈亏账户记入取现手续费
        Account sysAccount = accountBO.getAccount(ESystemAccount.SYS_ACOUNT_BTC
            .getCode());
        if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
            sysAccount = accountBO.changeAmount(sysAccount, withdraw.getFee(),
                EChannelType.BTC, ctqBtcUtxo.getHash(), "BTC",
                withdraw.getCode(), EJourBizTypePlat.AJ_WITHDRAWFEE.getCode(),
                EJourBizTypePlat.AJ_WITHDRAWFEE.getValue() + "-外部地址："
                        + withdraw.getPayCardNo());
        }
        // 平台盈亏账户记入取现矿工费
        sysAccount = accountBO.changeAmount(
            sysAccount,
            txFee.negate(),
            EChannelType.BTC,
            ctqBtcUtxo.getHash(),
            "BTC",
            withdraw.getCode(),
            EJourBizTypePlat.AJ_WFEE.getCode(),
            EJourBizTypePlat.AJ_WFEE.getValue() + "-外部地址："
                    + withdraw.getPayCardNo());
        // 落地交易记录
        btcTransactionBO.saveBtcUtxo(ctqBtcUtxo, withdraw.getCode());

        // 更新地址余额
        BtcAddress from = btcAddressBO.getBtcAddress(EAddressType.M,
            ctqBtcUtxo.getFrom());
        BtcAddress to = btcAddressBO.getBtcAddress(EAddressType.W,
            ctqBtcUtxo.getTo());
        btcAddressBO.refreshBalance(from);
        btcAddressBO.refreshBalance(to);

        // 修改散取地址状态为可使用
        btcAddressBO.refreshStatus(from, EMAddressStatus.NORMAL.getCode());
    }

    @Override
    @Transactional
    public void collection(String chargeCode) {

        BigDecimal limit = sysConfigBO
            .getBigDecimalValue(SysConstants.COLLECTION_LIMIT_BTC);
        BigDecimal balance = btcAddressBO.getBtcBalance(address);
        // 余额大于配置值时，进行归集
        if (balance.compareTo(Convert.toWei(limit, Unit.BTCER)) < 0) {
            throw new BizException("xn625000", "余额太少，无需归集");
        }
        // 获取今日归集地址
        BtcAddress wBtcAddress = btcAddressBO.getWBtcAddressToday();
        String toAddress = wBtcAddress.getAddress();
        // 预估矿工费用
        BigDecimal gasPrice = btcTransactionBO.getGasPrice();
        BigDecimal gasUse = new BigDecimal(21000);
        BigDecimal txFee = gasPrice.multiply(gasUse);
        BigDecimal value = balance.subtract(txFee);
        logger.info("地址余额=" + balance + "，以太坊平均价格=" + gasPrice + "，预计矿工费="
                + txFee + "，预计到账金额=" + value);
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException("xn625000", "余额不足以支付矿工费，不能归集");
        }
        // 归集广播
        BtcAddress secret = btcAddressBO.getBtcAddressSecret(xBtcAddress
            .getCode());
        String txHash = btcTransactionBO.broadcast(address, secret, toAddress,
            value);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "归集—交易广播失败");
        }
        // 归集记录落地
        btcCollectionBO.saveBtcCollection(address, toAddress, value, txHash,
            chargeCode);

    }

    @Override
    @Transactional
    public void collectionNotice(CtqBtcUtxo ctqBtcUtxo) {
        // 根据交易hash查询归集记录
        BtcCollection collection = btcCollectionBO
            .getBtcCollectionByTxHash(ctqBtcUtxo.getHash());
        if (!EBtcCollectionStatus.Broadcast.getCode().equals(
            collection.getStatus())) {
            throw new BizException("xn625000", "交易已处理，请勿重复处理");
        }
        // 归集订单状态更新
        BigDecimal gasPrice = new BigDecimal(ctqBtcUtxo.getGasPrice());
        BigDecimal gasUse = new BigDecimal(ctqBtcUtxo.getGas().toString());
        BigDecimal txFee = gasPrice.multiply(gasUse);
        btcCollectionBO.colectionNotice(collection, txFee,
            ctqBtcUtxo.getBlockCreateDatetime());
        // 平台冷钱包加钱
        Account coldAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_BTC_COLD.getCode());
        BigDecimal amount = new BigDecimal(ctqBtcUtxo.getValue());
        accountBO.changeAmount(coldAccount, amount, EChannelType.BTC,
            ctqBtcUtxo.getHash(), "BTC", collection.getCode(),
            EJourBizTypeCold.AJ_INCOME.getCode(),
            "归集-来自地址：" + collection.getFromAddress());
        // 平台盈亏账户记入矿工费
        Account sysAccount = accountBO.getAccount(ESystemAccount.SYS_ACOUNT_BTC
            .getCode());
        accountBO.changeAmount(sysAccount, txFee.negate(), EChannelType.BTC,
            ctqBtcUtxo.getHash(), "BTC", collection.getCode(),
            EJourBizTypePlat.AJ_MFEE.getCode(),
            "归集地址：" + collection.getFromAddress());
        // 落地交易记录
        btcTransactionBO.saveBtcUtxo(ctqBtcUtxo, collection.getCode());
        // 更新地址余额
        BtcAddress from = btcAddressBO.getBtcAddress(EAddressType.X,
            collection.getFromAddress());
        BtcAddress to = btcAddressBO.getBtcAddress(EAddressType.W,
            collection.getToAddress());
        btcAddressBO.refreshBalance(from);
        btcAddressBO.refreshBalance(to);
    }

    @Override
    public Paginable<BtcUtxo> queryBtcUtxoPage(int start, int limit,
            BtcUtxo condition) {
        return btcTransactionBO.getPaginable(start, limit, condition);
    }

    @Override
    public void depositNotice(CtqBtcUtxo ctqBtcUtxo) {
        // 平台冷钱包减钱
        BigDecimal amount = new BigDecimal(ctqBtcUtxo.getValue());
        Account coldAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_BTC_COLD.getCode());
        coldAccount = accountBO.changeAmount(coldAccount, amount.negate(),
            EChannelType.BTC, ctqBtcUtxo.getHash(), "BTC",
            ctqBtcUtxo.getHash(), EJourBizTypeCold.AJ_PAY.getCode(),
            "BTC定存至取现地址(M):" + ctqBtcUtxo.getTo());
        // 更新散取地址余额
        BtcAddress to = btcAddressBO.getBtcAddress(EAddressType.M,
            ctqBtcUtxo.getTo());
        btcAddressBO.refreshBalance(to);

    }

}
