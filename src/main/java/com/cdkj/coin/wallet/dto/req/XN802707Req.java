package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询充值订单详情
 * @author: xieyj 
 * @since: 2017年5月12日 上午10:00:44 
 * @history:
 */
public class XN802707Req {

    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
