/**
 * @Title XN802503Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月22日 下午1:03:23 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dto.res;

import java.util.List;

import com.cdkj.coin.wallet.domain.Account;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月22日 下午1:03:23 
 * @history:
 */
public class XN802503Res {

    // 账户列表
    private List<Account> accountList;

    // 总资产折合人民币
    private String totalAmountCNY;

    // 总资产折合美元
    private String totalAmountUSD;

    // 总资产折合港币
    private String totalAmountHKD;

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public String getTotalAmountCNY() {
        return totalAmountCNY;
    }

    public void setTotalAmountCNY(String totalAmountCNY) {
        this.totalAmountCNY = totalAmountCNY;
    }

    public String getTotalAmountUSD() {
        return totalAmountUSD;
    }

    public void setTotalAmountUSD(String totalAmountUSD) {
        this.totalAmountUSD = totalAmountUSD;
    }

    public String getTotalAmountHKD() {
        return totalAmountHKD;
    }

    public void setTotalAmountHKD(String totalAmountHKD) {
        this.totalAmountHKD = totalAmountHKD;
    }

}
