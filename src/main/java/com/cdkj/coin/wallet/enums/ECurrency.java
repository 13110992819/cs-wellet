/**
 * @Title ECurrency.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午4:41:06 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.coin.wallet.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年12月24日 下午1:51:38 
 * @history:
 */
public enum ECurrency {
    CNY("CNY", "人民币"),
    USD("USD", "美元"),
    HKD("HKD", "港币");


    public static Map<String, ECurrency> getCurrencyMap() {
        Map<String, ECurrency> map = new HashMap<String, ECurrency>();
        for (ECurrency currency : ECurrency.values()) {
            map.put(currency.getCode(), currency);
        }
        return map;
    }

    public static ECurrency getCurrency(String code) {
        Map<String, ECurrency> map = getCurrencyMap();
        ECurrency result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的currency不存在");
        }
        return result;
    }

    ECurrency(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
