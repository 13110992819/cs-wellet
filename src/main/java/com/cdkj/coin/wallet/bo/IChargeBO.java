package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.Charge;
import com.cdkj.coin.wallet.enums.EChannelType;

public interface IChargeBO extends IPaginableBO<Charge> {
    String applyOrderOnline(Account account, String payGroup, String refNo,
            String bizType, String bizNote, BigDecimal transAmount,
            EChannelType channelType, String applyUser, String fromAddress);

    void callBackChange(Charge dbCharge, boolean booleanFlag);

    String applyOrderOffline(Account account, String bizType,
            BigDecimal amount, String payCardInfo, String payCardNo,
            String applyUser, String applyNote);

    void payOrder(Charge data, boolean booleanFlag, String payUser,
            String payNote);

    List<Charge> queryChargeList(Charge condition);

    Charge getCharge(String code, String systemCode);

    public boolean isExistOfRefNo(String refNo);

}
