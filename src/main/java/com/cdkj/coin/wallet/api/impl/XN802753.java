package com.cdkj.coin.wallet.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.coin.wallet.ao.IWithdrawAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.dto.req.XN802753Req;
import com.cdkj.coin.wallet.dto.res.BooleanRes;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午2:07:37 
 * @history:
 */
public class XN802753 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802753Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        for (String code : req.getCodeList()) {
            withdrawAO.payOrder(code, req.getPayUser(), req.getPayResult(),
                req.getPayNote(), req.getChannelOrder(), req.getSystemCode());
        }
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802753Req.class);
        if (CollectionUtils.isEmpty(req.getCodeList())) {
            throw new BizException("订单列表不能为空");
        }
        StringValidater.validateBlank(req.getPayUser(), req.getPayResult(),
            req.getPayNote(), req.getSystemCode());
    }

}
