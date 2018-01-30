/**
 * @Title XN625200Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:48:07 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:48:07 
 * @history:
 */
public class XN625200Req {

    @NotBlank
    private String availableDatetimeStart; // 可用时间起

    @NotBlank
    private String availableDatetimeEnd; // 可用时间止

    public String getAvailableDatetimeStart() {
        return availableDatetimeStart;
    }

    public void setAvailableDatetimeStart(String availableDatetimeStart) {
        this.availableDatetimeStart = availableDatetimeStart;
    }

    public String getAvailableDatetimeEnd() {
        return availableDatetimeEnd;
    }

    public void setAvailableDatetimeEnd(String availableDatetimeEnd) {
        this.availableDatetimeEnd = availableDatetimeEnd;
    }

}
