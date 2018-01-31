package com.cdkj.coin.wallet.enums;

public enum EXAddressStatus {
    NORMAL("0", "正常"), CERTI("1", "已认证"), INVALID("2", "已弃用");

    EXAddressStatus(String code, String value) {
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
