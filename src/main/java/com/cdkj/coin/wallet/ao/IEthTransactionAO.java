/**
 * @Title IEthTransactionAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月7日 下午8:32:00 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ao;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.ethereum.CtqEthTransaction;
import com.cdkj.coin.wallet.ethereum.EthTransaction;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午8:32:00 
 * @history:
 */
public interface IEthTransactionAO {

    // 充值
    public String chargeNotice(CtqEthTransaction ctqEthTransaction);

    // 提现
    public void withdrawNotice(CtqEthTransaction ctqEthTransaction);

    // 归集
    public void collection(String address, String chargeCode);

    // 归集交易通知处理
    public void collectionNotice(CtqEthTransaction ctqEthTransaction);

    // 分页查询广播记录
    public Paginable<EthTransaction> queryEthTransactionPage(int start,
            int limit, EthTransaction condition);

    // 每日定存
    public void depositNotice(CtqEthTransaction ctqEthTransaction);
}
