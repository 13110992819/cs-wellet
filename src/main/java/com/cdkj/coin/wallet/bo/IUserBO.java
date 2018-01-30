/**
 * @Title IUserBO.java 
 * @Package com.ibis.pz 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午9:17:37 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.bo;

import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.User;
import com.cdkj.coin.wallet.dto.req.XN805042Req;
import com.cdkj.coin.wallet.dto.req.XN805043Req;
import com.cdkj.coin.wallet.enums.EUserStatus;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午9:17:37 
 * @history:
 */
public interface IUserBO extends IPaginableBO<User> {

    //
    public User doGetUserByOpenId(String appOpenId, String h5OpenId,
            String companyCode, String systemCode);

    // 根据手机号和类型判断手机号是否存在
    public void isMobileExist(String mobile, String kind, String companyCode,
            String systemCode);

    // 判断昵称是否存在
    public void isNicknameExist(String nickname, String kind,
            String companyCode, String systemCode);

    // 判断登录名是否存在
    public void isLoginNameExist(String loginName, String kind,
            String companyCode, String systemCode);

    public String getUserId(String mobile, String kind, String companyCode,
            String systemCode);

    public User getUser(String mobile, String kind, String companyCode,
            String systemCode);

    // 查询openId
    public void doCheckOpenId(String unionId, String h5OpenId,
            String appOpenId, String companyCode, String systemCode);

    // 前端用户注册
    public String doRegister(String mobile, String nickname, String loginPwd,
            User refereeUser, String kind, String province, String city,
            String area, String address, Double divRate1, Double divRate2,
            Double tradeRate, String companyCode, String systemCode);

    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String kind, String loginPwd, String nickname,
            String photo, String gender, String userReferee,
            String companyCode, String systemCode);

    public void refreshWxInfo(String userId, String type, String unionId,
            String openId, String nickname, String photo, String gender);

    public String doAddUser(XN805042Req req);

    public String doApplyRegUser(XN805043Req req, String roleCode);

    public String saveUser(String mobile, String kind, String companyCode,
            String systemCode);

    // 判断用户编号是否存在
    public boolean isUserExist(String userId, String systemCode);

    // 验证支付密码:拿tradePwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkTradePwd(String userId, String tradePwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd, String alertStr);

    // 校验是否已经有人实名认证
    public void checkIdentify(String kind, String idKind, String idNo,
            String realName);

    // 判断推荐人是否存在(手机号)
    public void checkUserReferee(String userReferee, String systemCode);

    public int refreshIdentity(String userId, String realName, String idKind,
            String idNo);

    public int refreshRealName(String userId, String realName);

    public int refreshLoginPwd(String userId, String loginPwd);

    public int refreshTradePwd(String userId, String tradePwd);

    public int refreshMobile(String userId, String mobile);

    public int refreshBindMobile(String userId, String loginName,
            String mobile, String loginPwd, String loginPwdStrength);

    public User getUser(String userId);

    public List<User> getUsersByUserReferee(String userReferee);

    public User getUserByLoginName(String loginName, String systemCode);

    public List<User> queryUserList(User condition);

    public void refreshStatus(String userId, EUserStatus normal,
            String updater, String remark);

    public void refreshRole(String userId, String roleCode, String updater,
            String remark);

    public void refreshPdf(String userId, String pdf, String updater,
            String remark);

    public void refreshLoginName(String userId, String loginName);

    public void refreshNickname(String userId, String nickname);

    public void refreshPhoto(String userId, String photo);

    public void refreshCompany(String userId, String companyCode);

    public void refreshUser(User data);

    public void refreshUserSupple(User data);

    public void refreshLevel(User data);

    public List<User> queryUserList(String mobile, String kind,
            String systemCode);

    public void approveUser(String userId, String approver, String status,
            Double divRate, String remark);

    public int refreshDivRate(String userId, Double divRate1, Double divRate2);

    public int refreshTradeRate(String userId, Double tradeRate);

    public Long totalUser(User condition);

    public void refreshEmail(String userId, String email);

    public void refreshRemark(String userId, String remark);

    public void refreshLastLogin(String userId);

    public void refreshGoogleSecret(String userId, String secret);

    public void refreshUserReferee(String userId, String userReferee,
            String userRefereeLevel);
}
