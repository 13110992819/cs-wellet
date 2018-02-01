package com.cdkj.coin.wallet.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.coin.wallet.exception.BizException;

public enum EAddressType {
    W("W", "归集地址"), M("M", "散取地址"), X("X", "分发地址"), Y("Y", "提现地址");

    EAddressType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public static EAddressType getAddressType(String code) {
        Map<String, EAddressType> map = getAddressTypeMap();
        EAddressType result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的bizType不存在");
        }
        return result;
    }

    public static Map<String, EAddressType> getAddressTypeMap() {
        Map<String, EAddressType> map = new HashMap<String, EAddressType>();
        for (EAddressType ethAddressType : EAddressType.values()) {
            map.put(ethAddressType.getCode(), ethAddressType);
        }
        return map;
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
