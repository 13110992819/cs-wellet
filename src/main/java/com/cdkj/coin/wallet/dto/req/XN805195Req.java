package com.cdkj.coin.wallet.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年2月24日 下午5:55:42 
 * @history:
 */
public class XN805195Req {

    // userId(必填)
    private String userId;

    // 身份号(必填)
    private String idNo;

    // 真实姓名(必填)
    private String realName;

    // 回调地址(选填)
    private String returnUrl;

    // 是否做本地数据库校验
    private String localCheck;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getLocalCheck() {
        return localCheck;
    }

    public void setLocalCheck(String localCheck) {
        this.localCheck = localCheck;
    }
}
