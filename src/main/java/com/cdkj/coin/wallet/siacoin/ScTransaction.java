package com.cdkj.coin.wallet.siacoin;

import java.math.BigInteger;

import com.cdkj.coin.wallet.dao.base.ABaseDO;

/**
* SC交易
* @author: haiqingzheng
* @since: 2018年01月30日 17:56:31
* @history:
*/
public class ScTransaction extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 交易哈希
    private String transactionid;

    // 确认区块高度
    private BigInteger confirmationheight;

    // 确认时间
    private BigInteger confirmationtimestamp;

    // 转出地址
    private String from;

    // 转入地址
    private String to;

    // 交易数量
    private String value;

    // 矿工费
    private String minerfee;

    // 关联订单号
    private String refNo;

    // **** 查询条件字段

    private String address;

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public BigInteger getConfirmationheight() {
        return confirmationheight;
    }

    public void setConfirmationheight(BigInteger confirmationheight) {
        this.confirmationheight = confirmationheight;
    }

    public BigInteger getConfirmationtimestamp() {
        return confirmationtimestamp;
    }

    public void setConfirmationtimestamp(BigInteger confirmationtimestamp) {
        this.confirmationtimestamp = confirmationtimestamp;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMinerfee() {
        return minerfee;
    }

    public void setMinerfee(String minerfee) {
        this.minerfee = minerfee;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
