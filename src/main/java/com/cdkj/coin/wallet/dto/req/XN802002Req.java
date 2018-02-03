/**
 * @Title XN802450Req.java 
 * @Package com.std.account.dto.req 
 * @Description 
 * @author xieyj  
 * @date 2016年12月23日 下午7:48:53 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月31日 下午5:43:44 
 * @history:
 */
public class XN802002Req {
    // 用户编号(必填)
    @NotBlank
    private String userId;

    // 币种(必填)
    @NotBlank
    private String currency;

    // 划转资金(必填)
    @NotBlank
    private String freezeAmount;

    // 业务类型(必填)
    @NotBlank
    private String bizType;

    // 业务说明(必填)
    @NotBlank
    private String bizNote;

    // 参考订单号(必填)
    @NotBlank
    private String refNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(String freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizNote() {
        return bizNote;
    }

    public void setBizNote(String bizNote) {
        this.bizNote = bizNote;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

}
