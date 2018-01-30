/**
 * @Title Account.java 
 * @Package ethereum 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月18日 下午7:47:45 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.eth;

import java.math.BigDecimal;
import java.util.List;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import com.cdkj.coin.wallet.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月18日 下午7:47:45 
 * @history:
 */
public class EthAccount {

    private static Admin admin = AdminClient.getSelfClient();

    private static Web3j web3j = Web3JClient.getClient();

    /**
     * Life
     * Like this
     * Like that
     * Also
     * It's not the same with you think
     * @Author lzh
     *
     */
    public List<String> getAccountlist() {

        try {
            return admin.personalListAccounts().send().getAccountIds();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String createAccount(String accountName, String password,
            AccountInfo accountInfo) {
        try {
            NewAccountIdentifier newAccountIdentifier = admin
                .personalNewAccount(password).send();
            if (newAccountIdentifier != null) {
                String accountId = newAccountIdentifier.getAccountId();
                return accountId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BigDecimal getBalance(String accountId) {
        try {

            DefaultBlockParameter defaultBlockParameter = DefaultBlockParameterName.LATEST;
            // EthGetBalance ethGetBalance = admin.ethGetBalance(accountId,
            // defaultBlockParameter).send();
            EthGetBalance ethGetBalance = web3j.ethGetBalance(accountId,
                defaultBlockParameter).send();
            if (ethGetBalance != null && ethGetBalance.getBalance() != null) {
                return new BigDecimal(ethGetBalance.getBalance().toString());
            }
        } catch (Exception e) {
            throw new BizException("xn625000", "以太坊余额查询异常，原因：" + e.getMessage());
        }
        return null;
    }
}
