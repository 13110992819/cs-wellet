package com.cdkj.coin.wallet.enums;

/**
 * Created by tianlei on 2017/十一月/14.
 */
public enum EAdsPublishType {

    DRAFT("0", "存草稿"),
    PUBLISH("1", "直接发布"),
    PUBLISH_DRAFT("2", "草稿发布"),
    PUBLISH_REEDIT("3", "编辑发布，原广告下架");

    EAdsPublishType(String code, String value) {
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
