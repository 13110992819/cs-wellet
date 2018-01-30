package com.cdkj.coin.wallet.enums;

/**
 * Created by tianlei on 2017/十二月/05.
 */
public enum EUserSettingsType {


    AUTO_HAOPING("1", "设置自动好评"),
    AUTO_TRUST("2", "设置自动信任");

    EUserSettingsType(String code, String value) {
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
