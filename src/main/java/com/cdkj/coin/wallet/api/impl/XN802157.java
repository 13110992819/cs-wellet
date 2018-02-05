/**
 * @Title XN625100.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月9日 下午7:00:49 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.api.impl;

import com.cdkj.coin.wallet.ao.IScTransactionAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.ObjValidater;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.dto.req.XN802157Req;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.siacoin.ScTransaction;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/** 
 * 分页查询广播记录
 * @author: haiqingzheng 
 * @since: 2017年11月9日 下午7:00:49 
 * @history:
 */
public class XN802157 extends AProcessor {

    private IScTransactionAO scTransactionAO = SpringContextHolder
        .getBean(IScTransactionAO.class);

    private XN802157Req req = null;

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        ScTransaction condition = new ScTransaction();
        condition.setTransactionid(req.getTransactionid());
        condition.setFrom(req.getFrom());
        condition.setTo(req.getTo());
        condition.setRefNo(req.getRefNo());
        condition.setAddress(req.getAddress());
        condition.setOrder("confirmationtimestamp", "desc");
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return scTransactionAO.queryScTransactionPage(start, limit, condition);
    }

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802157Req.class);
        ObjValidater.validateReq(req);
    }

}
