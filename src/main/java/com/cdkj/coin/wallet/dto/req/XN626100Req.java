/**
 * @Title XN616020Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月7日 下午9:38:09 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.cdkj.coin.wallet.bitcoin.CtqBtcUtxo;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午9:38:09 
 * @history:
 */
public class XN626100Req {

    @NotNull
    List<CtqBtcUtxo> utxoList;

    public List<CtqBtcUtxo> getUtxoList() {
        return utxoList;
    }

    public void setUtxoList(List<CtqBtcUtxo> utxoList) {
        this.utxoList = utxoList;
    }

}
