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
public class XN802205Req extends APageReq {

    private static final long serialVersionUID = -4584603630956235345L;

    // 地址类型
    private String type;

    // btc地址
    private String address;

    // 用户编号
    private String userId;

    // 账户编号
    private String accountNumber;

    // 状态
    private String status;

    // 状态列表
    private List<String> statusList;

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

}
