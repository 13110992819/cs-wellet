package com.cdkj.coin.wallet.enums;

public enum EBtcUtxoStatus {
    ENABLE("0", "未使用"), USING("1", "广播使用中"), USED("2", "已使用");

    EBtcUtxoStatus(String code, String value) {
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
