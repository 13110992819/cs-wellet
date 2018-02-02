/**
 * @Title ScTransactionAOImpl.java 
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

import com.cdkj.coin.wallet.ao.IScTransactionAO;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.IChargeBO;
import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.bo.IScAddressBO;
import com.cdkj.coin.wallet.bo.IScCollectionBO;
import com.cdkj.coin.wallet.bo.IScTransactionBO;
import com.cdkj.coin.wallet.bo.IWithdrawBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.common.DateUtil;
import com.cdkj.coin.wallet.common.SysConstants;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.Charge;
import com.cdkj.coin.wallet.domain.Withdraw;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EChannelType;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.enums.EJourBizTypeCold;
import com.cdkj.coin.wallet.enums.EJourBizTypePlat;
import com.cdkj.coin.wallet.enums.EJourBizTypeUser;
import com.cdkj.coin.wallet.enums.EScCollectionStatus;
import com.cdkj.coin.wallet.enums.ESystemAccount;
import com.cdkj.coin.wallet.enums.EWithdrawStatus;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.siacoin.CtqScTransaction;
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.siacoin.ScCollection;
import com.cdkj.coin.wallet.siacoin.ScTransaction;
import com.cdkj.coin.wallet.siacoin.SiadClient;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午8:33:42 
 * @history:
 */
@Service
public class ScTransactionAOImpl implements IScTransactionAO {

    private static final Logger logger = LoggerFactory
        .getLogger(ScTransactionAOImpl.class);

    @Autowired
    private IChargeBO chargeBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IScAddressBO scAddressBO;

    @Autowired
    private IScTransactionBO scTransactionBO;

    @Autowired
    private IScCollectionBO scCollectionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public String chargeNotice(CtqScTransaction ctqScTransaction) {
        ScAddress scAddress = scAddressBO.getScAddress(EAddressType.X,
            ctqScTransaction.getTo());
        if (scAddress == null) {
            throw new BizException("xn6250000", "充值地址不存在");
        }
        Charge condition = new Charge();
        condition.setRefNo(ctqScTransaction.getTransactionid());
        if (chargeBO.getTotalCount(condition) > 0) {
            return "";
        }
        Account account = accountBO.getAccountByUser(scAddress.getUserId(),
            ECoin.SC.getCode());
        String payGroup = OrderNoGenerater.generate("PG");
        BigDecimal amount = new BigDecimal(ctqScTransaction.getValue());
        // 充值订单落地
        String code = chargeBO.applyOrderOnline(account, payGroup,
            ctqScTransaction.getTransactionid(),
            EJourBizTypeUser.AJ_CHARGE.getCode(), "SC充值-来自地址："
                    + ctqScTransaction.getFrom(), amount, EChannelType.SC,
            account.getUserId(), ctqScTransaction.getFrom());
        // 账户加钱
        accountBO.changeAmount(account, amount, EChannelType.SC,
            ctqScTransaction.getTransactionid(), payGroup, code,
            EJourBizTypeUser.AJ_CHARGE.getCode(), "SC充值-来自地址："
                    + ctqScTransaction.getFrom());
        // 落地交易记录
        scTransactionBO.saveScTransaction(ctqScTransaction, code);
        return code;
    }

    @Override
    @Transactional
    public void withdrawNotice(CtqScTransaction ctqScTransaction) {
        // 根据交易hash查询取现订单
        Withdraw withdraw = withdrawBO.getWithdraw(ctqScTransaction
            .getTransactionid());
        if (withdraw == null) {
            return;
        }
        // 计算矿工费
        BigDecimal txFee = new BigDecimal(ctqScTransaction.getMinerfee());
        String txId = ctqScTransaction.getTransactionid();
        // 取现订单更新
        withdrawBO.payOrder(withdraw, EWithdrawStatus.Pay_YES,
            ctqScTransaction.getFrom(), "广播成功", txId, txId, txFee);
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
            EChannelType.SC,
            txId,
            "SC",
            withdraw.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW.getValue() + "-外部地址："
                    + withdraw.getPayCardNo());
        if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
            // 取现手续费扣减
            userAccount = accountBO.changeAmount(userAccount, withdraw.getFee()
                .negate(), EChannelType.SC, txId, "SC", withdraw.getCode(),
                EJourBizTypeUser.AJ_WITHDRAWFEE.getCode(),
                EJourBizTypeUser.AJ_WITHDRAWFEE.getValue());
        }

        // 平台盈亏账户记入取现手续费
        Account sysAccount = accountBO.getAccount(ESystemAccount.SYS_ACOUNT_ETH
            .getCode());
        if (withdraw.getFee().compareTo(BigDecimal.ZERO) > 0) {
            sysAccount = accountBO.changeAmount(sysAccount, withdraw.getFee(),
                EChannelType.SC, txId, "SC", withdraw.getCode(),
                EJourBizTypePlat.AJ_WITHDRAWFEE.getCode(),
                EJourBizTypePlat.AJ_WITHDRAWFEE.getValue() + "-外部地址："
                        + withdraw.getPayCardNo());
        }
        // 平台盈亏账户记入取现矿工费
        sysAccount = accountBO.changeAmount(
            sysAccount,
            txFee.negate(),
            EChannelType.SC,
            txId,
            "SC",
            withdraw.getCode(),
            EJourBizTypePlat.AJ_WFEE.getCode(),
            EJourBizTypePlat.AJ_WFEE.getValue() + "-外部地址："
                    + withdraw.getPayCardNo());
        // 落地交易记录
        scTransactionBO.saveScTransaction(ctqScTransaction, withdraw.getCode());

    }

    @Override
    @Transactional
    public void collection(String chargeCode) {
        // 获取钱包余额
        BigDecimal balance = SiadClient.getSiacoinBalance();
        BigDecimal limit = sysConfigBO
            .getBigDecimalValue(SysConstants.COLLECTION_LIMIT_SC);

        // 余额大于配置值时，进行归集
        if (balance.compareTo(SiadClient.toHasting(limit)) < 0) {
            logger.info("余额太少，无需归集");
            return;
        }
        // 获取今日归集地址
        ScAddress wScAddress = scAddressBO.getWScAddressToday();
        String toAddress = wScAddress.getAddress();

        // 默认矿工费用
        BigDecimal txFee = new BigDecimal("22500000000000000000000");
        BigDecimal value = balance.subtract(txFee);
        logger.info("地址余额=" + balance + "，预计矿工费=" + txFee + "，预计到账金额=" + value);
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            logger.info("余额不足以支付矿工费，不能归集");
            return;
        }
        // 归集广播
        String txHash = SiadClient.sendSingleAddress(toAddress, value);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "归集—交易广播失败");
        }
        // 归集记录落地
        scCollectionBO.saveScCollection(toAddress, value, txHash, chargeCode);

    }

    @Override
    @Transactional
    public void collectionNotice(CtqScTransaction ctqScTransaction) {
        // 根据交易hash查询归集记录
        ScCollection collection = scCollectionBO
            .getScCollectionByTxHash(ctqScTransaction.getTransactionid());
        if (!EScCollectionStatus.Broadcast.getCode().equals(
            collection.getStatus())) {
            throw new BizException("xn625000", "交易已处理，请勿重复处理");
        }
        // 归集订单状态更新
        BigDecimal txFee = new BigDecimal(ctqScTransaction.getMinerfee());
        scCollectionBO.colectionNotice(collection, ctqScTransaction.getFrom(),
            txFee, DateUtil.TimeStamp2Date(ctqScTransaction
                .getConfirmationtimestamp().toString(),
                DateUtil.DATA_TIME_PATTERN_1));
        // 平台冷钱包加钱
        Account coldAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_SC_COLD.getCode());
        BigDecimal amount = new BigDecimal(ctqScTransaction.getValue());
        accountBO.changeAmount(coldAccount, amount, EChannelType.SC,
            ctqScTransaction.getTransactionid(), "SC", collection.getCode(),
            EJourBizTypeCold.AJ_INCOME.getCode(),
            "归集-来自地址：" + collection.getFromAddress());
        // 平台盈亏账户记入矿工费
        Account sysAccount = accountBO.getAccount(ESystemAccount.SYS_ACOUNT_SC
            .getCode());
        accountBO.changeAmount(sysAccount, txFee.negate(), EChannelType.SC,
            ctqScTransaction.getTransactionid(), "SC", collection.getCode(),
            EJourBizTypePlat.AJ_MFEE.getCode(),
            "归集地址：" + collection.getFromAddress());
        // 落地交易记录
        scTransactionBO.saveScTransaction(ctqScTransaction,
            collection.getCode());
    }

    @Override
    public Paginable<ScTransaction> queryScTransactionPage(int start,
            int limit, ScTransaction condition) {
        return scTransactionBO.getPaginable(start, limit, condition);
    }

    @Override
    public void depositNotice(CtqScTransaction ctqScTransaction) {
        // 平台冷钱包减钱
        BigDecimal amount = new BigDecimal(ctqScTransaction.getValue());
        Account coldAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_SC_COLD.getCode());
        coldAccount = accountBO.changeAmount(coldAccount, amount.negate(),
            EChannelType.SC, ctqScTransaction.getTransactionid(),
            EChannelType.SC.getCode(), ctqScTransaction.getTransactionid(),
            EJourBizTypeCold.AJ_PAY.getCode(),
            "SC定存至钱包:" + ctqScTransaction.getTo());
    }

}
