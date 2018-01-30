package com.cdkj.coin.wallet.api.impl;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.ao.IWithdrawAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.dto.req.XN802750Req;
import com.cdkj.coin.wallet.dto.res.PKCodeRes;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * 线下取现申请(需交易密码)
 * @author: myb858 
 * @since: 2017年4月24日 下午8:00:31 
 * @history:
 */
public class XN802750 extends AProcessor {

    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802750Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        BigDecimal amount = StringValidater.toBigDecimal(req.getAmount());
        String code = withdrawAO.applyOrderTradePwd(req.getAccountNumber(),
            amount, req.getPayCardInfo(), req.getPayCardNo(),
            req.getApplyUser(), req.getApplyNote(), req.getTradePwd(),
            req.getGoogleCaptcha());
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802750Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getPayCardInfo(), req.getPayCardNo(), req.getApplyUser());
        StringValidater.validateAmount(req.getAmount());
    }
}
