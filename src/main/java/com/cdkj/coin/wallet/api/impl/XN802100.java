/**
 * @Title XN625200.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:12:21 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.api.impl;

import java.util.Date;

import com.cdkj.coin.wallet.ao.IEthAddressAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.DateUtil;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.dto.req.XN802100Req;
import com.cdkj.coin.wallet.dto.req.XN802100ReqList;
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
public class XN802100 extends AProcessor {

    private IEthAddressAO ethAddressAO = SpringContextHolder
        .getBean(IEthAddressAO.class);

    private XN802100ReqList req = null;

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {

        for (XN802100Req req : req.getwAddressList()) {
            // 想使用的日期 2017-09-01 至 2017-09-03
            // 数据库存储为 2017-09-01 00:00:00 至 2017-09-03 23:59:59
            Date start = DateUtil.getStartDatetime(req
                .getAvailableDatetimeStart());
            Date end = DateUtil.getEndDatetime(req.getAvailableDatetimeEnd());
            ethAddressAO.importWAddress(req.getAddress(), start, end);
        }

        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802100ReqList.class);
    }

}
