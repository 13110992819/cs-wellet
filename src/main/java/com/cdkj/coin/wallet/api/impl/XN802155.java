/**
 * @Title XN625205.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:16:17 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.api.impl;

import com.cdkj.coin.wallet.ao.IScAddressAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.ObjValidater;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.dto.req.XN802155Req;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/** 
 * 分页查询SC地址
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:16:17 
 * @history:
 */
public class XN802155 extends AProcessor {

    private IScAddressAO scAddressAO = SpringContextHolder
        .getBean(IScAddressAO.class);

    private XN802155Req req = null;

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        ScAddress condition = new ScAddress();
        condition.setStatusList(req.getStatusList());
        condition.setType(req.getType());
        condition.setAddressForQuery(req.getAddress());
        condition.setUserId(req.getUserId());
        condition.setStatus(req.getStatus());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return scAddressAO.queryScAddressPage(start, limit, condition);
    }

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802155Req.class);
        ObjValidater.validateReq(req);
    }

}
