/**
 * @Title XN802902.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年12月7日 上午10:21:31 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.api.impl;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.ao.IAccountAO;
import com.cdkj.coin.wallet.ao.IScCollectionAO;
import com.cdkj.coin.wallet.ao.IWithdrawAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.dto.res.XN802902Res;
import com.cdkj.coin.wallet.enums.ESystemAccount;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.siacoin.SiadClient;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/** 
 * 平台SC币统计
 * @author: haiqingzheng 
 * @since: 2017年12月7日 上午10:21:31 
 * @history:
 */
public class XN802902 extends AProcessor {

    private IScCollectionAO scCollectionAO = SpringContextHolder
        .getBean(IScCollectionAO.class);

    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        XN802902Res res = new XN802902Res();
        // 散取钱包总额
        BigDecimal walletCount = SiadClient.getSiacoinBalance();
        // 历史总归集
        BigDecimal totolCollectCount = scCollectionAO.getTotalCollect();
        // 历史总取现
        BigDecimal totolWithdrawCount = withdrawAO.getTotalWithdraw();
        // 冷钱包余额
        BigDecimal coldCount = accountAO.getAccount(
            ESystemAccount.SYS_ACOUNT_SC_COLD.getCode()).getAmount();
        // 盈亏账户余额
        BigDecimal platCount = accountAO.getAccount(
            ESystemAccount.SYS_ACOUNT_SC.getCode()).getAmount();
        res.setWalletCount(walletCount.toString());
        res.setTotolCollectCount(totolCollectCount.toString());
        res.setTotolWithdrawCount(totolWithdrawCount.toString());
        res.setTotalCount(coldCount.add(walletCount).toString());
        res.setColdCount(coldCount.toString());
        res.setPlatCount(platCount.toString());
        return res;
    }

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

    }

}
