package com.cdkj.coin.wallet.dto.req;

public class XN805952Req {
    // 系统编号(必填)
    private String systemCode;

    // 公司编号(必填)
    private String companyCode;

    // 邮箱(必填)
    private String email;

    // 业务类型(必填)
    private String bizType;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
}
