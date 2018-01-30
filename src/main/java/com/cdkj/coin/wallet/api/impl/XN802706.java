package com.cdkj.coin.wallet.api.impl;

import com.cdkj.coin.wallet.ao.IChargeAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.dto.req.XN802706Req;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * 详情查询充值订单
 * @author: xieyj 
 * @since: 2017年5月13日 下午7:58:10 
 * @history:
 */
public class XN802706 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802706Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return chargeAO.getCharge(req.getCode(), req.getSystemCode());
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802706Req.class);
        StringValidater.validateBlank(req.getCode(), req.getSystemCode());

    }

}
