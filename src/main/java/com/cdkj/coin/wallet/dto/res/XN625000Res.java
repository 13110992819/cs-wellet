/**
 * @Title XN625000Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月16日 下午5:02:42 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月16日 下午5:02:42 
 * @history:
 */
public class XN625000Res {

    private String txAppCode;

    private String txAppAdmin;

    private String accountType;

    private transient String accessKey;

    private transient String secretKey;

    private String sign;

    public String getTxAppCode() {
        return txAppCode;
    }

    public void setTxAppCode(String txAppCode) {
        this.txAppCode = txAppCode;
    }

    public String getTxAppAdmin() {
        return txAppAdmin;
    }

    public void setTxAppAdmin(String txAppAdmin) {
        this.txAppAdmin = txAppAdmin;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
