/**
 * @Title XN802900Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年12月7日 上午10:22:34 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年12月7日 上午10:22:34 
 * @history:
 */
public class XN802900Res {

    // 历史归集总额
    private String totolCollectCount;

    // 历史散取总额
    private String totolWithdrawCount;

    // 当前散取地址余额
    private String toWithdrawCount;

    // 客户未归集总额
    private String toCollectCount;

    // 平台所有币=冷钱包+客户未归集总额+当前散取地址余额
    private String totalCount;

    // 冷钱包余额
    private String coldCount;

    // 平台盈亏账户余额
    private String platCount;

    public String getTotolCollectCount() {
        return totolCollectCount;
    }

    public void setTotolCollectCount(String totolCollectCount) {
        this.totolCollectCount = totolCollectCount;
    }

    public String getTotolWithdrawCount() {
        return totolWithdrawCount;
    }

    public void setTotolWithdrawCount(String totolWithdrawCount) {
        this.totolWithdrawCount = totolWithdrawCount;
    }

    public String getToWithdrawCount() {
        return toWithdrawCount;
    }

    public void setToWithdrawCount(String toWithdrawCount) {
        this.toWithdrawCount = toWithdrawCount;
    }

    public String getToCollectCount() {
        return toCollectCount;
    }

    public void setToCollectCount(String toCollectCount) {
        this.toCollectCount = toCollectCount;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getColdCount() {
        return coldCount;
    }

    public void setColdCount(String coldCount) {
        this.coldCount = coldCount;
    }

    public String getPlatCount() {
        return platCount;
    }

    public void setPlatCount(String platCount) {
        this.platCount = platCount;
    }

}
