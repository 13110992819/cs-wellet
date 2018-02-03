package com.cdkj.coin.wallet.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.Account;

public interface IAccountAO {
    String DEFAULT_ORDER_COLUMN = "account_number";

    // 个人创建多账户
    public void distributeAccount(String userId, String realName,
            List<String> currencyList, String systemCode, String companyCode);

    // 更新户名
    public void editAccountName(String userId, String realName);

    // 分页查询账户
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition);

    // 根据accountNumber查询账户
    public Account getAccount(String accountNumber);

    // 根据用户编号,币种获取账户列表
    public List<Account> getAccountByUserId(String userId, String currency);

    // 列表查询账户
    public List<Account> queryAccountList(Account condition);

    // 不同用户不同币种间资金划转
    public void transAmount(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, BigDecimal transAmount,
            String fromBizType, String toBizType, String fromBizNote,
            String toBizNote, String refNo);

    // 冻结金额
    public Account frozenAmount(String userId, String currency,
            BigDecimal freezeAmount, String bizType, String bizNote,
            String refNo);

    // 解冻(冻结金额原路返回)
    public Account unfrozenAmount(String userId, String currency,
            BigDecimal freezeAmount, String bizType, String bizNote,
            String refNo);

}
