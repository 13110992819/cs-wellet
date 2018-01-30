package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.Jour;
import com.cdkj.coin.wallet.enums.EBoolean;

/**
 * @author: xieyj 
 * @since: 2016年12月23日 下午2:40:13 
 * @history:
 */
public interface IJourHistoryBO extends IPaginableBO<Jour> {

    // 对账结果录入
    public void doCheckJour(Jour jour, EBoolean checkResult,
            BigDecimal checkAmount, String checkUser, String checkNote);

    // 当前流水，调整不通过。即账其实是平的
    public void adjustJourNO(Jour jour, String adjustUser, String adjustNote);

    public void adjustJourYES(Jour jour, String adjustUser, String adjustNote);

    public List<Jour> queryJourList(Jour condition);

    public Jour getJour(String code, String systemCode);

    public Long getTotalAmount(String bizType, String channelType,
            String accountNumber);
}
