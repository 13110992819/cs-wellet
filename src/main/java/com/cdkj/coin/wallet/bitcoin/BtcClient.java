package com.cdkj.coin.wallet.bitcoin;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月30日 下午8:54:31 
 * @history:
 */
public class BtcClient {

    // 获取单个地址
    public static BtcAddressRes getSingleAddress() {
        NetworkParameters params = MainNetParams.get();
        ECKey key = new ECKey();
        String address = key.toAddress(params).toString();
        String privatekey = key.getPrivateKeyAsWiF(params);
        return new BtcAddressRes(address, privatekey);
    }

    // public static final String SC_URL = PropertiesUtil.Config.SC_NODE;
    //
    // final static String basic = Credentials.basic("zhangsan", "123456");

    public static void main(String[] args) {
        // createWalletWithSeed("onward menu aztec strained adrenalin inroads itself wanted dizzy ankle wedge napkin piloted haystack dabbing lipstick scrub nerves surfer hoax oatmeal unusual hydrogen circle sack oncoming greater hope ablaze");
        // unlock("onward menu aztec strained adrenalin inroads itself wanted dizzy ankle wedge napkin piloted haystack dabbing lipstick scrub nerves surfer hoax oatmeal unusual hydrogen circle sack oncoming greater hope ablaze");
        // System.out.println(getSingleAddress());
        // System.out
        // .println(getTransaction("716b17fbfece111839adb92d7aaaccffd97169ee4bf64333fa44b666433c62eb"));
    }

    // // 根据已有的种子创建钱包，需要unlock
    // public static String createWalletWithSeed(String seed) {
    // RequestBody formBody = new FormBody.Builder().add("seed", seed)
    // .add("force", "true").build();
    // return doAccessHTTPPostJson(SC_URL + "/wallet/init/seed", formBody);
    // }
    //
    // // 获取钱包余额
    // public static BigDecimal getSiacoinBalance() {
    // BigDecimal result = BigDecimal.ZERO;
    // String resStr = doAccessHTTPGetJson(SC_URL + "/wallet");
    // System.out.println(resStr);
    // result = JSONObject.parseObject(resStr).getBigDecimal(
    // "confirmedsiacoinbalance");
    // return result;
    // }
    //
    // // 获取钱包信息
    // public static String getWalletInfo() {
    // return doAccessHTTPGetJson(SC_URL + "/wallet");
    // }
    //
    // // 解锁钱包
    // public static String unlock(String password) {
    // RequestBody formBody = new FormBody.Builder().add("encryptionpassword",
    // password).build();
    // return doAccessHTTPPostJson(SC_URL + "/wallet/unlock", formBody);
    // }
    //
    // // 锁定钱包
    // public static String lock() {
    // RequestBody formBody = new FormBody.Builder().build();
    // return doAccessHTTPPostJson(SC_URL + "/wallet/lock", formBody);
    // }
    //
    // // 获取单个地址
    // public static String getSingleAddress() {
    // String address = null;
    // try {
    // String resStr = doAccessHTTPGetJson(SC_URL + "/wallet/address");
    // address = JSONObject.parseObject(resStr).getString("address");
    // if (address == null) {
    // String message = JSONObject.parseObject(resStr).getString(
    // "message");
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // "SC地址生成失败" + message);
    // }
    // } catch (Exception e) {
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // "获取SC地址异常，原因：" + e.getMessage());
    // }
    // return address;
    // }
    //
    // // 获取当前区块高度
    // public static BigInteger getBlockHeight() {
    // BigInteger result = BigInteger.ZERO;
    // String resStr = doAccessHTTPGetJson(SC_URL + "/consensus");
    // String height = JSONObject.parseObject(resStr).getString("height");
    // result = new BigInteger(height);
    // return result;
    // }
    //
    // // 获取当前区块高度
    // public static boolean verifyAddress(String address) {
    // boolean result = false;
    // if (StringUtils.isNotBlank(address)) {
    // try {
    // String resStr = doAccessHTTPGetJson(SC_URL
    // + "/wallet/verify/address/" + address);
    // result = JSONObject.parseObject(resStr).getBoolean("valid");
    // } catch (Exception e) {
    // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
    // "Siad访问异常，原因：" + e.getMessage());
    // }
    // }
    // return result;
    // }
    //
    // // 转账
    // public static String sendSingleAddress(String toAddress, BigDecimal
    // amount) {
    // String txId = null;
    // RequestBody formBody = new FormBody.Builder()
    // .add("destination", toAddress).add("amount", amount.toString())
    // .build();
    // String resStr = doAccessHTTPPostJson(SC_URL + "/wallet/siacoins",
    // formBody);
    // JSONArray txIdList = JSONObject.parseObject(resStr).getJSONArray(
    // "transactionids");
    // if (!txIdList.isEmpty()) {
    // txId = (String) txIdList.get(txIdList.size() - 1);
    // }
    // return txId;
    // }
    //
    // // 获取单个交易信息
    // public static Transaction getTransaction(String txId) {
    // Transaction transaction = null;
    // String resStr = doAccessHTTPGetJson(SC_URL + "/wallet/transaction/"
    // + txId);
    // String txStr = JSONObject.parseObject(resStr).getString("transaction");
    // Gson gson = new Gson();
    // transaction = gson.fromJson(txStr, new TypeToken<Transaction>() {
    // }.getType());
    // return transaction;
    // }
    //
    // public static String doAccessHTTPPostJson(String sendUrl,
    // RequestBody formBody) {
    //
    // String jsonStr = null;
    //
    // String requestStr = sendUrl;
    //
    // OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(1,
    // TimeUnit.DAYS).build();
    //
    // Request request = new Request.Builder()
    // .addHeader("User-Agent", "Sia-Agent")
    // .addHeader("Authorization", basic).url(requestStr).post(formBody)
    // .build();
    // Call call = okHttpClient.newCall(request);
    // try {
    //
    // Response response = call.execute();
    // jsonStr = response.body().string();
    // // System.out.println("请求URL：" + sendUrl);
    // // System.out.println("返回结果：" + jsonStr);
    //
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // return jsonStr;
    // }
    //
    // public static String doAccessHTTPGetJson(String sendUrl) {
    // // System.out.println("请求URL：" + sendUrl);
    // String jsonStr = null;
    //
    // String requestStr = sendUrl;
    //
    // OkHttpClient okHttpClient = new OkHttpClient();
    //
    // Request request = new Request.Builder().get()
    // .addHeader("User-Agent", "Sia-Agent")
    // .addHeader("Authorization", basic).url(requestStr).build();
    // Call call = okHttpClient.newCall(request);
    // try {
    //
    // Response response = call.execute();
    // jsonStr = response.body().string();
    //
    // // System.out.println("返回结果：" + jsonStr);
    //
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }
    // return jsonStr;
    // }
    //
    // public static BigDecimal toHasting(BigDecimal value) {
    // BigDecimal result = null;
    // result = value.multiply(new BigDecimal("1000000000000000000000000"));
    // return result;
    // }
    //
    // public static BigDecimal fromHasting(BigDecimal value) {
    // BigDecimal result = null;
    // result = value.divide(new BigDecimal("1000000000000000000000000"));
    // return result;
    // }
}
