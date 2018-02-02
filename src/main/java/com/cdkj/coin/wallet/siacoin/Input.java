/**
 * @Title ScInput.java 
 * @Package com.cdkj.coin.siacoin 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年1月30日 下午9:12:34 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.siacoin;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月30日 下午9:12:34 
 * @history:
 */
public class Input {
    private String parentid;

    private String fundtype;

    private boolean walletaddress;

    private String relatedaddress;

    private String value;

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getFundtype() {
        return fundtype;
    }

    public void setFundtype(String fundtype) {
        this.fundtype = fundtype;
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
