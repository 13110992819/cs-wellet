/**
 * @Title XN802170Req.java 
 * @Package com.cdkj.coin.wallet.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年2月1日 下午8:09:43 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2018年2月1日 下午8:09:43 
 * @history:
 */
public class XN802176Req {

    // 币种
    private String currency;

    // 提现地址
    private String address;

    // 用户编号
    private String userId;

    // 状态
    private String status;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
