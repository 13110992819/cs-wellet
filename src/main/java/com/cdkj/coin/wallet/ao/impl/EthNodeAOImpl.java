/**
 * @Title EthNodeAOImpl.java 
 * @Package com.cdkj.coin.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年1月18日 上午10:06:32 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ao.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.admin.Admin;

import com.cdkj.coin.wallet.ao.IEthNodeAO;
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.dto.res.XN625800Res;
import com.cdkj.coin.wallet.eth.AdminClient;
import com.cdkj.coin.wallet.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月18日 上午10:06:32 
 * @history:
 */
@Service
public class EthNodeAOImpl implements IEthNodeAO {

    @Autowired
    ICtqBO ctqBO;

    private static Admin infuraAdmin = AdminClient.getInfuraClient();

    private static Admin bcoinAdmin = AdminClient.getBcoinClient();

    /** 
     * @see com.cdkj.coin.wallet.ao.IEthNodeAO#getNodeMonitorInfo()
     */
    @Override
    public XN625800Res getNodeMonitorInfo() {
        XN625800Res res = null;
        try {

            // 三方节点信息
            BigInteger infuraBlockNumber = infuraAdmin.ethBlockNumber().send()
                .getBlockNumber();
            BigInteger infuraGasPrice = infuraAdmin.ethGasPrice().send()
                .getGasPrice();

            // 倍可盈节点信息
            BigInteger bcoinBlockNumber = bcoinAdmin.ethBlockNumber().send()
                .getBlockNumber();
            BigInteger bcoinGasPrice = bcoinAdmin.ethGasPrice().send()
                .getGasPrice();

            BigInteger bcoinScaned = ctqBO.getScanedBlockNumber();

            res = new XN625800Res();

            res.setInfuraBlockNumber(infuraBlockNumber);
            res.setInfuraGasPrice(infuraGasPrice);

            res.setBcoinBlockNumber(bcoinBlockNumber);
            res.setBcoinGasPrice(bcoinGasPrice);
            res.setBcoinScaned(bcoinScaned);

        } catch (Exception e) {
            throw new BizException("获取节点监控信息失败，原因：" + e.getMessage());
        }
        return res;
    }

}
