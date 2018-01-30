/**
 * @Title XN805123Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月23日 下午2:58:53 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月23日 下午2:58:53 
 * @history:
 */
public class XN805123Res {

    // 已成功邀请人数
    private Long inviteCount;

    // 提成收益
    private String inviteProfit;

    public Long getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Long inviteCount) {
        this.inviteCount = inviteCount;
    }

    public String getInviteProfit() {
        return inviteProfit;
    }

    public void setInviteProfit(String inviteProfit) {
        this.inviteProfit = inviteProfit;
    }
}
