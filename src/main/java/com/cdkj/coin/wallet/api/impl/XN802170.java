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
import com.cdkj.coin.wallet.dto.req.XN802170Req;
import com.cdkj.coin.wallet.dto.res.BooleanRes;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/** 
 * @author: haiqingzheng 
 * @since: 2018年2月1日 下午8:08:46 
 * @history:
 */
public class XN802170 extends AProcessor {
    private IWithdrawAddressAO withdrawAddressAO = SpringContextHolder
        .getBean(IWithdrawAddressAO.class);

    private XN802170Req req = null;

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        withdrawAddressAO
            .addWithdrawAddress(req.getCurrency(), req.getAddress(),
                req.getLabel(), req.getUserId(), req.getIsCerti());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doCheck(java.lang.String, java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802170Req.class);
        ObjValidater.validateReq(req);
    }

}
