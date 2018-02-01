/**
 * @Title XN625200.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:12:21 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.api.impl;

import com.cdkj.coin.wallet.ao.IScAddressAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.ObjValidater;
import com.cdkj.coin.wallet.dto.req.XN802150Req;
import com.cdkj.coin.wallet.dto.res.BooleanRes;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/** 
 * 导入归集地址（无私钥）
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:12:21 
 * @history:
 */
public class XN802150 extends AProcessor {

    private IScAddressAO scAddressAO = SpringContextHolder
        .getBean(IScAddressAO.class);

    private XN802150Req req = null;

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {

        for (String waddress : req.getwAddressList()) {
            scAddressAO.importWAddress(waddress, req.getUpdater(),
                req.getRemark());
        }

        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802150Req.class);
        ObjValidater.validateReq(req);
    }

}
