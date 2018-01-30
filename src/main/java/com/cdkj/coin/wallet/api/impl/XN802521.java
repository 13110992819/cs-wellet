package com.cdkj.coin.wallet.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.coin.wallet.ao.IJourAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.DateUtil;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.domain.Jour;
import com.cdkj.coin.wallet.dto.req.XN802521Req;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * 列表查询流水(oss)
 * @author: xieyj 
 * @since: 2016年12月26日 下午12:29:08 
 * @history:
 */
public class XN802521 extends AProcessor {

    private IJourAO jourAO = SpringContextHolder.getBean(IJourAO.class);

    private XN802521Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Jour condition = new Jour();
        condition.setPayGroup(req.getPayGroup());
        condition.setRefNo(req.getRefNo());
        condition.setChannelType(req.getChannelType());
        condition.setChannelOrder(req.getChannelOrder());

        condition.setAccountNumber(req.getAccountNumber());
        condition.setCurrency(req.getCurrency());
        condition.setUserId(req.getUserId());
        condition.setRealName(req.getRealName());
        condition.setType(req.getType());

        condition.setBizType(req.getBizType());
        condition.setStatus(req.getStatus());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        condition.setWorkDate(req.getWorkDate());

        condition.setCheckUser(req.getCheckUser());
        condition.setAdjustUser(req.getAdjustUser());
        condition.setSystemCode(req.getSystemCode());
        condition.setCompanyCode(req.getCompanyCode());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IJourAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());

        return jourAO.queryJourList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802521Req.class);
        StringValidater
            .validateBlank(req.getSystemCode(), req.getCompanyCode());
    }
}
