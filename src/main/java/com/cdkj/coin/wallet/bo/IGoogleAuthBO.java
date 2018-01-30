/**
 * @Title IGoogleAuthBO.java 
 * @Package com.cdkj.coin.bo 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年12月6日 下午4:55:04 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.bo;

/** 
 * @author: haiqingzheng 
 * @since: 2017年12月6日 下午4:55:04 
 * @history:
 */
public interface IGoogleAuthBO {

    // 生成谷歌秘钥
    public String generateSecretKey();

    // 校验谷歌验证码
    public void checkCode(String secret, String googleCaptcha, Long timeMsec);

}
