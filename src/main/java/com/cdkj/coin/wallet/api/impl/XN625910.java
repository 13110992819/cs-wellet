package com.cdkj.coin.wallet.api.impl;

import com.cdkj.coin.wallet.ao.ISYSConfigAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.dto.req.XN625910Req;
import com.cdkj.coin.wallet.dto.res.BooleanRes;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * 修改系统参数
 * @author: xieyj 
 * @since: 2016年11月23日 下午5:54:40 
 * @history:
 */
public class XN625910 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN625910Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        sysConfigAO.editSYSConfig(StringValidater.toLong(req.getId()),
            req.getCvalue(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625910Req.class);
        StringValidater.validateBlank(req.getId(), req.getUpdater(),
            req.getCvalue());
    }

}
