package com.cdkj.coin.wallet.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.Charge;
import com.cdkj.coin.wallet.dto.res.XN802707Res;

public interface IChargeAO {

    String DEFAULT_ORDER_COLUMN = "code";

    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote);

    public void payOrder(String code, String payUser, String payResult,
            String payNote, String systemCode);

    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition);

    public List<Charge> queryChargeList(Charge condition);

    public Charge getCharge(String code, String systemCode);

    // 充币对账详情
    public XN802707Res getChargeCheckInfo(String code);

}
