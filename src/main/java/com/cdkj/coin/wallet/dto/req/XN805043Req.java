package com.cdkj.coin.wallet.dto.req;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class XN805043Req {

    @NotBlank
    private String mobile; // 手机号

    @NotBlank
    @Length(min = 2, max = 10, message = "昵称长度必须在2-10之间")
    private String nickname; // 昵称

    @NotBlank
    private String loginPwd; // 登录密码、

    @NotBlank
    private String realName; // 真实姓名

    @NotBlank
    private String idNo; // 身份证号

    private String userRefereeKind; // 推荐人类型(选填)

    private String userReferee; // 推荐人(选填)

    private String inviteCode; // 推荐人编号，AES加密后的数据(选填)

    @NotBlank
    private String kind; // 用户类型

    private String province; // 省(选填)

    private String city; // 市(选填)

    private String area; // 区(选填)

    private String address; // 详细地址(选填)

    @NotBlank
    private String companyCode; // 公司编号(必填)

    @NotBlank
    private String systemCode; // 系统编号(必填)

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getUserRefereeKind() {
        return userRefereeKind;
    }

    public void setUserRefereeKind(String userRefereeKind) {
        this.userRefereeKind = userRefereeKind;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
}
