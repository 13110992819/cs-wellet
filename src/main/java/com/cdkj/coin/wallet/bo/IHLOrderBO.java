package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.HLOrder;
import com.cdkj.coin.wallet.domain.Jour;
import com.cdkj.coin.wallet.enums.EHLOrderStatus;

public interface IHLOrderBO extends IPaginableBO<HLOrder> {

    String applyOrder(Account account, Jour jour, BigDecimal applyAmount,
            String applyUser, String applyNote);

    void approveOrder(HLOrder order, EHLOrderStatus status, String approveUser,
            String approveNote);

    HLOrder getHLOrder(String code, String systemCode);

    List<HLOrder> queryHLOrderList(HLOrder condition);

}
