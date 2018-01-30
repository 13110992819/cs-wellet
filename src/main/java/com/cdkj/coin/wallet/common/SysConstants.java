package com.cdkj.coin.wallet.common;

public class SysConstants {

    public static final String admin = "admin";

    public static final String COLLECTION_LIMIT = "collection_limit"; // 账户余额大于等于该值时，进行归集

    public static final String TRADE_FEE_RATE = "trade_fee_rate"; // 默认广告手续费

    public static final String ACTIVITY_TRADE_FEE_RATE = "activity_trade_fee_rate"; // 活动期间交易广告费

    public static final String WITHDRAW_FEE = "withdraw_fee"; // 提现手续费

    public static final String FEN_CHENG_FEE = "fen_cheng_fee"; // 普通交易者默认分佣

    public static final String AGENT_FEN_CHENG_FEE = "agent_fen_cheng_fee"; // 代理人默认分佣

    public static final String ETH_COIN_PRICE_X = "eth_coin_price_x"; // 以太币价格计算因子

    // 七牛云图片配置
    public static String QINIU_ACCESS_KEY = "qiniu_access_key";

    public static String QINIU_SECRET_KEY = "qiniu_secret_key";

    public static String QINIU_BUCKET = "qiniu_bucket";

    public static String QINIU_DOMAIN = "qiniu_domain";

    // 腾讯云配置
    public static String TX_APP_CODE = "tx_app_code";// 应用编号

    public static String TX_APP_ADMIN = "tx_app_admin";// 应用编号

    public static String TX_ACCESS_KEY = "tx_access_key";// 公钥

    public static String TX_SECRET_KEY = "tx_secret_key";// 私钥

    public static String TX_ACCOUNT_TYPE = "tx_account_type";// 账号类型

    // 取现规则配置
    public static String CUSERQXBS = "CUSERQXBS"; // C端用户取现倍数

    public static String CUSERQXFL = "CUSERQXFL"; // C端用户取现费率

    public static String CUSERQXSX = "CUSERQXSX"; // C端用户取现时效

    public static String CUSERMONTIMES = "CUSERMONTIMES"; // C端用户每月取现次数

    public static String TRANSAMOUNTBS = "TRANSAMOUNTBS"; // C端2C端转账金额倍数

    public static String QXDBZDJE = "QXDBZDJE"; // 取现单笔最大金额

    // 短信模板
    public static String ORDER_CONTACT = "您的广告刚刚被新用户关注，可能随时跟您聊天，赶紧去看看吧！"; // 有人开始聊天

    public static String ORDER_SUBMIT = "您发布的广告已经有新用户下单了噢，赶紧去处理吧！"; // 有人下单

    public static String ARBITRATE = "您有一个新的仲裁订单（编号：%s）需要处理！"; // 有人仲裁

    public static String WITHDRAW = "您有一个新的取现订单（编号：%s）需要处理！"; // 有人提现

    // 短信通知配置
    public static String ZC_SMS_NOTICE = "zc_sms_notice"; // 仲裁短信通知

    public static String QX_SMS_NOTICE = "qx_sms_notice";// 取现短信通知

}
