package com.cdkj.coin.wallet.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.coin.wallet.ao.IAccountAO;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IJourBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.dto.res.XN802503Res;
import com.cdkj.coin.wallet.enums.EAccountType;
import com.cdkj.coin.wallet.enums.EChannelType;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.enums.EEthAddressType;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;

@Service
public class AccountAOImpl implements IAccountAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    protected ICtqBO ctqBO;

    @Override
    @Transactional
    public void distributeAccount(String userId, String realName,
            String accountType, List<String> currencyList, String systemCode,
            String companyCode) {
        if (CollectionUtils.isNotEmpty(currencyList)) {
            Map<String, EAccountType> map = EAccountType
                .getAccountTypeResultMap();
            EAccountType eAccountType = map.get(accountType);
            if (null == eAccountType) {
                new BizException("XN0000", "账户类型不存在");
            }
            for (String currency : currencyList) {
                String accountId = accountBO.distributeAccount(userId,
                    realName, eAccountType, currency, systemCode, companyCode);
                generateAddress(userId, accountId, currency);
            }
        }
    }

    private String generateAddress(String userId, String accountId,
            String currency) {

        String address = null;

        if (ECoin.ETH.getCode().equals(currency)) {
            address = ethAddressBO.generateAddress(EEthAddressType.X, userId);
            ctqBO.uploadAddress(address, EEthAddressType.X.getCode());
        } else if (ECoin.BTC.getCode().equals(currency)) {

        } else if (ECoin.SC.getCode().equals(currency)) {
            // address = scAddressBO.generateAddress(EEthAddressType.X, userId);
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "不支持的币种"
                    + currency);
        }

        return address;
    }

    @Override
    public void editAccountName(String userId, String realName) {
        // 验证用户名和系统编号
        Account condition = new Account();
        condition.setUserId(userId);
        List<Account> accountList = accountBO.queryAccountList(condition);
        if (CollectionUtils.isEmpty(accountList)) {
            new BizException("XN0000", "该用户无对应账号");
        }
        accountBO.refreshAccountName(userId, realName);
    }

    @Override
    @Transactional
    public void transAmountCZB(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, BigDecimal transAmount,
            String fromBizType, String toBizType, String fromBizNote,
            String toBizNote, String refNo) {
        accountBO.transAmountCZB(fromUserId, fromCurrency, toUserId,
            toCurrency, transAmount, fromBizType, toBizType, fromBizNote,
            toBizNote, refNo);
    }

    @Override
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition) {
        return accountBO.getPaginable(start, limit, condition);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountBO.getAccount(accountNumber);
    }

    @Override
    public XN802503Res getAccountByUserId(String userId, String currency) {
        XN802503Res res = new XN802503Res();

        // 总资产
        BigDecimal totalAmountCNY = BigDecimal.ZERO;
        BigDecimal totalAmountUSD = BigDecimal.ZERO;
        BigDecimal totalAmountHKD = BigDecimal.ZERO;

        Account condition = new Account();
        condition.setUserId(userId);
        condition.setCurrency(currency);
        List<Account> accountList = accountBO.queryAccountList(condition);

        return res;
    }

    @Override
    public List<Account> queryAccountList(Account condition) {
        return accountBO.queryAccountList(condition);
    }

    @Override
    public void changeAmount(String accountNumber, String channelType,
            String channelOrder, String payGroup, String refNo, String bizType,
            String bizNote, BigDecimal transAmount) {
        Account account = accountBO.getAccount(accountNumber);
        accountBO.changeAmount(account, transAmount,
            EChannelType.getEChannelType(channelType), channelOrder, payGroup,
            refNo, bizType, bizNote);
    }
}
