package com.cdkj.coin.wallet.enums;

/**
 * Created by tianlei on 2017/十二月/06.
 */
public enum EUserReleationType {

    BLACKLIST("0", "拉黑"),
    TRUST("1", "信任");

    EUserReleationType(String code, String value) {
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
