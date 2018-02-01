/**
 * @Title XN625200Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:48:07 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:48:07 
 * @history:
 */
public class XN802150Req {

    @NotNull
    @Size(min = 1)
    List<String> wAddressList;

    @NotEmpty
    private String updater;

    private String remark;

    public List<String> getwAddressList() {
        return wAddressList;
    }

    public void setwAddressList(List<String> wAddressList) {
        this.wAddressList = wAddressList;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
