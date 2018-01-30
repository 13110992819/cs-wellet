package com.cdkj.coin.wallet.enums;

public enum EEthMAddressStatus {
    NORMAL("0", "可使用"), IN_USE("1", "广播使用中"), INVALID("2", "已弃用");

    EEthMAddressStatus(String code, String value) {
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
