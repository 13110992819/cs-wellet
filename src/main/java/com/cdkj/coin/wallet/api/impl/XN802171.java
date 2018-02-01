/**
 * @Title XN802170.java 
 * @Package com.cdkj.coin.wallet.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年2月1日 下午8:08:46 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.api.impl;

import com.cdkj.coin.wallet.ao.IWithdrawAddressAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.ObjValidater;
import com.cdkj.coin.wallet.dto.req.XN802171Req;
import com.cdkj.coin.wallet.dto.res.BooleanRes;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/** 
 * @author: haiqingzheng 
 * @since: 2018年2月1日 下午8:08:46 
 * @history:
 */
public class XN802171 extends AProcessor {
    private IWithdrawAddressAO withdrawAddressAO = SpringContextHolder
        .getBean(IWithdrawAddressAO.class);

    private XN802171Req req = null;

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        withdrawAddressAO.dropWithdrawAddress(req.getCode());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doCheck(java.lang.String, java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802171Req.class);
        ObjValidater.validateReq(req);
    }

}
