package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotEmpty;

public class XN802200Req {

    @NotEmpty
    String address;

    @NotEmpty
    private String updater;

    private String remark;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
