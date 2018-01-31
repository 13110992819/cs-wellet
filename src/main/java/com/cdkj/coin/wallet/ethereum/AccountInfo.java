/**
 * @Title AccountInfo.java 
 * @Package ethereum 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月18日 下午7:47:08 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ethereum;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月18日 下午7:47:08 
 * @history:
 */
public class AccountInfo {

    private String userName;

    private String phone;

    private String address;

    private String school;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
