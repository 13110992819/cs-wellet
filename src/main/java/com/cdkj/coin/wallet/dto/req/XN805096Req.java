package com.cdkj.coin.wallet.dto.req;

public class XN805096Req {

    // 用户编号（必填）
    private String userId;

    // 交易广告费率
    private String tradeRate;

    // 更新人（必填）
    private String updater;

    // 备注（选填）
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTradeRate() {
        return tradeRate;
    }

    public void setTradeRate(String tradeRate) {
        this.tradeRate = tradeRate;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
