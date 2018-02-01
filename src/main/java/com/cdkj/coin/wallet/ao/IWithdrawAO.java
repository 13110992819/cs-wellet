package com.cdkj.coin.wallet.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.Withdraw;
import com.cdkj.coin.wallet.dto.res.XN802758Res;
import com.cdkj.coin.wallet.spring.ServiceModule;

@ServiceModule
public interface IWithdrawAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 待申请
    public String applyOrder(String accountNumber, BigDecimal amount,
            BigDecimal fee, String payCardInfo, String payCardNo,
            String applyUser, String applyNote);

    // 取现审核
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote, String systemCode);

    // 取现广播
    public void broadcast(String code, String mAddressCode, String approveUser);

    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder, String systemCode);

    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition);

    public List<Withdraw> queryWithdrawList(Withdraw condition);

    public Withdraw getWithdraw(String code, String systemCode);

    public XN802758Res getWithdrawCheckInfo(String code);

    public BigDecimal getTotalWithdraw();
}
