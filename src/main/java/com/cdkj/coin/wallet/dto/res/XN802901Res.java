package com.cdkj.coin.wallet.dto.res;

import java.math.BigDecimal;

/** 
 * @author: haiqingzheng 
 * @since: 2017年5月18日 下午7:28:09 
 * @history:
 */
public class XN802901Res {
    // 收入金额
    private BigDecimal incomeAmount;

    // 取现金额
    private BigDecimal withdrawAmount;

    public XN802901Res(BigDecimal incomeAmount, BigDecimal withdrawAmount) {
        super();
        this.incomeAmount = incomeAmount;
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
}
