package com.cdkj.coin.wallet.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.coin.wallet.ao.IHLOrderAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.DateUtil;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.domain.HLOrder;
import com.cdkj.coin.wallet.dto.req.XN802805Req;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * 分页查询红冲蓝补订单
 * @author: xieyj 
 * @since: 2016年12月24日 上午8:17:00 
 * @history:
 */
public class XN802805 extends AProcessor {
    private IHLOrderAO hlOrderAO = SpringContextHolder
        .getBean(IHLOrderAO.class);

    private XN802805Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        HLOrder condition = new HLOrder();
        condition.setAccountNumber(req.getAccountNumber());
        condition.setAccountName(req.getAccountName());
        condition.setCurrency(req.getCurrency());
        condition.setJourCode(req.getJourCode());
        condition.setChannelType(req.getChannelType());

        condition.setDirection(req.getDirection());
        condition.setStatus(req.getStatus());
        condition.setApplyUser(req.getApplyUser());
        condition.setApplyDatetimeStart(DateUtil.getFrontDate(
            req.getApplyDateStart(), false));
        condition.setApplyDatetimeEnd(DateUtil.getFrontDate(
            req.getApplyDateEnd(), true));

        condition.setApproveUser(req.getApproveUser());
        condition.setApproveDatetimeStart(DateUtil.getFrontDate(
            req.getApproveDateStart(), false));
        condition.setApproveDatetimeEnd(DateUtil.getFrontDate(
            req.getApproveDateEnd(), true));
        condition.setSystemCode(req.getSystemCode());
        condition.setCompanyCode(req.getCompanyCode());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IHLOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return hlOrderAO.queryHLOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802805Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater
            .validateBlank(req.getSystemCode(), req.getCompanyCode());
    }
}
