/**
 * @Title XN802170Req.java 
 * @Package com.cdkj.coin.wallet.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年2月1日 下午8:09:43 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2018年2月1日 下午8:09:43 
 * @history:
 */
public class XN802170Req {

    // 币种
    @NotBlank
    private String currency;

    // 提现地址
    @NotBlank
    private String address;

    // 标签
    @NotBlank
    private String label;

    // 用户编号
    @NotBlank
    private String userId;

    @NotBlank
    private String isCerti;

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

    public String getIsCerti() {
        return isCerti;
    }

    public void setIsCerti(String isCerti) {
        this.isCerti = isCerti;
    }

}
