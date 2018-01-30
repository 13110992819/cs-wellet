/**
 * @Title IEthNodeAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年1月18日 上午9:57:47 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ao;

import com.cdkj.coin.wallet.dto.res.XN625800Res;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月18日 上午9:57:47 
 * @history:
 */
public interface IEthNodeAO {

    // 获取节点监控信息
    public XN625800Res getNodeMonitorInfo();

}
