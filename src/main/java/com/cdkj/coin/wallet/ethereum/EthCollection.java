package com.cdkj.coin.wallet.ethereum;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.coin.wallet.dao.base.ABaseDO;

/**
* 归集记录
* @author: haiqingzheng
* @since: 2017年11月09日 14:26:36
* @history:
*/
public class EthCollection extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 被归集地址
    private String fromAddress;

    // 归集地址
    private String toAddress;

    // 归集数量
    private transient BigDecimal amount;

    private String amountString;

    // 交易hash
    private String txHash;

    // 矿工费
    private transient BigDecimal txFee;

    private String txFeeString;

    // 状态
    private String status;

    // 发起时间
    private Date createDatetime;

    // 网络记账时间
    private Date ethDatetime;

    // 完成时间
    private Date updateDatetime;

    // 关联订单号
    private String refNo;

    // 订单编号模糊查询
    private String codeForQuery;

    public String getTxFeeString() {
        return txFeeString;
    }

    public void setTxFeeString(String txFeeString) {
        this.txFeeString = txFeeString;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
        this.amountString = amount.toString();
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public BigDecimal getTxFee() {
        return txFee;
    }

    public void setTxFee(BigDecimal txFee) {
        this.txFee = txFee;
        this.txFeeString = txFee.toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getEthDatetime() {
        return ethDatetime;
    }

    public void setEthDatetime(Date ethDatetime) {
        this.ethDatetime = ethDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getAmountString() {
        return amountString;
    }

    public void setAmountString(String amountString) {
        this.amountString = amountString;
    }

    public String getCodeForQuery() {
        return codeForQuery;
    }

    public void setCodeForQuery(String codeForQuery) {
        this.codeForQuery = codeForQuery;
    }

}
