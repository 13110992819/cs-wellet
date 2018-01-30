/**
 * @Title XN625200Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:48:07 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:48:07 
 * @history:
 */
public class XN625203Req {

    @NotBlank
    private String address; // 以太坊地址

    @NotBlank
    private String label; // 标签

    @NotBlank
    private String userId; // 用户编号

    @NotBlank
    private String smsCaptcha; // 手机验证码

    @NotBlank
    private String isCerti; // 是否设置为认证账户

    private String tradePwd; // 资金密码，isCerti为true时必填

    private String googleCaptcha; // isCerti为true，开启谷歌认证时必填

    public String getGoogleCaptcha() {
        return googleCaptcha;
    }

    public void setGoogleCaptcha(String googleCaptcha) {
        this.googleCaptcha = googleCaptcha;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    public String getIsCerti() {
        return isCerti;
    }

    public void setIsCerti(String isCerti) {
        this.isCerti = isCerti;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

}
