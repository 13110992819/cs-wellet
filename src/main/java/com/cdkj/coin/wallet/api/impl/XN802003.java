package com.cdkj.coin.wallet.api.impl;

import com.cdkj.coin.wallet.ao.IAccountAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.ObjValidater;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.dto.req.XN802003Req;
import com.cdkj.coin.wallet.dto.res.BooleanRes;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * 解冻金额
 * @author: haiqingzheng 
 * @since: 2018年2月3日 下午8:37:31 
 * @history:
 */
public class XN802003 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN802003Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        accountAO.unfrozenAmount(req.getUserId(), req.getCurrency(),
            StringValidater.toBigDecimal(req.getUnfreezeAmount()),
            req.getBizType(), req.getBizNote(), req.getRefNo());
        return new BooleanRes(true);
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802003Req.class);
        ObjValidater.validateReq(req);
    }

}
