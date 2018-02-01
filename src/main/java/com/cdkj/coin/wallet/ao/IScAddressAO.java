/**
 * @Title IScAddressAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:38:33 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ao;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.siacoin.ScAddress;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:38:33 
 * @history:
 */
public interface IScAddressAO {

    // 弃用地址
    public void abandonAddress(String code);

    // 根据地址获取地址类型
    public EAddressType getType(String address);

    // 生成散取️地址（有私钥）
    public String generateMAddress(String updater, String remark);

    public Paginable<ScAddress> queryScAddressPage(int start, int limit,
            ScAddress condition);

    public ScAddress getScAddress(String code);

}
