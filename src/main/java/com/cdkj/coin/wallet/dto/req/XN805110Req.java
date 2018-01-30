/**
 * @Title XN805080Req.java 
 * @Package com.std.user.dto.req 
 * @Description 
 * @author xieyj  
 * @date 2016年8月31日 下午12:02:51 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

/** 
 * @author: xieyj 
 * @since: 2016年8月31日 下午12:02:51 
 * @history:
 */
public class XN805110Req {
    // 用户编号(必填)
    private String userId;

    // 关注用户编号(必填)
    private String toUser;

    // 关系类型
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
