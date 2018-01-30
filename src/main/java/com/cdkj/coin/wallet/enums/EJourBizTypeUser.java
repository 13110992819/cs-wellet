package com.cdkj.coin.wallet.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.coin.wallet.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EJourBizTypeUser {
    AJ_CHARGE("charge", "充值"), AJ_WITHDRAW("withdraw", "取现"), AJ_BUY("buy",
            "交易买入"), AJ_SELL("sell", "交易卖出"), AJ_TRADEFEE("tradefee", "交易广告费"), AJ_WITHDRAWFEE(
            "withdrawfee", "取现手续费"), AJ_INVITE("invite", "推荐好友分成"), AJ_HC("hc",
            "红冲"), AJ_LB("lb", "蓝补"),

    AJ_ADS_FROZEN("tradefrozen", "交易冻结"), AJ_ADS_UNFROZEN("tradeunfrozen",
            "交易解冻"), AJ_WITHDRAW_FROZEN("withdrawfrozen", "取现冻结"), AJ_WITHDRAW_UNFROZEN(
            "withdrawunfrozen", "取现解冻");

    public static EJourBizTypeUser getBizType(String code) {
        Map<String, EJourBizTypeUser> map = getBizTypeMap();
        EJourBizTypeUser result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeUser> getBizTypeMap() {
        Map<String, EJourBizTypeUser> map = new HashMap<String, EJourBizTypeUser>();
        for (EJourBizTypeUser bizType : EJourBizTypeUser.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeUser(String code, String value) {
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
