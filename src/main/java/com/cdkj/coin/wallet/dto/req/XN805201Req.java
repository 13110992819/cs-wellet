/**
 * @Title XN805201Req.java 
 * @Package com.std.user.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月23日 下午5:15:43 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月23日 下午5:15:43 
 * @history:
 */
public class XN805201Req {
    // 记录ID
    private String id;

    // 操作人
    private String updater;

    // 备注
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
