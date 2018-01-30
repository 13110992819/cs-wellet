package com.cdkj.coin.wallet.dto.req;

/**
 * @author: xieyj 
 * @since: 2016年10月12日 上午6:38:45 
 * @history:
 */
public class XN805065Req {

    // 用户编号(必填)
    private String userId;

    // 登录密码(必填)
    private String loginPwd;

    // admin密码(必填)
    private String adminUserId;

    // admin密码(必填)
    private String adminPwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }
}
