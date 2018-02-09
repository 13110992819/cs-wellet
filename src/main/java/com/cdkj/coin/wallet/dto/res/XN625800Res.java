/**
 * @Title XN625800Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年1月18日 上午9:59:59 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.res;

import java.math.BigInteger;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月18日 上午9:59:59 
 * @history:
 */
public class XN625800Res {
    // infura最新区块高度
    private BigInteger infuraBlockNumber;

    // infura最新gas价格
    private BigInteger infuraGasPrice;

    // bcoin最新区块高度
    private BigInteger bcoinBlockNumber;

    // bcoin最新gas价格
    private BigInteger bcoinGasPrice;

    // bcoin已扫描至区块高度
    private BigInteger bcoinScaned;

    // SC钱包状态
    private boolean scWalletOpened;

    // SC钱包余额
    private String scWalletBalance;

    // SC区块高度
    private BigInteger scBlockNumber;

    // SC扫描高度
    private BigInteger scScanNumber;

    public BigInteger getInfuraBlockNumber() {
        return infuraBlockNumber;
    }

    public void setInfuraBlockNumber(BigInteger infuraBlockNumber) {
        this.infuraBlockNumber = infuraBlockNumber;
    }

    public BigInteger getInfuraGasPrice() {
        return infuraGasPrice;
    }

    public void setInfuraGasPrice(BigInteger infuraGasPrice) {
        this.infuraGasPrice = infuraGasPrice;
    }

    public BigInteger getBcoinBlockNumber() {
        return bcoinBlockNumber;
    }

    public void setBcoinBlockNumber(BigInteger bcoinBlockNumber) {
        this.bcoinBlockNumber = bcoinBlockNumber;
    }

    public BigInteger getBcoinGasPrice() {
        return bcoinGasPrice;
    }

    public void setBcoinGasPrice(BigInteger bcoinGasPrice) {
        this.bcoinGasPrice = bcoinGasPrice;
    }

    public BigInteger getBcoinScaned() {
        return bcoinScaned;
    }

    public void setBcoinScaned(BigInteger bcoinScaned) {
        this.bcoinScaned = bcoinScaned;
    }

    public boolean isScWalletOpened() {
        return scWalletOpened;
    }

    public void setScWalletOpened(boolean scWalletOpened) {
        this.scWalletOpened = scWalletOpened;
    }

    public String getScWalletBalance() {
        return scWalletBalance;
    }

    public void setScWalletBalance(String scWalletBalance) {
        this.scWalletBalance = scWalletBalance;
    }

    public BigInteger getScBlockNumber() {
        return scBlockNumber;
    }

    public void setScBlockNumber(BigInteger scBlockNumber) {
        this.scBlockNumber = scBlockNumber;
    }

    public BigInteger getScScanNumber() {
        return scScanNumber;
    }

    public void setScScanNumber(BigInteger scScanNumber) {
        this.scScanNumber = scScanNumber;
    }

}
