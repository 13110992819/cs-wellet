package com.cdkj.coin.wallet.api.impl;

import com.cdkj.coin.wallet.ao.IWithdrawAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.ObjValidater;
import com.cdkj.coin.wallet.dto.req.XN802758Req;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * 取现对账详情
 * @author: xieyj 
 * @since: 2017年5月17日 下午6:35:28 
 * @history:
 */
public class XN802758 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802758Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return withdrawAO.getWithdrawCheckInfo(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802758Req.class);
        ObjValidater.validateReq(req);
    }

}
