package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN805071Req {

    @NotBlank
    private String userId; // userId（必填）

    @NotBlank
    private String secret;// 谷歌秘钥（必填）

    @NotBlank
    private String smsCaptcha;// 手机验证码（必填）

    @NotBlank
    private String googleCaptcha;// 谷歌验证码（必填）

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    public String getGoogleCaptcha() {
        return googleCaptcha;
    }

    public void setGoogleCaptcha(String googleCaptcha) {
        this.googleCaptcha = googleCaptcha;
    }

}
