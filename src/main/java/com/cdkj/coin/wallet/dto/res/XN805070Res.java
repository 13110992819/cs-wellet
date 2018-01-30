/**
 * @Title XN805070Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年12月6日 下午5:22:28 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年12月6日 下午5:22:28 
 * @history:
 */
public class XN805070Res {

    // 谷歌secret
    private String secret;

    public XN805070Res(String secret) {
        super();
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}
