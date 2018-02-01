/**
 * @Title XN623850Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月25日 下午1:39:58 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.res;

import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.ethereum.EthAddress;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月25日 下午1:39:58 
 * @history:
 */
public class XN623850Res {

    // 平台账户信息
    private Account account;

    // 以太坊地址信息
    private EthAddress ethAddress;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public EthAddress getEthAddress() {
        return ethAddress;
    }

    public void setEthAddress(EthAddress ethAddress) {
        this.ethAddress = ethAddress;
    }
}
