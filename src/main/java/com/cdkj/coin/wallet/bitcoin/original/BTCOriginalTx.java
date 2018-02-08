package com.cdkj.coin.wallet.bitcoin.original;

import java.math.BigDecimal;
import java.util.List;

public class BTCOriginalTx {

    private String txid;

    private String version;

    private Long blockheight;

    private Boolean isCoinBase;

    private String blockhash;

    private Long confirmations;

    private Long blocktime;

    private BigDecimal valueIn;

    private BigDecimal valueOut;

    private BigDecimal fees;

    /* 输入列表 */
    private List<BTCVinUTXO> vin;

    /* 输出列表 */
    private List<BTCVoutUTXO> vout;

    public Boolean getCoinBase() {
        return isCoinBase;
    }

    public void setCoinBase(Boolean coinBase) {
        isCoinBase = coinBase;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Long getBlockheight() {
        return blockheight;
    }

    public void setBlockheight(Long blockheight) {
        this.blockheight = blockheight;
    }

    public List<BTCVinUTXO> getVin() {
        return vin;
    }

    public void setVin(List<BTCVinUTXO> vin) {
        this.vin = vin;
    }

    public List<BTCVoutUTXO> getVout() {
        return vout;
    }

    public void setVout(List<BTCVoutUTXO> vout) {
        this.vout = vout;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getIsCoinBase() {
        return isCoinBase;
    }

    public void setIsCoinBase(Boolean isCoinBase) {
        this.isCoinBase = isCoinBase;
    }

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public Long getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Long confirmations) {
        this.confirmations = confirmations;
    }

    public BigDecimal getValueIn() {
        return valueIn;
    }

    public void setValueIn(BigDecimal valueIn) {
        this.valueIn = valueIn;
    }

    public BigDecimal getValueOut() {
        return valueOut;
    }

    public void setValueOut(BigDecimal valueOut) {
        this.valueOut = valueOut;
    }

    public BigDecimal getFees() {
        return fees;
    }

    public void setFees(BigDecimal fees) {
        this.fees = fees;
    }

    public Long getBlocktime() {
        return blocktime;
    }

    public void setBlocktime(Long blocktime) {
        this.blocktime = blocktime;
    }
}
