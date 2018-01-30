/**
 * @Title XN805090Req.java
 * @Package com.std.user.dto.req
 * @Description
 * @author xieyj
 * @date 2016年8月31日 下午12:04:08
 * @version V1.0
 */
package com.cdkj.coin.wallet.dto.req;

/**
 * @author: xieyj
 * @since: 2016年8月31日 下午12:04:08
 * @history:
 */
public class XN805115Req extends APageReq {

    private static final long serialVersionUID = 1L;

    // 用户编号(选填)
    private String userId;

    // 关系人编号(选填)
    private String toUser;

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
