package com.cdkj.coin.wallet.dto.req;

public class XN805082Req {

    // userId（必填）
    private String userId;

    // 备注（必填）
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
