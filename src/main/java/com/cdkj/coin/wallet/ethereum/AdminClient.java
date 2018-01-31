/**
 * @Title ParityClient.java 
 * @Package ethereum 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月18日 下午7:42:57 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ethereum;

import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

import com.cdkj.coin.wallet.common.PropertiesUtil;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月18日 下午7:42:57 
 * @history:
 */
public class AdminClient {

    private static String ETH_URL_SELF = PropertiesUtil.Config.ETH_URL_SELF;

    private static String ETH_URL_OTHER = PropertiesUtil.Config.ETH_URL_OTHER;

    private static String ETH_NODE_INFURA = PropertiesUtil.Config.ETH_NODE_INFURA;

    private static String ETH_NODE_BCOIN = PropertiesUtil.Config.ETH_NODE_BCOIN;

    private AdminClient() {
    }

    private static class ClientHolder {
        private static final Admin selfAdmin = Admin.build(new HttpService(
            ETH_URL_SELF));

        private static final Admin otherAdmin = Admin.build(new HttpService(
            ETH_URL_OTHER));

        private static final Admin infuraAdmin = Admin.build(new HttpService(
            ETH_NODE_INFURA));

        private static final Admin bcoinAdmin = Admin.build(new HttpService(
            ETH_NODE_BCOIN));
    }

    public static final Admin getSelfClient() {
        return ClientHolder.selfAdmin;
    }

    public static final Admin getThirdClient() {
        return ClientHolder.otherAdmin;
    }

    public static final Admin getInfuraClient() {
        return ClientHolder.infuraAdmin;
    }

    public static final Admin getBcoinClient() {
        return ClientHolder.bcoinAdmin;
    }
}
