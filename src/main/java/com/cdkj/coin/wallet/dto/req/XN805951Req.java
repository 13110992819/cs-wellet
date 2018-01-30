package com.cdkj.coin.wallet.dto.req;

public class XN805951Req {

    // 系统编号(必填)
    private String systemCode;

    // 公司编号(必填)
    private String companyCode;

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
}
