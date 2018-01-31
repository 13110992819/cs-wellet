/**
 * @Title XN802450Req.java 
 * @Package com.std.account.dto.req 
 * @Description 
 * @author xieyj  
 * @date 2016年12月23日 下午7:48:53 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.req;

import java.util.List;

/** 
 * 分配C端用户账户
 * @author: haiqingzheng 
 * @since: 2018年1月31日 下午5:43:44 
 * @history:
 */
public class XN802000Req {
    // 用户编号(必填)
    private String userId;

    // 户名(选填）
    private String realName;

    // 币种列表(必填)
    private List<String> currencyList;

    // 系统编号(必填)
    private String systemCode;

    // 公司编号(必填)
    private String companyCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
