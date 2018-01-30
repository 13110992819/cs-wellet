package com.cdkj.coin.wallet.dto.req;

public class XN805069Req {

    // userId（必填）
    private String userId;

    // 原支付密码（必填）
    private String oldTradePwd;

    // 新支付密码（必填）
    private String newTradePwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldTradePwd() {
        return oldTradePwd;
    }

    public void setOldTradePwd(String oldTradePwd) {
        this.oldTradePwd = oldTradePwd;
    }

    public String getNewTradePwd() {
        return newTradePwd;
    }

    public void setNewTradePwd(String newTradePwd) {
        this.newTradePwd = newTradePwd;
    }

}
