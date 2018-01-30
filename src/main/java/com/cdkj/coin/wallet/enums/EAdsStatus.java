package com.cdkj.coin.wallet.enums;

/**
 * Created by tianlei on 2017/十一月/14.
 */
public enum EAdsStatus {

    // DRAFT("0", "草稿"), DAIJIAOYI("1", "待交易"), JIAOYIZHONG("2", "交易中"), XIAJIA(
    // "3", "已下架");

    DRAFT("0", "草稿"), SHANGJIA("1", "已上架"), XIAJIA("2", "已下架");

    EAdsStatus(String code, String value) {
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
