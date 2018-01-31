package com.cdkj.coin.wallet.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties props;
    static {
        props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("找不到config.properties文件", e);
        } catch (IOException e) {
            throw new RuntimeException("读取config.properties文件出错", e);
        }
    }

    public static String getProperty(String key) {
        if (props == null) {
            // throw new Exception("配置文件初始化失败");
        }
        return props.getProperty(key);
    }

    public static final class Config {
        public static String CTQ_URL = props.getProperty("CTQ_URL");

        public static String SMS_URL = props.getProperty("SMS_URL");

        public static String CERTI_URL = props.getProperty("CERTI_URL");

        public static String ETH_URL_SELF = props.getProperty("ETH_URL_SELF");

        public static String ETH_URL_OTHER = props.getProperty("ETH_URL_OTHER");

        public static String KEY_STORE_PATH = props
            .getProperty("KEY_STORE_PATH");

        public static String ETH_NODE_INFURA = props
            .getProperty("ETH_NODE_INFURA");

        public static String ETH_NODE_BCOIN = props
            .getProperty("ETH_NODE_BCOIN");

        public static String SC_NODE = props.getProperty("SC_NODE");

    }
}
