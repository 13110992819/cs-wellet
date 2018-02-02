/**
 * @Title XN625205Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:25:36 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

import java.util.List;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:25:36 
 * @history:
 */
public class XN802105Req extends APageReq {

    private static final long serialVersionUID = -4584603630956235345L;

    // 地址类型
    private String type;

    // 以太坊地址
    private String address;

    // 用户编号
    private String userId;

    // 可用时间起
    private String availableDatetimeStart;

    // 可用时间止
    private String availableDatetimeEnd;

    // 状态
    private String status;

    // 状态列表
    private List<String> statusList;

    // 余额起
    private String balanceStart;

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
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

    public String getAvailableDatetimeStart() {
        return availableDatetimeStart;
    }

    public void setAvailableDatetimeStart(String availableDatetimeStart) {
        this.availableDatetimeStart = availableDatetimeStart;
    }

    public String getAvailableDatetimeEnd() {
        return availableDatetimeEnd;
    }

    public void setAvailableDatetimeEnd(String availableDatetimeEnd) {
        this.availableDatetimeEnd = availableDatetimeEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBalanceStart() {
        return balanceStart;
    }

    public void setBalanceStart(String balanceStart) {
        this.balanceStart = balanceStart;
    }
}
