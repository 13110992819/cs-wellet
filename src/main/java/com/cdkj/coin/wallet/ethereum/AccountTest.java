/**
 * @Title AccountTest.java 
 * @Package ethereum 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月18日 下午7:49:59 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ethereum;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;
import org.web3j.utils.Numeric;

import com.cdkj.coin.wallet.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月18日 下午7:49:59 
 * @history:
 */
public class AccountTest {

    public static void main(String args[]) {

        // for (int i = 0; i < 3; i++) {
        String amount = "5000000000000000000";
        System.out
            .println("准备开始广播，交易金额=" + Convert.fromWei(amount, Unit.ETHER));
        String hash = broadcast(
            "0x6cFa2A79263BD6989161616CC0054EbfEf2df30b".toLowerCase(),
            "12345678", "0x5eefbaf4ae486e17686b523d1718eb57beb2973d",
            new BigDecimal(amount));
        System.out.println("广播完成，交易hash=" + hash);
        // }

        // BigDecimal a = new BigDecimal("10000000000000000000");
        // BigDecimal b = new BigDecimal("9999999999999999999");
        // System.out.println(a + " " + b);

        // BigDecimal balance = new BigDecimal("10000000000000000");
        // if (balance.compareTo(Convert.toWei("0.01", Unit.ETHER)) < 0) {
        // throw new BizException("xn625000", "余额太少，无需归集");
        // }

        // createAccount();
    }

    public static void getBalance() {
        EthAccount account = new EthAccount();
        BigDecimal ba = account
            .getBalance("0xb1cd852c72141bdac6ccf314d9ea82d2af03f2ac");
        System.out.print(ba);
    }

    public static void queryAccount() {
        EthAccount account = new EthAccount();
        List<String> accounts = account.getAccountlist();
        for (String accountId : accounts) {
            System.out.println(accountId);
        }

    }

    public static void createAccount() {

        Admin selfAdmin = Admin.build(new HttpService(
            "http://47.96.161.183:8545/"));
        NewAccountIdentifier newAccountIdentifier;
        try {
            newAccountIdentifier = selfAdmin.personalNewAccount("123456")
                .send();
            if (newAccountIdentifier != null) {
                String address = newAccountIdentifier.getAccountId();
                System.out.println(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String broadcast(String from, String fromPassword, String to,
            BigDecimal value) {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        String txHash = null;
        try {
            String fileDirPath = "/Users/haiqingzheng/Desktop/ethereum/beikeying/data/keystore";
            File keyStoreFileDir = new File(fileDirPath);
            File[] subFiles = keyStoreFileDir.listFiles();
            File keystoreFile = null;
            for (File file : subFiles) {
                if (file.isDirectory() != true) {
                    // from: 0x244eb6078add0d58b2490ae53976d80f54a404ae
                    if (file.getName().endsWith(from.substring(2))) {
                        // 找到了该文件
                        keystoreFile = file;
                        break;
                    }
                }
            }
            if (keystoreFile == null) {
                throw new BizException("xn6250000", "未找到keystore文件");
            }
            //
            Credentials credentials = WalletUtils.loadCredentials(fromPassword,
                keystoreFile);
            //
            EthGetTransactionCount ethGetTransactionCount = web3j
                .ethGetTransactionCount(from, DefaultBlockParameterName.LATEST)
                .sendAsync().get();
            //
            BigInteger nonce = ethGetTransactionCount.getTransactionCount();

            // 预估矿工费用
            // BigDecimal gasPrice = new BigDecimal(web3j.ethGasPrice().send()
            // .getGasPrice().toString());
            BigDecimal gasPrice = new BigDecimal("40000000000");

            // 查询余额
            BigDecimal balance = null;
            DefaultBlockParameter defaultBlockParameter = DefaultBlockParameterName.LATEST;
            EthGetBalance ethGetBalance = web3j.ethGetBalance(from,
                defaultBlockParameter).send();
            if (ethGetBalance != null) {
                balance = new BigDecimal(ethGetBalance.getBalance().toString());
            } else {
                throw new BizException("xn625000", "以太坊余额查询失败");
            }
            // 预计消耗gas
            BigDecimal gasUsed = new BigDecimal(21000);
            BigDecimal txFee = gasPrice.multiply(gasUsed);

            // value为空，全部转出
            if (value == null) {
                value = balance.subtract(txFee);
            }

            System.out.println("地址余额=" + balance + "，以太坊平均价格=" + gasPrice
                    + "，预计矿工费=" + txFee + "，预计到账金额=" + value + "，预计剩余金额="
                    + balance.subtract(txFee).subtract(value));
            if (value.compareTo(BigDecimal.ZERO) < 0
                    || value.compareTo(BigDecimal.ZERO) == 0) {
                throw new BizException("xn625000", "余额不足以支付矿工费，不能转账");
            }

            // TODO 动态获取
            BigInteger gasLimit = BigInteger.valueOf(30000);

            System.out.println("预计矿工费:"
                    + Convert.fromWei(gasUsed.multiply(gasPrice).toString(),
                        Unit.ETHER));

            // 本地签名的
            RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce, new BigInteger(gasPrice.toString()), gasLimit, to,
                new BigInteger(value.toString()), "");

            // 签名
            byte[] signedMessage = TransactionEncoder.signMessage(
                rawTransaction, credentials);
            txHash = Numeric.toHexString(signedMessage);
            EthSendTransaction ethSendTransaction = web3j
                .ethSendRawTransaction(txHash).sendAsync().get();

            if (ethSendTransaction.getError() != null) {
                // failure
                throw new BizException("xn625000", "广播失败"
                        + ethSendTransaction.getError().getMessage());
            }
            txHash = ethSendTransaction.getTransactionHash();

        } catch (Exception e) {
            throw new BizException("xn625000", "交易广播异常-" + e.getMessage());
        }
        return txHash;
        // success

    }
}
