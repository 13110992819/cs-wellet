/**
 * @Title XNlh5013.java 
 * @Package com.xnjr.moom.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午7:40:47 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.coin.wallet.ao.ISYSDictAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.domain.SYSDict;
import com.cdkj.coin.wallet.dto.req.XN625905Req;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/** 
 * 分页查询数据字典
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午7:40:47 
 * @history:
 */
public class XN625905 extends AProcessor {
    private ISYSDictAO sysDictAO = SpringContextHolder
        .getBean(ISYSDictAO.class);

    private XN625905Req req = null;

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        SYSDict condition = new SYSDict();
        condition.setCompanyCode(req.getCompanyCode());
        condition.setSystemCode(req.getSystemCode());
        condition.setType(req.getType());
        condition.setParentKey(req.getParentKey());
        condition.setDkey(req.getDkey());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ISYSDictAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return sysDictAO.querySYSDictPage(start, limit, condition);
    }

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625905Req.class);
        StringValidater
            .validateBlank(req.getSystemCode(), req.getCompanyCode());
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
