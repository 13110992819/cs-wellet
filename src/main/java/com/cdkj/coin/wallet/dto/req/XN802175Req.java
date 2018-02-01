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
public class XN802175Req extends APageReq {

    private static final long serialVersionUID = -616331602757298592L;

    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
