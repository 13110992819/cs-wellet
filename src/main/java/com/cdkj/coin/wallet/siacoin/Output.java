/**
 * @Title Output.java 
 * @Package com.cdkj.coin.siacoin 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年1月30日 下午9:15:12 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.siacoin;

import java.math.BigInteger;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月30日 下午9:15:12 
 * @history:
 */
public class Output {
    private String id;

    private String fundtype;

    private BigInteger maturityheight;

    private boolean walletaddress;

    private String relatedaddress;

    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFundtype() {
        return fundtype;
    }

    public void setFundtype(String fundtype) {
        this.fundtype = fundtype;
    }

    public BigInteger getMaturityheight() {
        return maturityheight;
    }

    public void setMaturityheight(BigInteger maturityheight) {
        this.maturityheight = maturityheight;
    }

    public boolean isWalletaddress() {
        return walletaddress;
    }

    public void setWalletaddress(boolean walletaddress) {
        this.walletaddress = walletaddress;
    }

    public String getRelatedaddress() {
        return relatedaddress;
    }

    public void setRelatedaddress(String relatedaddress) {
        this.relatedaddress = relatedaddress;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
