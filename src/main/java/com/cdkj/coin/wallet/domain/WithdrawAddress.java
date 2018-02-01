package com.cdkj.coin.wallet.domain;

import java.util.Date;

import com.cdkj.coin.wallet.dao.base.ABaseDO;

/**
* 取现地址
* @author: haiqingzheng
* @since: 2018年02月01日 20:01:31
* @history:
*/
public class WithdrawAddress extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 币种
    private String currency;

    // 提现地址
    private String address;

    // 标签
    private String label;

    // 用户编号
    private String userId;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 最后操作人
    private String updater;

    // 最后一次更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
