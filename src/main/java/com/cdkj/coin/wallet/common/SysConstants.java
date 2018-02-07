package com.cdkj.coin.wallet.common;

public class SysConstants {

    public static final String admin = "admin";

    public static final String COLLECTION_LIMIT_ETH = "collection_limit_eth"; // ETH地址余额大于等于该值时，进行归集

    public static final String COLLECTION_LIMIT_SC = "collection_limit_sc"; // SC钱包余额大于等于该值时，进行归集

    public static final String COLLECTION_LIMIT_BTC = "collection_limit_btc"; // UTXO总额大于等于该值时，进行归集

    public static final String MIN_MINER_FEE_BTC = "min_miner_fee_btc"; // BTC单笔广播，平台最多愿意承担的最大矿工费，大于这个矿工费

    public static final String MAX_MINER_FEE_BTC = "max_miner_fee_btc"; // BTC单笔广播，平台最多愿意承担的最大矿工费，大于这个矿工费

    // 取现规则配置
    public static String CUSERQXBS = "CUSERQXBS"; // C端用户取现倍数

    public static String CUSERQXFL = "CUSERQXFL"; // C端用户取现费率

    public static String CUSERQXSX = "CUSERQXSX"; // C端用户取现时效

    public static String CUSERMONTIMES = "CUSERMONTIMES"; // C端用户每月取现次数

    public static String TRANSAMOUNTBS = "TRANSAMOUNTBS"; // C端2C端转账金额倍数

    public static String QXDBZDJE = "QXDBZDJE"; // 取现单笔最大金额

}
