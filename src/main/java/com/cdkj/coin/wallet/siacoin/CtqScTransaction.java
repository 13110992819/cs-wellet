package com.cdkj.coin.wallet.siacoin;

import java.math.BigInteger;
import java.util.Date;

import com.cdkj.coin.wallet.dao.base.ABaseDO;

/**
* SC交易
* @author: haiqingzheng
* @since: 2018年01月30日 17:56:31
* @history:
*/
public class CtqScTransaction extends ABaseDO {

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

    // 同步时间
    private Date syncDatetime;

    // 状态 0-未推送 1-已推送
    private String status;

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

    public Date getSyncDatetime() {
        return syncDatetime;
    }

    public void setSyncDatetime(Date syncDatetime) {
        this.syncDatetime = syncDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
