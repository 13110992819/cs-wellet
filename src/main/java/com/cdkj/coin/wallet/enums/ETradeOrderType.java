package com.cdkj.coin.wallet.enums;

public enum ETradeOrderType {
    BUY("buy", "购买订单"), SELL("sell", "出售订单");

    ETradeOrderType(String code, String value) {
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
