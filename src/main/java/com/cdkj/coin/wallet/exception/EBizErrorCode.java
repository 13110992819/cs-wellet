package com.cdkj.coin.wallet.exception;

/**
 * @author: haiqingzheng 
 * @since: 2017年11月14日 下午1:05:03 
 * @history:
 */
public enum EBizErrorCode {

    DEFAULT("xn805000", "业务异常");
    // PUSH_STATUS_UPDATE_FAILURE("eth000001","地址状态更新失败");

    private String code;

    private String info;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    EBizErrorCode(String code, String info) {
        this.code = code;
        this.info = info;
    }
}
