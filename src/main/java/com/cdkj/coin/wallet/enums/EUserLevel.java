package com.cdkj.coin.wallet.enums;

/**
 * 用户等级
 *
 * @author: xieyj
 * @since: 2016年5月24日 上午9:11:47
 * @history:
 */
public enum EUserLevel {
    ONE("1", "普通交易者"), TWO("2", "代理人");

    EUserLevel(String code, String value) {
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
