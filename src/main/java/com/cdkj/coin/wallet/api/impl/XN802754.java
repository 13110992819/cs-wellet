package com.cdkj.coin.wallet.api.impl;

import com.cdkj.coin.wallet.ao.IWithdrawAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.ObjValidater;
import com.cdkj.coin.wallet.dto.req.XN802754Req;
import com.cdkj.coin.wallet.dto.res.BooleanRes;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * 提现广播
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午2:07:37 
 * @history:
 */
public class XN802754 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802754Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        withdrawAO.broadcast(req.getCode(), req.getmAddressCode(),
            req.getApproveUser());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802754Req.class);
        ObjValidater.validateReq(req);
    }

}
