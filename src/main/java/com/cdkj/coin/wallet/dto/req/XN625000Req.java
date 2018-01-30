/**
 * @Title XN625000Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月17日 下午12:58:31 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月17日 下午12:58:31 
 * @history:
 */
public class XN625000Req {

    @NotBlank
    private String userId; // 用户编号

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
