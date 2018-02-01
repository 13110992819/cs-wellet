/**
 * @Title IScTransactionAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月7日 下午8:32:00 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ao;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.siacoin.CtqScTransaction;
import com.cdkj.coin.wallet.siacoin.ScTransaction;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午8:32:00 
 * @history:
 */
public interface IScTransactionAO {

    // 充值
    public String chargeNotice(CtqScTransaction ctqScTransaction);

    // 提现
    public void withdrawNotice(CtqScTransaction ctqScTransaction);

    // 归集
    public void collection(String chargeCode);

    // 归集交易通知处理
    public void collectionNotice(CtqScTransaction ctqScTransaction);

    // 分页查询广播记录
    public Paginable<ScTransaction> queryScTransactionPage(int start,
            int limit, ScTransaction condition);

    // 每日定存
    public void depositNotice(CtqScTransaction ctqScTransaction);
}
