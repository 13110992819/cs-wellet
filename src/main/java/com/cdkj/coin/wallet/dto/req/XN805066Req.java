package com.cdkj.coin.wallet.dto.req;

public class XN805066Req {
    // userId（必填）
    private String userId;

    // 支付密码（必填）
    private String tradePwd;

    // 手机验证码（必填）
    private String smsCaptcha;

    // 谷歌验证码（选填）
    private String googleCaptcha;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
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
