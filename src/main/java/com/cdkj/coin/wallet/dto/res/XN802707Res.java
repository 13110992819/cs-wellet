/**
 * @Title XN802707Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月23日 下午2:47:23 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.res;

import java.util.List;

import com.cdkj.coin.wallet.domain.Charge;
import com.cdkj.coin.wallet.domain.Jour;
import com.cdkj.coin.wallet.ethereum.EthCollection;
import com.cdkj.coin.wallet.ethereum.EthTransaction;
import com.cdkj.coin.wallet.siacoin.ScTransaction;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月23日 下午2:47:23 
 * @history:
 */
public class XN802707Res {

    // 充值订单详情
    private Charge charge;

    // 充值关联归集订单详情
    private EthCollection ethCollection;

    // 充值订单相关流水
    private List<Jour> jourList;

    // ETH充值相关广播记录
    private List<EthTransaction> ethTransList;

    // SC充值相关广播记录
    private List<ScTransaction> scTransList;

    public EthCollection getEthCollection() {
        return ethCollection;
    }

    public void setEthCollection(EthCollection ethCollection) {
        this.ethCollection = ethCollection;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public List<Jour> getJourList() {
        return jourList;
    }

    public void setJourList(List<Jour> jourList) {
        this.jourList = jourList;
    }

    public List<EthTransaction> getEthTransList() {
        return ethTransList;
    }

    public void setEthTransList(List<EthTransaction> ethTransList) {
        this.ethTransList = ethTransList;
    }

    public List<ScTransaction> getScTransList() {
        return scTransList;
    }

    public void setScTransList(List<ScTransaction> scTransList) {
        this.scTransList = scTransList;
    }

}
