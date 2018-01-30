/**
 * @Title CtqBOImpl.java 
 * @Package com.cdkj.coin.bo.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月7日 下午1:56:29 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.bo.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.domain.SYSConfig;
import com.cdkj.coin.wallet.dto.req.XN616000Req;
import com.cdkj.coin.wallet.dto.req.XN616020Req;
import com.cdkj.coin.wallet.dto.req.XN625917Req;
import com.cdkj.coin.wallet.enums.ESystemCode;
import com.cdkj.coin.wallet.http.BizConnecter;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月7日 下午1:56:29 
 * @history:
 */
@Component
public class CtqBOImpl implements ICtqBO {

    /** 
     * @see com.cdkj.coin.wallet.bo.ICtqBO#uploadAddress(java.lang.String, java.lang.String)
     */
    @Override
    public void uploadAddress(String address, String type) {
        XN616000Req req = new XN616000Req();
        req.setAddress(address);
        req.setType(type);
        BizConnecter.getBizData("626000", JsonUtil.Object2Json(req));
    }

    @Override
    public void confirm(List<String> hashList) {
        XN616020Req req = new XN616020Req();
        req.setHashList(hashList);
        BizConnecter.getBizData("626020", JsonUtil.Object2Json(req));
    }

    @Override
    public BigInteger getScanedBlockNumber() {
        BigInteger number = BigInteger.ZERO;
        XN625917Req req = new XN625917Req();
        req.setKey("curBlockNumber");
        req.setSystemCode(ESystemCode.COIN.getCode());
        req.setCompanyCode(ESystemCode.COIN.getCode());
        SYSConfig sysConfig = BizConnecter.getBizData("625917",
            JsonUtil.Object2Json(req), SYSConfig.class);
        if (sysConfig != null) {
            number = new BigInteger(sysConfig.getCvalue());
        }
        return number;
    }
}
