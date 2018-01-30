package com.cdkj.coin.wallet.domain;

import com.cdkj.coin.wallet.dao.base.ABaseDO;

/**
* 以太坊交易
* @author: haiqingzheng
* @since: 2017年10月29日 19:13:13
* @history:
*/
public class EthTransaction extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 交易hash
    private String hash;

    // 第几个交易
    private String nonce;

    // 区块hash
    private String blockHash;

    // 区块编号
    private String blockNumber;

    // transactionIndex
    private String transactionIndex;

    // 发送地址
    private String from;

    // 接受地址
    private String to;

    // 数量
    private String value;

    // gas价格
    private String gasPrice;

    // gas价格
    private String gasUsed;

    // 消耗gas
    private String gas;

    // input
    private String input;

    // 创建时间
    private String creates;

    // publicKey
    private String publicKey;

    // raw
    private String raw;

    // r
    private String r;

    // s
    private String s;

    private String refNo;

    // **** 查询条件字段

    private String address;

    private String createsStart;

    private String createsEnd;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(String transactionIndex) {
        this.transactionIndex = transactionIndex;
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

    public String getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getCreates() {
        return creates;
    }

    public void setCreates(String creates) {
        this.creates = creates;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
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

    public String getCreatesStart() {
        return createsStart;
    }

    public void setCreatesStart(String createsStart) {
        this.createsStart = createsStart;
    }

    public String getCreatesEnd() {
        return createsEnd;
    }

    public void setCreatesEnd(String createsEnd) {
        this.createsEnd = createsEnd;
    }

}
