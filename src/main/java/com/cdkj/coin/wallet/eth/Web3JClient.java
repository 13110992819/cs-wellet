/**
 * @Title Web3JClient.java 
 * @Package ethereum 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月18日 下午7:37:41 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.eth;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.cdkj.coin.wallet.common.PropertiesUtil;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月18日 下午7:37:41 
 * @history:
 */
public class Web3JClient {

    private static String ETH_URL = PropertiesUtil.Config.ETH_URL_OTHER;

    private Web3JClient() {
    }

    private volatile static Web3j web3j;

    public static Web3j getClient() {
        if (web3j == null) {
            synchronized (Web3JClient.class) {
                if (web3j == null) {
                    web3j = Web3j.build(new HttpService(ETH_URL));
                }
            }
        }
        return web3j;
    }
}
