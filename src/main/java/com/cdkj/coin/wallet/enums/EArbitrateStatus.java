package com.cdkj.coin.wallet.enums;

public enum EArbitrateStatus {
    TO_HANDLE("0", "待处理"), COMPLETE("1", "已处理"), CANCEL("2", "已取消");

    EArbitrateStatus(String code, String value) {
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
