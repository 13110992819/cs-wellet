package com.cdkj.coin.wallet.dto.req;

/** 
 * @author: xieyj 
 * @since: 2016年8月31日 下午12:04:08 
 * @history:
 */
public class XN805116Req {

    // 用户编号(必填)
    private String userId;

    // 关系人编号(必填)
    private String toUser;

    //关系类型
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

}
