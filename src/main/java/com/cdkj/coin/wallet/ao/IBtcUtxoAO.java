/**
 * @Title IEthTransactionAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月7日 下午8:32:00 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ao;

import com.cdkj.coin.wallet.bitcoin.CtqBtcUtxo;
import com.cdkj.coin.wallet.bo.base.Paginable;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午8:32:00 
 * @history:
 */
public interface IBtcUtxoAO {

    // 充值
    public String chargeNotice(CtqBtcUtxo ctqBtcUtxo);

    // 提现
    public void withdrawNotice(CtqBtcUtxo ctqBtcUtxo);

    // 归集
    public void collection(String address, String chargeCode);

    // 归集交易通知处理
    public void collectionNotice(CtqBtcUtxo ctqBtcUtxo);

    // 分页查询广播记录
    public Paginable<CtqBtcUtxo> queryEthTransactionPage(int start, int limit,
            CtqBtcUtxo condition);

    // 每日定存
    public void depositNotice(CtqBtcUtxo ctqBtcUtxo);
}
