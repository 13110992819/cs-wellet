package com.cdkj.coin.wallet.enums;

import com.cdkj.coin.wallet.exception.BizException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianlei on 2017/十一月/22.
 */
public enum ECommentLevel {

    CHA_PING("0", "差评"), ZHONG_PING("1", "中评"),HAO_PING("2","好评");

    ECommentLevel(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
