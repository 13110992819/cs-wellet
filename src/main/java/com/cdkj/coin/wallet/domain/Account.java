package com.cdkj.coin.wallet.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.coin.wallet.dao.base.ABaseDO;

/**
 * 账户
 * @author: xieyj 
 * @since: 2016年12月23日 上午10:41:45 
 * @history:
 */
public class Account extends ABaseDO {

    private static final long serialVersionUID = 8322573358554172531L;

    // 账户编号
    private String accountNumber;

    // 用户编号
    private String userId;

    // 户名
    private String realName;

    // 类别（C端账号/B端账号/P平台账号）
    private String type;

    // 状态（0正常/1程序冻结/2人工冻结）
    private String status;

    // 币种
    private String currency;

    // 余额
    private BigDecimal amount;

    private String amountString;

    // 冻结金额
    private transient BigDecimal frozenAmount;

    private String frozenAmountString;

    // md5
    private String md5;

    // 累计增加金额
    private transient BigDecimal addAmount;

    private String addAmountString;

    // 入金
    private transient BigDecimal inAmount;

    private String inAmountString;

    // 出金
    private transient BigDecimal outAmount;

    private String outAmountString;

    // 创建时间
    private Date createDatetime;

    // 最近一次变动对应的流水编号
    private String lastOrder;

    // 系统编号
    private String systemCode;

    // 公司编号
    private String companyCode;

    // **************************db properties **************************

    // 创建起始时间
    private Date createDatetimeStart;

    // 创建终止时间
    private Date createDatetimeEnd;

    // 区块链地址
    private String coinAddress;

    public String getCoinAddress() {
        return coinAddress;
    }

    public void setCoinAddress(String coinAddress) {
        this.coinAddress = coinAddress;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
        this.amountString = amount.toString();
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
        this.frozenAmountString = frozenAmount.toString();
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public BigDecimal getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(BigDecimal addAmount) {
        this.addAmount = addAmount;
        this.addAmountString = addAmount.toString();
    }

    public BigDecimal getInAmount() {
        return inAmount;
    }

    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
        this.inAmountString = inAmount.toString();
    }

    public BigDecimal getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
        this.outAmountString = outAmount.toString();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(String lastOrder) {
        this.lastOrder = lastOrder;
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

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getAmountString() {
        return amountString;
    }

    public void setAmountString(String amountString) {
        this.amountString = amountString;
    }

    public String getFrozenAmountString() {
        return frozenAmountString;
    }

    public void setFrozenAmountString(String frozenAmountString) {
        this.frozenAmountString = frozenAmountString;
    }

    public String getAddAmountString() {
        return addAmountString;
    }

    public void setAddAmountString(String addAmountString) {
        this.addAmountString = addAmountString;
    }

    public String getInAmountString() {
        return inAmountString;
    }

    public void setInAmountString(String inAmountString) {
        this.inAmountString = inAmountString;
    }

    public String getOutAmountString() {
        return outAmountString;
    }

    public void setOutAmountString(String outAmountString) {
        this.outAmountString = outAmountString;
    }

}
