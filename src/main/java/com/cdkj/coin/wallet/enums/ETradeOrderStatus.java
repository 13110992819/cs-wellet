package com.cdkj.coin.wallet.enums;

public enum ETradeOrderStatus {
    TO_SUBMIT("-1", "代下单"), TO_PAY("0", "待支付"), PAYED("1", "已支付待释放"), RELEASED(
            "2", "已释放待评价"), COMPLETE("3", "已完成"), CANCEL("4", "已取消"), ARBITRATE(
            "5", "仲裁中");

    ETradeOrderStatus(String code, String value) {
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
