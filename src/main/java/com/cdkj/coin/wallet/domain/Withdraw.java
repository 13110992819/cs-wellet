package com.cdkj.coin.wallet.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.coin.wallet.dao.base.ABaseDO;

//取现（3步骤）
public class Withdraw extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -6551310796135984342L;

    // 订单编号
    private String code;

    // 针对账号
    private String accountNumber;

    // 针对户名（手机号或其他）
    private String accountName;

    // 币种
    private String currency;

    // 账户类型
    private String type;

    // 取现金额
    private transient BigDecimal amount;

    private String amountString;

    // 取现手续费
    private transient BigDecimal fee;

    private String feeString;

    // 支付渠道
    private String channelType;

    // 渠道银行代号
    private String channelBank;

    // 支付渠道的订单编号（支付渠道代表）
    private String channelOrder;

    // 支付渠道账号信息（如开户支行）
    private String payCardInfo;

    // 支付渠道账号（如银行卡号）
    private String payCardNo;

    // 状态（待审核/审核不通过/审核通过待支付/支付成功/支付失败）
    private String status;

    // 申请人
    private String applyUser;

    // 申请说明
    private String applyNote;

    // 申请时间
    private Date applyDatetime;

    // 审核人
    private String approveUser;

    // 审核说明
    private String approveNote;

    // 审核时间
    private Date approveDatetime;

    // 支付回录人
    private String payUser;

    // 支付回录说明
    private String payNote;

    // 支付组号（信息流代表）
    private String payGroup;

    private String payCode;

    // 支付手续费
    private transient BigDecimal payFee;

    private String payFeeString;

    // 支付回录时间
    private Date payDatetime;

    // 系统编号
    private String systemCode;

    // 公司编号
    private String companyCode;

    // *******************************
    // 订单编号模糊查询
    private String codeForQuery;

    // 申请时间起
    private Date applyDatetimeStart;

    // 申请时间止
    private Date applyDatetimeEnd;

    // 审批时间起
    private Date approveDatetimeStart;

    // 审批时间止
    private Date approveDatetimeEnd;

    // 支付时间起
    private Date payDatetimeStart;

    // 支付时间止
    private Date payDatetimeEnd;

    // 申请用户
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getApplyDatetimeStart() {
        return applyDatetimeStart;
    }

    public void setApplyDatetimeStart(Date applyDatetimeStart) {
        this.applyDatetimeStart = applyDatetimeStart;
    }

    public Date getApplyDatetimeEnd() {
        return applyDatetimeEnd;
    }

    public void setApplyDatetimeEnd(Date applyDatetimeEnd) {
        this.applyDatetimeEnd = applyDatetimeEnd;
    }

    public Date getApproveDatetimeStart() {
        return approveDatetimeStart;
    }

    public void setApproveDatetimeStart(Date approveDatetimeStart) {
        this.approveDatetimeStart = approveDatetimeStart;
    }

    public Date getApproveDatetimeEnd() {
        return approveDatetimeEnd;
    }

    public void setApproveDatetimeEnd(Date approveDatetimeEnd) {
        this.approveDatetimeEnd = approveDatetimeEnd;
    }

    public Date getPayDatetimeStart() {
        return payDatetimeStart;
    }

    public void setPayDatetimeStart(Date payDatetimeStart) {
        this.payDatetimeStart = payDatetimeStart;
    }

    public Date getPayDatetimeEnd() {
        return payDatetimeEnd;
    }

    public void setPayDatetimeEnd(Date payDatetimeEnd) {
        this.payDatetimeEnd = payDatetimeEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
        this.amountString = amount.toString();
    }

    public String getAmountString() {
        return amountString;
    }

    public void setAmountString(String amountString) {
        this.amountString = amountString;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
        this.feeString = fee.toString();
    }

    public String getFeeString() {
        return feeString;
    }

    public void setFeeString(String feeString) {
        this.feeString = feeString;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelBank() {
        return channelBank;
    }

    public void setChannelBank(String channelBank) {
        this.channelBank = channelBank;
    }

    public String getPayCardInfo() {
        return payCardInfo;
    }

    public void setPayCardInfo(String payCardInfo) {
        this.payCardInfo = payCardInfo;
    }

    public String getPayCardNo() {
        return payCardNo;
    }

    public void setPayCardNo(String payCardNo) {
        this.payCardNo = payCardNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPayNote() {
        return payNote;
    }

    public void setPayNote(String payNote) {
        this.payNote = payNote;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getChannelOrder() {
        return channelOrder;
    }

    public void setChannelOrder(String channelOrder) {
        this.channelOrder = channelOrder;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public BigDecimal getPayFee() {
        return payFee;
    }

    public void setPayFee(BigDecimal payFee) {
        this.payFee = payFee;
        this.payFeeString = payFee.toString();
    }

    public String getPayFeeString() {
        return payFeeString;
    }

    public void setPayFeeString(String payFeeString) {
        this.payFeeString = payFeeString;
    }

    public String getCodeForQuery() {
        return codeForQuery;
    }

    public void setCodeForQuery(String codeForQuery) {
        this.codeForQuery = codeForQuery;
    }
}
