package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.HLOrder;
import com.cdkj.coin.wallet.enums.EAccountStatus;
import com.cdkj.coin.wallet.enums.EAccountType;
import com.cdkj.coin.wallet.enums.EChannelType;
import com.cdkj.coin.wallet.enums.ECurrency;

/**
 * @author: xieyj
 * @since: 2016年11月11日 上午11:23:06 
 * @history:
 */
public interface IAccountBO extends IPaginableBO<Account> {

    // 分配账户
    public String distributeAccount(String userId, String realName,
            EAccountType accountType, String currency, String systemCode,
            String companyCode);

    // 变更账户余额：流水落地
    public Account changeAmount(Account dbAccount, BigDecimal transAmount,
            EChannelType channelType, String channelOrder, String payGroup,
            String refNo, String bizType, String bizNote);

    // 仅变更账户余额：流水不落地
    public void changeAmountNotJour(String accountNumber,
            BigDecimal transAmount, String lastOrder);

    // 红冲蓝补导致的资金变动（落地流水不需要对账）
    public void changeAmountForHL(HLOrder order);

    // 冻结金额（余额变动）
    public Account frozenAmount(Account dbAccount, BigDecimal freezeAmount,
            String bizType, String bizNote, String refNo);

    // 解冻账户(冻结金额原路返回)
    public Account unfrozenAmount(Account dbAccount, BigDecimal freezeAmount,
            String bizType, String bizNote, String refNo);

    // 内部转账
    public void transAmountCZB(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, BigDecimal transAmount,
            String fromBizType, String toBizType, String fromBizNote,
            String toBizNote, String refNo);

    // 更新户名
    public void refreshAccountName(String userId, String realName);

    // 更新账户状态
    public void refreshStatus(String accountNumber, EAccountStatus status);

    // 获取账户
    public Account getAccount(String accountNumber);

    // 通过用户编号和币种获取币种
    public Account getAccountByUser(String userId, String currency);

    // 根据系统编号,公司编号和币种获取对应的系统账户(账户类型确定为系统账户)
    public Account getSysAccountNumber(String systemCode, String companyCode,
            ECurrency currency);

    // 获取账户列表
    public List<Account> queryAccountList(Account data);
}
