/**
 * @Title SiadClient.java 
 * @Package com.cdkj.coin.siacoin 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年1月30日 下午8:54:31 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.siacoin;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.alibaba.fastjson.JSONObject;
import com.cdkj.coin.wallet.common.PropertiesUtil;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Credentials;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月30日 下午8:54:31 
 * @history:
 */
public class SiadClient {

    public static final String SC_URL = PropertiesUtil.Config.SC_NODE;

    final static String basic = Credentials.basic("zhangsan", "123456");

    public static void main(String[] args) {
        // createWalletWithSeed("onward menu aztec strained adrenalin inroads itself wanted dizzy ankle wedge napkin piloted haystack dabbing lipstick scrub nerves surfer hoax oatmeal unusual hydrogen circle sack oncoming greater hope ablaze");
        // unlock("onward menu aztec strained adrenalin inroads itself wanted dizzy ankle wedge napkin piloted haystack dabbing lipstick scrub nerves surfer hoax oatmeal unusual hydrogen circle sack oncoming greater hope ablaze");
        System.out.println(getSingleAddress());
    }

    // 根据已有的种子创建钱包，需要unlock
    public static String createWalletWithSeed(String seed) {
        RequestBody formBody = new FormBody.Builder().add("seed", seed)
            .add("force", "true").build();
        return doAccessHTTPPostJson(SC_URL + "/wallet/init/seed", formBody);
    }

    // 获取钱包信息
    public static String getWalletInfo() {
        return doAccessHTTPGetJson(SC_URL + "/wallet");
    }

    // 解锁钱包
    public static String unlock(String password) {
        RequestBody formBody = new FormBody.Builder().add("encryptionpassword",
            password).build();
        return doAccessHTTPPostJson(SC_URL + "/wallet/unlock", formBody);
    }

    // 锁定钱包
    public static String lock() {
        RequestBody formBody = new FormBody.Builder().build();
        return doAccessHTTPPostJson(SC_URL + "/wallet/lock", formBody);
    }

    // 获取单个地址
    public static String getSingleAddress() {
        String address = null;
        try {
            String resStr = doAccessHTTPGetJson(SC_URL + "/wallet/address");
            address = JSONObject.parseObject(resStr).getString("address");
            if (address == null) {
                String message = JSONObject.parseObject(resStr).getString(
                    "message");
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "SC地址生成失败" + message);
            }
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "获取SC地址异常，原因：" + e.getMessage());
        }
        return address;
    }

    // 获取当前区块高度
    public static BigInteger getBlockHeight() {
        BigInteger result = BigInteger.ZERO;
        String resStr = doAccessHTTPGetJson(SC_URL + "/consensus");
        String height = JSONObject.parseObject(resStr).getString("height");
        result = new BigInteger(height);
        return result;
    }

    // 获取钱包相关的交易列表
    public static List<Transaction> getTransactions(BigInteger startheight,
            BigInteger endheight) {
        List<Transaction> result = null;
        String resStr = doAccessHTTPGetJson(SC_URL
                + "/wallet/transactions?startheight=" + startheight
                + "&endheight=" + endheight);
        String txStr = JSONObject.parseObject(resStr).getString(
            "confirmedtransactions");

        if (txStr != null) {
            Gson gson = new Gson();
            result = gson.fromJson(txStr, new TypeToken<List<Transaction>>() {
            }.getType());
        }

        return result;
    }

    // 获取当前区块高度
    public static boolean verifyAddress(String address) {
        boolean result = false;
        String resStr = doAccessHTTPGetJson(SC_URL + "/wallet/verify/address/"
                + address);
        result = JSONObject.parseObject(resStr).getBoolean("valid");
        return result;
    }

    public static String doAccessHTTPPostJson(String sendUrl,
            RequestBody formBody) {

        String jsonStr = null;

        String requestStr = sendUrl;

        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(1,
            TimeUnit.DAYS).build();

        Request request = new Request.Builder()
            .addHeader("User-Agent", "Sia-Agent")
            .addHeader("Authorization", basic).url(requestStr).post(formBody)
            .build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            jsonStr = response.body().string();
            // System.out.println("请求URL：" + sendUrl);
            // System.out.println("返回结果：" + jsonStr);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonStr;
    }

    public static String doAccessHTTPGetJson(String sendUrl) {
        // System.out.println("请求URL：" + sendUrl);
        String jsonStr = null;

        String requestStr = sendUrl;

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().get()
            .addHeader("User-Agent", "Sia-Agent")
            .addHeader("Authorization", basic).url(requestStr).build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            jsonStr = response.body().string();

            // System.out.println("返回结果：" + jsonStr);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonStr;
    }
}
