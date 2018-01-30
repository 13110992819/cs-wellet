package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN805073Req {

    // userId（必填）
    @NotBlank(message = "用户编号不能为空")
    private String userId;

    // 推荐人手机号（必填）
    @NotBlank(message = "推荐人手机号不能为空")
    private String userReferee;

    // 推荐人类型（必填）
    @NotBlank(message = "推荐人类型不能为空")
    private String userRefereeKind;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getUserRefereeKind() {
        return userRefereeKind;
    }

    public void setUserRefereeKind(String userRefereeKind) {
        this.userRefereeKind = userRefereeKind;
    }

}
