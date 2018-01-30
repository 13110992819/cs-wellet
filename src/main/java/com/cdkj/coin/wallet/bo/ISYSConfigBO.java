/**
 * @Title ISYSConfigBO.java 
 * @Package com.xnjr.moom.bo 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:40:52 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.Map;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.SYSConfig;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午2:40:52 
 * @history:
 */
public interface ISYSConfigBO extends IPaginableBO<SYSConfig> {
    public int refreshSYSConfig(Long id, String cvalue, String updater,
            String remark);

    public SYSConfig getSYSConfig(Long id);

    public Map<String, String> getConfigsMap(String systemCode);

    public SYSConfig getSYSConfig(String key, String systemCode);

    public SYSConfig getSYSConfig(String key, String companyCode,
            String systemCode);

    public Map<String, String> getConfigsMap(String systemCode,
            String companyCode);

    public Map<String, String> getConfigsMap(String type, String companyCode,
            String systemCode);

    public String getStringValue(String key);

    public Double getDoubleValue(String key);

    public Integer getIntegerValue(String key);

    public Long getLongValue(String key);

    public BigDecimal getBigDecimalValue(String key);

}
