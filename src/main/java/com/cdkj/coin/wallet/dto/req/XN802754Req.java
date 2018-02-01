package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 广播
 * @author: xieyj 
 * @since: 2017年5月12日 上午10:02:36 
 * @history:
 */
public class XN802754Req {

    // 取现订单编号(必填)
    @NotBlank
    private String code;

    // 散取地址编号(币种为以太坊时必填)
    private String mAddressCode;

    // 操作人(必填)
    @NotBlank
    private String approveUser;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getmAddressCode() {
        return mAddressCode;
    }

    public void setmAddressCode(String mAddressCode) {
        this.mAddressCode = mAddressCode;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

}
