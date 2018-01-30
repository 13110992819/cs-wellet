/**
 * @Title XN805920Req.java 
 * @Package com.std.user.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月22日 下午2:36:47 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

/** 
 * 将用户拉入黑名单
 * @author: haiqingzheng 
 * @since: 2017年2月22日 下午2:36:47 
 * @history:
 */
public class XN805200Req {

    // 用户编号（必填）
    private String userId;

    // 拉黑类型（必填)
    private String type;

    // 操作人（必填)
    private String updater;

    // 备注（选填）
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
