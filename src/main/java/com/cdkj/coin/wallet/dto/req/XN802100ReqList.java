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

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:48:07 
 * @history:
 */
public class XN802100ReqList {

    List<XN802100Req> wAddressList;

    public List<XN802100Req> getwAddressList() {
        return wAddressList;
    }

    public void setwAddressList(List<XN802100Req> wAddressList) {
        this.wAddressList = wAddressList;
    }

}
