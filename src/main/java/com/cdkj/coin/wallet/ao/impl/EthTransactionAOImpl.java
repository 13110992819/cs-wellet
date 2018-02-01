/**
 * @Title EthTransactionAOImpl.java 
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

import com.cdkj.coin.wallet.ao.IEthTransactionAO;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.IChargeBO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IEthCollectionBO;
import com.cdkj.coin.wallet.bo.IEthTransactionBO;
import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.bo.IWithdrawBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.common.SysConstants;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.Charge;
import com.cdkj.coin.wallet.domain.Withdraw;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EChannelType;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.enums.EEthCollectionStatus;
import com.cdkj.coin.wallet.enums.EJourBizTypeCold;
import com.cdkj.coin.wallet.enums.EJourBizTypePlat;
import com.cdkj.coin.wallet.enums.EJourBizTypeUser;
import com.cdkj.coin.wallet.enums.EMAddressStatus;
import com.cdkj.coin.wallet.enums.ESystemAccount;
import com.cdkj.coin.wallet.enums.EWithdrawStatus;
import com.cdkj.coin.wallet.ethereum.CtqEthTransaction;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.ethereum.EthCollection;
import com.cdkj.coin.wallet.ethereum.EthTransaction;
import com.cdkj.coin.wallet.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午8:33:42 
 * @history:
 */
@Service
public class EthTransactionAOImpl implements IEthTransactionAO {

    private static final Logger logger = LoggerFactory
        .getLogger(EthTransactionAOImpl.class);

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private IEthCollectionBO ethCollectionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public String chargeNotice(CtqEthTransaction ctqEthTransaction) {
        EthAddress ethAddress = ethAddressBO.getEthAddress(EAddressType.X,
            ctqEthTransaction.getTo());
        if (ethAddress == null) {
            throw new BizException("xn6250000", "充值地址不存在");
        }
        Charge condition = new Charge();
        condition.setRefNo(ctqEthTransaction.getHash());
        if (chargeBO.getTotalCount(condition) > 0) {
            return "";
        }
        Account account = accountBO.getAccountByUser(ethAddress.getUserId(),
            ECoin.ETH.getCode());
        String payGroup = OrderNoGenerater.generate("PG");
        BigDecimal amount = new BigDecimal(ctqEthTransaction.getValue());
        // 充值订单落地
        String code = chargeBO.applyOrderOnline(account, payGroup,
            ctqEthTransaction.getHash(), EJourBizTypeUser.AJ_CHARGE.getCode(),
            "ETH充值-来自地址：" + ctqEthTransaction.getFrom(), amount,
            EChannelType.ETH, account.getUserId(), ctqEthTransaction.getFrom());
        // 账户加钱
        accountBO.changeAmount(account, amount, EChannelType.ETH,
            ctqEthTransaction.getHash(), payGroup, code,
            EJourBizTypeUser.AJ_CHARGE.getCode(), "ETH充值-来自地址："
                    + ctqEthTransaction.getFrom());
        // 落地交易记录
        ethTransactionBO.saveEthTransaction(ctqEthTransaction, code);
        // 更新地址余额
        ethAddressBO.refreshBalance(ethAddress);
        return code;
    }

    @Override
    @Transactional
    public void withdrawNotice(CtqEthTransaction ctqEthTransaction) {
        // 根据交易hash查询取现订单
        Withdraw withdraw = withdrawBO.getWithdraw(ctqEthTransaction.getHash());
        if (withdraw == null) {
            return;
        }
        // 计算矿工费
        BigDecimal gasPrice = new BigDecimal(ctqEthTransaction.getGasPrice());
        BigDecimal gasUse = new BigDecimal(ctqEthTransaction.getGas()
            .toString());
        BigDecimal txFee = gasPrice.multiply(gasUse);
        // 取现订单更新
        withdrawBO.payOrder(withdraw, EWithdrawStatus.Pay_YES,
            ctqEthTransaction.getFrom(), "广播成功", ctqEthTransaction.getHash(),
            ctqEthTransaction.getHash(), txFee);
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
            EChannelType.ETH,
            ctqEthTransaction.getHash(),
            "ETH",
            withdraw.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW.getValue() + "-外部地址："
                    + withdraw.getPayCardNo());
        if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
            // 取现手续费扣减
            userAccount = accountBO.changeAmount(userAccount, withdraw.getFee()
                .negate(), EChannelType.ETH, ctqEthTransaction.getHash(),
                "ETH", withdraw.getCode(), EJourBizTypeUser.AJ_WITHDRAWFEE
                    .getCode(), EJourBizTypeUser.AJ_WITHDRAWFEE.getValue());
        }

        // 平台盈亏账户记入取现手续费
        Account sysAccount = accountBO.getAccount(ESystemAccount.SYS_ACOUNT_ETH
            .getCode());
        if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
            sysAccount = accountBO.changeAmount(sysAccount, withdraw.getFee(),
                EChannelType.ETH, ctqEthTransaction.getHash(), "ETH",
                withdraw.getCode(), EJourBizTypePlat.AJ_WITHDRAWFEE.getCode(),
                EJourBizTypePlat.AJ_WITHDRAWFEE.getValue() + "-外部地址："
                        + withdraw.getPayCardNo());
        }
        // 平台盈亏账户记入取现矿工费
        sysAccount = accountBO.changeAmount(
            sysAccount,
            txFee.negate(),
            EChannelType.ETH,
            ctqEthTransaction.getHash(),
            "ETH",
            withdraw.getCode(),
            EJourBizTypePlat.AJ_WFEE.getCode(),
            EJourBizTypePlat.AJ_WFEE.getValue() + "-外部地址："
                    + withdraw.getPayCardNo());
        // 落地交易记录
        ethTransactionBO.saveEthTransaction(ctqEthTransaction,
            withdraw.getCode());

        // 更新地址余额
        EthAddress from = ethAddressBO.getEthAddress(EAddressType.M,
            ctqEthTransaction.getFrom());
        EthAddress to = ethAddressBO.getEthAddress(EAddressType.W,
            ctqEthTransaction.getTo());
        ethAddressBO.refreshBalance(from);
        ethAddressBO.refreshBalance(to);

        // 修改散取地址状态为可使用
        ethAddressBO.refreshStatus(from, EMAddressStatus.NORMAL.getCode());
    }

    @Override
    @Transactional
    public void collection(String address, String chargeCode) {
        // 获取地址信息
        EthAddress xEthAddress = ethAddressBO.getEthAddress(EAddressType.X,
            address);
        if (xEthAddress == null) {
            throw new BizException("xn625000", "该地址不能归集");
        }
        BigDecimal limit = sysConfigBO
            .getBigDecimalValue(SysConstants.COLLECTION_LIMIT_ETH);
        BigDecimal balance = ethAddressBO.getEthBalance(address);
        // 余额大于配置值时，进行归集
        if (balance.compareTo(Convert.toWei(limit, Unit.ETHER)) < 0) {
            throw new BizException("xn625000", "余额太少，无需归集");
        }
        // 获取今日归集地址
        EthAddress wEthAddress = ethAddressBO.getWEthAddressToday();
        String toAddress = wEthAddress.getAddress();
        // 预估矿工费用
        BigDecimal gasPrice = ethTransactionBO.getGasPrice();
        BigDecimal gasUse = new BigDecimal(21000);
        BigDecimal txFee = gasPrice.multiply(gasUse);
        BigDecimal value = balance.subtract(txFee);
        logger.info("地址余额=" + balance + "，以太坊平均价格=" + gasPrice + "，预计矿工费="
                + txFee + "，预计到账金额=" + value);
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException("xn625000", "余额不足以支付矿工费，不能归集");
        }
        // 归集广播
        EthAddress secret = ethAddressBO.getEthAddressSecret(xEthAddress
            .getCode());
        String txHash = ethTransactionBO.broadcast(address, secret, toAddress,
            value);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "归集—交易广播失败");
        }
        // 归集记录落地
        ethCollectionBO.saveEthCollection(address, toAddress, value, txHash,
            chargeCode);

    }

    @Override
    @Transactional
    public void collectionNotice(CtqEthTransaction ctqEthTransaction) {
        // 根据交易hash查询归集记录
        EthCollection collection = ethCollectionBO
            .getEthCollectionByTxHash(ctqEthTransaction.getHash());
        if (!EEthCollectionStatus.Broadcast.getCode().equals(
            collection.getStatus())) {
            throw new BizException("xn625000", "交易已处理，请勿重复处理");
        }
        // 归集订单状态更新
        BigDecimal gasPrice = new BigDecimal(ctqEthTransaction.getGasPrice());
        BigDecimal gasUse = new BigDecimal(ctqEthTransaction.getGas()
            .toString());
        BigDecimal txFee = gasPrice.multiply(gasUse);
        ethCollectionBO.colectionNotice(collection, txFee,
            ctqEthTransaction.getBlockCreateDatetime());
        // 平台冷钱包加钱
        Account coldAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_ETH_COLD.getCode());
        BigDecimal amount = new BigDecimal(ctqEthTransaction.getValue());
        accountBO.changeAmount(coldAccount, amount, EChannelType.ETH,
            ctqEthTransaction.getHash(), "ETH", collection.getCode(),
            EJourBizTypeCold.AJ_INCOME.getCode(),
            "归集-来自地址：" + collection.getFromAddress());
        // 平台盈亏账户记入矿工费
        Account sysAccount = accountBO.getAccount(ESystemAccount.SYS_ACOUNT_ETH
            .getCode());
        accountBO.changeAmount(sysAccount, txFee.negate(), EChannelType.ETH,
            ctqEthTransaction.getHash(), "ETH", collection.getCode(),
            EJourBizTypePlat.AJ_MFEE.getCode(),
            "归集地址：" + collection.getFromAddress());
        // 落地交易记录
        ethTransactionBO.saveEthTransaction(ctqEthTransaction,
            collection.getCode());
        // 更新地址余额
        EthAddress from = ethAddressBO.getEthAddress(EAddressType.X,
            collection.getFromAddress());
        EthAddress to = ethAddressBO.getEthAddress(EAddressType.W,
            collection.getToAddress());
        ethAddressBO.refreshBalance(from);
        ethAddressBO.refreshBalance(to);
    }

    @Override
    public Paginable<EthTransaction> queryEthTransactionPage(int start,
            int limit, EthTransaction condition) {
        return ethTransactionBO.getPaginable(start, limit, condition);
    }

    @Override
    public void depositNotice(CtqEthTransaction ctqEthTransaction) {
        // 平台冷钱包减钱
        BigDecimal amount = new BigDecimal(ctqEthTransaction.getValue());
        Account coldAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_ETH_COLD.getCode());
        coldAccount = accountBO.changeAmount(coldAccount, amount.negate(),
            EChannelType.ETH, ctqEthTransaction.getHash(), "ETH",
            ctqEthTransaction.getHash(), EJourBizTypeCold.AJ_PAY.getCode(),
            "ETH定存至取现地址(M):" + ctqEthTransaction.getTo());
        // 更新散取地址余额
        EthAddress to = ethAddressBO.getEthAddress(EAddressType.M,
            ctqEthTransaction.getTo());
        ethAddressBO.refreshBalance(to);

    }

}
