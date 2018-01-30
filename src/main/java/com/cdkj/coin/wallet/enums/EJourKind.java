package com.cdkj.coin.wallet.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.coin.wallet.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:54:16 
 * @history:
 */
public enum EJourKind {

    BALANCE("0", "余额流水"), FROZEN("1", "冻结金额流水");

    public static Map<String, EJourKind> getJourKindResultMap() {
        Map<String, EJourKind> map = new HashMap<String, EJourKind>();
        for (EJourKind type : EJourKind.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    public static EJourKind getJourKind(String code) {
        Map<String, EJourKind> map = getJourKindResultMap();
        EJourKind result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的EJourKind不存在");
        }
        return result;
    }

    EJourKind(String code, String value) {
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
