package com.cdkj.coin.wallet.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2017年2月9日 下午8:10:43 
 * @history:
 */
public enum ESystemAccount {
    SYS_ACOUNT_ETH("SYS_ACOUNT_ETH", "平台ETH盈亏账户"), SYS_ACOUNT_ETH_COLD(
            "SYS_ACOUNT_ETH_COLD", "平台ETH冷钱包")

    , SYS_ACOUNT_SC("SYS_ACOUNT_SC", "平台SC盈亏账户"), SYS_ACOUNT_SC_COLD(
            "SYS_ACOUNT_SC_COLD", "平台SC冷钱包");
    public static Map<String, ESystemAccount> getMap() {
        Map<String, ESystemAccount> map = new HashMap<String, ESystemAccount>();
        for (ESystemAccount direction : ESystemAccount.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    ESystemAccount(String code, String value) {
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
