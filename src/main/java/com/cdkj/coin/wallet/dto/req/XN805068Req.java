package com.cdkj.coin.wallet.dto.req;

public class XN805068Req {
    // userId（必填）
    private String userId;

    // 新支付密码（必填）
    private String newTradePwd;

    // 短信验证码（必填）
    private String smsCaptcha;

    // 证件类型（必填）
    private String idKind;

    // 证件号（必填）
    private String idNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewTradePwd() {
        return newTradePwd;
    }

    public void setNewTradePwd(String newTradePwd) {
        this.newTradePwd = newTradePwd;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

}
