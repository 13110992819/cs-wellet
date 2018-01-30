package com.cdkj.coin.wallet.dto.req;

public class XN805093Req {

    // 用户编号（必填）
    private String userId;

    // 分成比例1（必填）
    private String divRate1;

    // 分成比例2（必填）
    private String divRate2;

    // 更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDivRate1() {
        return divRate1;
    }

    public void setDivRate1(String divRate1) {
        this.divRate1 = divRate1;
    }

    public String getDivRate2() {
        return divRate2;
    }

    public void setDivRate2(String divRate2) {
        this.divRate2 = divRate2;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
