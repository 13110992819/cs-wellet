package com.cdkj.coin.wallet.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.coin.wallet.ao.IAccountAO;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IJourBO;
import com.cdkj.coin.wallet.bo.IScAddressBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.enums.EAccountType;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.siacoin.ScAddress;

@Service
public class AccountAOImpl implements IAccountAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IScAddressBO scAddressBO;

    @Autowired
    protected ICtqBO ctqBO;

    @Override
    @Transactional
    public void distributeAccount(String userId, String realName,
            List<String> currencyList, String systemCode, String companyCode) {
        if (CollectionUtils.isNotEmpty(currencyList)) {
            for (String currency : currencyList) {
                String accountId = accountBO.distributeAccount(userId,
                    realName, EAccountType.Customer, currency, systemCode,
                    companyCode);
                generateAddress(userId, accountId, currency);
            }
        }
    }

    private String generateAddress(String userId, String accountId,
            String currency) {

        String address = null;

        if (ECoin.ETH.getCode().equals(currency)) {
            address = ethAddressBO.generateAddress(EAddressType.X, userId,
                accountId);
            ctqBO.uploadEthAddress(address, EAddressType.X.getCode());
        } else if (ECoin.BTC.getCode().equals(currency)) {
            // todo
            // 生成比特币地址
            // 上传比特币地址至橙提取
        } else if (ECoin.SC.getCode().equals(currency)) {
            try {
                address = scAddressBO.generateAddress(EAddressType.X, userId,
                    accountId, "system", "注册时自动分配");
            } catch (Exception e) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "SC地址生成发生异常，原因：" + e.getMessage());
            }

            ctqBO.uploadScAddress(address, EAddressType.X.getCode());
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
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition) {
        return accountBO.getPaginable(start, limit, condition);
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountBO.getAccount(accountNumber);
    }

    @Override
    @Transactional
    public List<Account> getAccountByUserId(String userId, String currency) {
        // 如果没有SC账户，则创建一个账户
        if (!accountBO.isAccountExist(userId, ECoin.SC.getCode())) {
            Account account = accountBO.getAccountByUser(userId,
                ECoin.ETH.getCode());
            List<String> currencyList = new ArrayList<String>();
            currencyList.add(ECoin.SC.getCode());
            distributeAccount(userId, account.getRealName(), currencyList,
                account.getSystemCode(), account.getCompanyCode());
        }
        Account condition = new Account();
        condition.setUserId(userId);
        condition.setCurrency(currency);
        List<Account> accountList = accountBO.queryAccountList(condition);
        for (Account account : accountList) {
            if (ECoin.ETH.getCode().equals(account.getCurrency())) {
                // X地址获取
                EthAddress ethAddress = ethAddressBO
                    .getEthAddressByAccountNumber(account.getAccountNumber());
                account.setCoinAddress(ethAddress.getAddress());
            } else if (ECoin.SC.getCode().equals(account.getCurrency())) {
                ScAddress scAddress = scAddressBO
                    .getScAddressByAccountNumber(account.getAccountNumber());
                account.setCoinAddress(scAddress.getAddress());
            }
        }

        return accountList;
    }

    @Override
    public List<Account> queryAccountList(Account condition) {
        return accountBO.queryAccountList(condition);
    }

    @Override
    @Transactional
    public void transAmount(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, BigDecimal transAmount,
            String fromBizType, String toBizType, String fromBizNote,
            String toBizNote, String refNo) {
        accountBO.transAmount(fromUserId, fromCurrency, toUserId, toCurrency,
            transAmount, fromBizType, toBizType, fromBizNote, toBizNote, refNo);
    }

    @Override
    public Account frozenAmount(String userId, String currency,
            BigDecimal freezeAmount, String bizType, String bizNote,
            String refNo) {
        Account dbAccount = accountBO.getAccountByUser(userId, currency);
        return accountBO.frozenAmount(dbAccount, freezeAmount, bizType,
            bizNote, refNo);
    }

    @Override
    public Account unfrozenAmount(String userId, String currency,
            BigDecimal unfreezeAmount, String bizType, String bizNote,
            String refNo) {
        Account dbAccount = accountBO.getAccountByUser(userId, currency);
        return accountBO.unfrozenAmount(dbAccount, unfreezeAmount, bizType,
            bizNote, refNo);
    }
}
