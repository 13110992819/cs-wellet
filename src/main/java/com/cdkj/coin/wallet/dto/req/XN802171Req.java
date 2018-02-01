/**
 * @Title XN802170Req.java 
 * @Package com.cdkj.coin.wallet.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年2月1日 下午8:09:43 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2018年2月1日 下午8:09:43 
 * @history:
 */
public class XN802171Req {

    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
