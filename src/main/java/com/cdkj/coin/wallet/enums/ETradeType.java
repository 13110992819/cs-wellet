package com.cdkj.coin.wallet.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianlei on 2017/十一月/14.
 */
public enum ETradeType {

    SELL("1", "卖"),
    BUY("0", "买");

    public static Map<String, ETradeType> getDirectionMap() {
        Map<String, ETradeType> map = new HashMap<String, ETradeType>();
        for (ETradeType direction : ETradeType.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    ETradeType(String code, String value) {
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
