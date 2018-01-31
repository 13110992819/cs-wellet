package com.cdkj.coin.wallet.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.cdkj.coin.wallet.ao.IAccountAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.dto.req.XN802000Req;
import com.cdkj.coin.wallet.dto.res.BooleanRes;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 *  一人创建多个币种账户（注册时）
 * @author: myb858 
 * @since: 2017年4月3日 下午1:59:08 
 * @history:
 */
public class XN802000 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN802000Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        accountAO.distributeAccount(req.getUserId(), req.getRealName(),
            req.getCurrencyList(), req.getSystemCode(), req.getCompanyCode());
        return new BooleanRes(true);
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802000Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getSystemCode());
        if (CollectionUtils.isEmpty(req.getCurrencyList())) {
            new BizException("XN0000", "账户币种不能为空");
        }
    }

}
