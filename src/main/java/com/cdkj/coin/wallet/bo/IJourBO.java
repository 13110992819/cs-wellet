package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.HLOrder;
import com.cdkj.coin.wallet.domain.Jour;
import com.cdkj.coin.wallet.enums.EBoolean;
import com.cdkj.coin.wallet.enums.EChannelType;

/**
 * @author: xieyj 
 * @since: 2016年12月23日 下午2:40:13 
 * @history:
 */
public interface IJourBO extends IPaginableBO<Jour> {
    // 不需要对账的新增
    public String addJourForHL(Account dbAccount, HLOrder order, String bizType);

    // 余额流水新增
    public String addJour(Account dbAccount, EChannelType channelType,
            String channelOrder, String payGroup, String refNo, String bizType,
            String bizNote, BigDecimal transAmount);

    // 冻结流水新增
    public String addFrozenJour(Account dbAccount, EChannelType channelType,
            String channelOrder, String payGroup, String refNo, String bizType,
            String bizNote, BigDecimal transAmount);

    // 对账结果录入
    public void doCheckJour(Jour jour, EBoolean checkResult,
            BigDecimal checkAmount, String checkUser, String checkNote);

    // 当前流水，调整不通过。即账其实是平的
    public void adjustJourNO(Jour jour, String adjustUser, String adjustNote);

    public void adjustJourYES(Jour jour, String adjustUser, String adjustNote);

    public List<Jour> queryJourList(Jour condition);

    public Jour getJour(String code, String systemCode);

    public Jour getJourNotException(String code, String systemCode);

    public BigDecimal getTotalAmount(String bizType, String channelType,
            String accountNumber, String dateStart, String dateEnd);

}
