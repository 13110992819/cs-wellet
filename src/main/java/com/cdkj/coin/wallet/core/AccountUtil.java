/**
 * @Title AccountUtil.java 
 * @Package com.ibis.account.core 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午4:54:24 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.core;

import java.math.BigDecimal;
import java.util.Random;

import com.cdkj.coin.wallet.common.DateUtil;
import com.cdkj.coin.wallet.common.MD5Util;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午4:54:24 
 * @history:
 */
public class AccountUtil {
    /*
     * @Value("${account.md5.key}") private String key;
     */
    private static String key = "123";

    public static String md5(BigDecimal amount) {
        StringBuffer bf = new StringBuffer(key);
        bf.append(amount);
        return MD5Util.md5(bf.toString());
    }

    public static String md5(String preMd5, BigDecimal preAmount,
            BigDecimal nowAmount) {
        // if (preMd5 != null && !preMd5.equals(md5(preAmount))) {
        // throw new BizException("xn000000", "账户金额已被篡改，请联系管理员");
        // }
        StringBuffer bf = new StringBuffer(key);
        bf.append(nowAmount);
        return MD5Util.md5(bf.toString());
    }

    public static String generateAccountNumber() {
        int random = Math.abs(new Random().nextInt()) % 10;
        String today = DateUtil.getToday(DateUtil.DATA_TIME_PATTERN_3);
        return today + String.valueOf(random);
    }

}
