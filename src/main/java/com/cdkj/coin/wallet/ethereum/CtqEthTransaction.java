package com.cdkj.coin.wallet.ethereum;

import java.math.BigInteger;
import java.util.Date;

import com.cdkj.coin.wallet.dao.base.ABaseDO;

/**
* 以太坊交易
* @author: haiqingzheng
* @since: 2017年10月29日 19:13:13
* @history:
*/
public class CtqEthTransaction extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 交易hash
    private String hash;

    // 第几个交易
    private BigInteger nonce;

    // 区块hash
    private String blockHash;

    // 区块编号
    private String blockNumber;

    // transactionIndex
    private BigInteger transactionIndex;

    // 发送地址
    private String from;

    // 接受地址
    private String to;

    // 数量
    private String value;

    // gas价格
    private String gasPrice;

    // gaslimit
    private BigInteger gas;

    // 消耗gas
    private BigInteger gasUsed;

    // 区块生成时间
    private Date blockCreateDatetime;

    // 区块生成时间
    private Date syncDatetime;

    private String status;

    // ################# 一下属性为查询而添加 ##############
    private Date blockCreateDatetimeStart;

    private Date blockCreateDatetimeEnd;

    public Date getBlockCreateDatetimeStart() {
        return blockCreateDatetimeStart;
    }

    public void setBlockCreateDatetimeStart(Date blockCreateDatetimeStart) {
        this.blockCreateDatetimeStart = blockCreateDatetimeStart;
    }

    public Date getBlockCreateDatetimeEnd() {
        return blockCreateDatetimeEnd;
    }

    public void setBlockCreateDatetimeEnd(Date blockCreateDatetimeEnd) {
        this.blockCreateDatetimeEnd = blockCreateDatetimeEnd;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getBlockCreateDatetime() {
        return blockCreateDatetime;
    }

    public void setBlockCreateDatetime(Date blockCreateDatetime) {
        this.blockCreateDatetime = blockCreateDatetime;
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

    // // input
    // private String input;
    //
    // // 创建时间
    // private String creates;
    //
    // // publicKey
    // private String publicKey;
    //
    // // raw
    // private String raw;
    //
    // // r
    // private String r;
    //
    // // s
    // private String s;

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public BigInteger getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(BigInteger transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public BigInteger getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(BigInteger gasUsed) {
        this.gasUsed = gasUsed;
    }

    public BigInteger getGas() {
        return gas;
    }

    public void setGas(BigInteger gas) {
        this.gas = gas;
    }
}
