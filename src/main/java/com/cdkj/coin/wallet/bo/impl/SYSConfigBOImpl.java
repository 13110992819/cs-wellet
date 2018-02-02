package com.cdkj.coin.wallet.bo.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.bo.base.PaginableBOImpl;
import com.cdkj.coin.wallet.domain.SYSConfig;
import com.cdkj.coin.wallet.dto.req.XN660917Req;
import com.cdkj.coin.wallet.enums.ESystemCode;
import com.cdkj.coin.wallet.http.BizConnecter;
import com.cdkj.coin.wallet.http.JsonUtils;

/**
 * 
 * @author: Gejin 
 * @since: 2016年4月17日 下午7:56:03 
 * @history:
 */

@Component
public class SYSConfigBOImpl extends PaginableBOImpl<SYSConfig> implements
        ISYSConfigBO {

    static Logger logger = Logger.getLogger(SYSConfigBOImpl.class);

    @Override
    public SYSConfig getSYSConfig(String key, String companyCode,
            String systemCode) {
        XN660917Req req = new XN660917Req();
        req.setCkey(key);
        req.setCompanyCode(companyCode);
        req.setSystemCode(systemCode);
        return BizConnecter.getBizData("660917", JsonUtils.object2Json(req),
            SYSConfig.class);
    }

    @Override
    public Double getDoubleValue(String key) {
        Double result = 0.0;
        SYSConfig config = getSYSConfig(key, ESystemCode.COIN.getCode(),
            ESystemCode.COIN.getCode());
        try {
            result = Double.valueOf(config.getCvalue());
        } catch (Exception e) {
            logger.error("参数名为" + key + "的配置转换成Double类型发生错误, 原因："
                    + e.getMessage());
        }
        return result;
    }

    @Override
    public Integer getIntegerValue(String key) {
        Integer result = 0;
        SYSConfig config = getSYSConfig(key, ESystemCode.COIN.getCode(),
            ESystemCode.COIN.getCode());
        try {
            result = Integer.valueOf(config.getCvalue());
        } catch (Exception e) {
            logger.error("参数名为" + key + "的配置转换成Integer类型发生错误, 原因："
                    + e.getMessage());
        }
        return result;
    }

    @Override
    public String getStringValue(String key) {
        SYSConfig config = getSYSConfig(key, ESystemCode.COIN.getCode(),
            ESystemCode.COIN.getCode());
        return config.getCvalue();
    }

    @Override
    public Long getLongValue(String key) {
        Long result = 0L;
        SYSConfig config = getSYSConfig(key, ESystemCode.COIN.getCode(),
            ESystemCode.COIN.getCode());
        try {
            result = Long.valueOf(config.getCvalue());
        } catch (Exception e) {
            logger.error("参数名为" + key + "的配置转换成Long类型发生错误, 原因："
                    + e.getMessage());
        }
        return result;
    }

    @Override
    public BigDecimal getBigDecimalValue(String key) {
        BigDecimal result = BigDecimal.ZERO;
        SYSConfig config = getSYSConfig(key, ESystemCode.COIN.getCode(),
            ESystemCode.COIN.getCode());
        try {
            result = new BigDecimal(config.getCvalue());
        } catch (Exception e) {
            logger.error("参数名为" + key + "的配置转换成BigDecimal类型发生错误, 原因："
                    + e.getMessage());
        }
        return result;
    }

}
