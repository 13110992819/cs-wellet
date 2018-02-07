package com.cdkj.coin.wallet.enums;


public enum EBtcUtxoRefType {
    COLLECTION("0", "归集"), WITHDRAW("1", "取现");

    EBtcUtxoRefType(String code, String value) {
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
