package com.cdkj.coin.wallet.enums;

public enum EWithdrawStatus {
    toApprove("1", "待审批"), Approved_NO("2", "审批不通过"), Approved_YES("3",
            "审批通过待广播"), Broadcast("4", "广播中"), Pay_NO("5", "广播失败"), Pay_YES(
            "6", "广播成功");
    EWithdrawStatus(String code, String value) {
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
