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
public class XN802001Req {
    // 来方用户编号(必填)
    @NotBlank
    private String fromUserId;

    // 来方币种(必填)
    @NotBlank
    private String fromCurrency;

    // 接收方用户编号(必填)
    @NotBlank
    private String toUserId;

    // 去方币种(必填)
    @NotBlank
    private String toCurrency;

    // 划转资金(必填)
    @NotBlank
    private String transAmount;

    // 来方业务类型(必填)
    @NotBlank
    private String fromBizType;

    // 去方业务类型(必填)
    @NotBlank
    private String toBizType;

    // 来方业务说明(必填)
    @NotBlank
    private String fromBizNote;

    // 去方业务说明(必填)
    @NotBlank
    private String toBizNote;

    // 参考订单号(必填)
    @NotBlank
    private String refNo;

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getFromBizType() {
        return fromBizType;
    }

    public void setFromBizType(String fromBizType) {
        this.fromBizType = fromBizType;
    }

    public String getToBizType() {
        return toBizType;
    }

    public void setToBizType(String toBizType) {
        this.toBizType = toBizType;
    }

    public String getFromBizNote() {
        return fromBizNote;
    }

    public void setFromBizNote(String fromBizNote) {
        this.fromBizNote = fromBizNote;
    }

    public String getToBizNote() {
        return toBizNote;
    }

    public void setToBizNote(String toBizNote) {
        this.toBizNote = toBizNote;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
}
