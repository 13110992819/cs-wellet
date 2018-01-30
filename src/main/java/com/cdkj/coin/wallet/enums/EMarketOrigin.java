package com.cdkj.coin.wallet.enums;

/**
 * Created by tianlei on 2017/十一月/13.
 */
public enum EMarketOrigin {

    BITFINEX("bitfinex","B站"),
    OKEX("okex","okex");



    EMarketOrigin(String code, String value) {
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
