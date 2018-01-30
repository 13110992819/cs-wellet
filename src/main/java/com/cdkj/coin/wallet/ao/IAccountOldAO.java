/**
 * @Title IAccountAO.java 
 * @Package com.std.user.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月10日 上午11:08:04 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.domain.Account;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月10日 上午11:08:04 
 * @history:
 */
public interface IAccountOldAO {

    // 根据用户ID获取账户列表信息
    public List<Account> getAccountByUserId(String userId);

    // 以太币划转
    public void transfer(String fromUserId, String toAddress, BigDecimal amount);

    // 取现订单支付（广播）
    public void payOrder(String code, String payUser, String payResult,
            String payNote);
}
