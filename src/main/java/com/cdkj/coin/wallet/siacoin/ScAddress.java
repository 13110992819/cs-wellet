package com.cdkj.coin.wallet.siacoin;

import java.util.Date;
import java.util.List;

import com.cdkj.coin.wallet.dao.base.ABaseDO;
import com.cdkj.coin.wallet.domain.User;

/**
* SC地址
* @author: haiqingzheng
* @since: 2017年10月23日 21:53:23
* @history:
*/
public class ScAddress extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // ID主键
    private String code;

    // 地址类型
    private String type;

    // SC地址
    private String address;

    // 用户编号
    private String userId;

    // 账户编号
    private String accountNumber;

    // 状态
    private String status;

    // 创建时间
    private Date createDatetime;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /* DB properties 结束 */

    // 状态列表
    private List<String> statusList;

    // 类型列表
    private List<String> typeList;

    // 地址模糊查询
    private String addressForQuery;

    // 所属用户信息
    private User user;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public String getAddressForQuery() {
        return addressForQuery;
    }

    public void setAddressForQuery(String addressForQuery) {
        this.addressForQuery = addressForQuery;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
