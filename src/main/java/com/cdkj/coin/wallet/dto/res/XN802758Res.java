/**
 * @Title XN802758Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月23日 下午7:44:55 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.res;

import java.util.List;

import com.cdkj.coin.wallet.domain.Jour;
import com.cdkj.coin.wallet.domain.Withdraw;
import com.cdkj.coin.wallet.ethereum.EthTransaction;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月23日 下午7:44:55 
 * @history:
 */
public class XN802758Res {

    // 取现订单详情
    private Withdraw withdraw;

    // 取现订单相关流水
    private List<Jour> jourList;

    // 取现相关广播记录
    private List<EthTransaction> transList;

    public Withdraw getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Withdraw withdraw) {
        this.withdraw = withdraw;
    }

    public List<Jour> getJourList() {
        return jourList;
    }

    public void setJourList(List<Jour> jourList) {
        this.jourList = jourList;
    }

    public List<EthTransaction> getTransList() {
        return transList;
    }

    public void setTransList(List<EthTransaction> transList) {
        this.transList = transList;
    }
}
