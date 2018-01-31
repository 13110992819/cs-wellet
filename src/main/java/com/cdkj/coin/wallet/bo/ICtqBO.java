/**
 * @Title ICtqBO.java 
 * @Package com.cdkj.coin.bo 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月7日 下午1:54:35 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.bo;

import java.math.BigInteger;
import java.util.List;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午1:54:35 
 * @history:
 */
public interface ICtqBO {
    // 上传ETH地址
    public void uploadEthAddress(String address, String type);

    // ETH交易确认
    public void confirmEth(List<String> hashList);

    // 获取ETH节点扫描信息
    public BigInteger getScanedBlockNumber();

    // 上传SC地址
    public void uploadScAddress(String address, String type);

    // SC交易确认
    public void confirmSc(List<String> hashList);
}
