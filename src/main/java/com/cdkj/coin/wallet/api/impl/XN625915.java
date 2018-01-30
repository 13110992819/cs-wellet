package com.cdkj.coin.wallet.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.coin.wallet.ao.ISYSConfigAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.domain.SYSConfig;
import com.cdkj.coin.wallet.dto.req.XN625915Req;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * 分页查询系统参数
 * @author: Gejin 
 * @since: 2016年4月17日 下午8:30:55 
 * @history:
 */
public class XN625915 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN625915Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSConfig condition = new SYSConfig();
        condition.setType(req.getType());
        condition.setCkeyForQuery(req.getCkey());
        condition.setUpdater(req.getUpdater());
        condition.setCompanyCode(req.getCompanyCode());
        condition.setSystemCode(req.getSystemCode());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISYSConfigAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return sysConfigAO.querySYSConfigPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException
    {
        req = JsonUtil.json2Bean(inputparams, XN625915Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater
            .validateBlank(req.getSystemCode(), req.getCompanyCode());
    }
}
