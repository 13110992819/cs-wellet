package com.cdkj.coin.wallet.common;

public class SysConstants {

    public static final String admin = "admin";

    public static final String COLLECTION_LIMIT_ETH = "collection_limit_eth"; // ETH地址余额大于等于该值时，进行归集

    public static final String COLLECTION_LIMIT_SC = "collection_limit_sc"; // SC钱包余额大于等于该值时，进行归集

    public static final String WITHDRAW_FEE_ETH = "withdraw_fee_eth"; // 提现手续费

    public static final String WITHDRAW_FEE_SC = "withdraw_fee_sc"; // 提现手续费

    // 取现规则配置
    public static String CUSERQXBS = "CUSERQXBS"; // C端用户取现倍数

    public static String CUSERQXFL = "CUSERQXFL"; // C端用户取现费率

    public static String CUSERQXSX = "CUSERQXSX"; // C端用户取现时效

    public static String CUSERMONTIMES = "CUSERMONTIMES"; // C端用户每月取现次数

    public static String TRANSAMOUNTBS = "TRANSAMOUNTBS"; // C端2C端转账金额倍数

    public static String QXDBZDJE = "QXDBZDJE"; // 取现单笔最大金额

}
