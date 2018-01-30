package com.cdkj.coin.wallet.dto.res;

public class XNUserRes {
    private String userId;

    public XNUserRes() {
    }

    public XNUserRes(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
