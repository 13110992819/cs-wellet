package com.cdkj.coin.wallet.dto.req;

public class XN805091Req {

    // 用户编号(必填)
    private String userId;

    // 更新人(必填)
    private String updater;

    // 备注(选填)
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
