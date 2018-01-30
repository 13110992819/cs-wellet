package com.cdkj.coin.wallet.dto.req;

public class XN805081Req {

    // userId（必填）
    private String userId;

    // 邮箱（必填）
    private String email;

    // 验证码（必填）
    private String captcha;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
