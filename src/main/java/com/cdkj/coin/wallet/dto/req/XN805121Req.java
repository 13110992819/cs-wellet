package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN805121Req {
    // userId（必填）
    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
