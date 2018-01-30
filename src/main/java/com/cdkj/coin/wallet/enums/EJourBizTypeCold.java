package com.cdkj.coin.wallet.enums;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.coin.wallet.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:09:32 
 * @history:
 */
public enum EJourBizTypeCold {
    AJ_INCOME("income", "收入"), AJ_PAY("pay", "支出"), AJ_HC("hc", "红冲"), AJ_LB(
            "lb", "蓝补");
    public static EJourBizTypeCold getBizType(String code) {
        Map<String, EJourBizTypeCold> map = getBizTypeMap();
        EJourBizTypeCold result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeCold> getBizTypeMap() {
        Map<String, EJourBizTypeCold> map = new HashMap<String, EJourBizTypeCold>();
        for (EJourBizTypeCold bizType : EJourBizTypeCold.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeCold(String code, String value) {
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
