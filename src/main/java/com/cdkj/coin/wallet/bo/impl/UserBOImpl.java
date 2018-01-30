/**
 * @Title UserBOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午9:21:25 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.IUserBO;
import com.cdkj.coin.wallet.bo.base.PaginableBOImpl;
import com.cdkj.coin.wallet.common.MD5Util;
import com.cdkj.coin.wallet.common.PhoneUtil;
import com.cdkj.coin.wallet.common.PwdUtil;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.dao.IUserDAO;
import com.cdkj.coin.wallet.domain.User;
import com.cdkj.coin.wallet.dto.req.XN805042Req;
import com.cdkj.coin.wallet.dto.req.XN805043Req;
import com.cdkj.coin.wallet.enums.EUserLevel;
import com.cdkj.coin.wallet.enums.EUserPwd;
import com.cdkj.coin.wallet.enums.EUserStatus;
import com.cdkj.coin.wallet.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午9:21:25 
 * @history:
 */
@Component
public class UserBOImpl extends PaginableBOImpl<User> implements IUserBO {
    @Autowired
    private IUserDAO userDAO;

    @Override
    public User doGetUserByOpenId(String appOpenId, String h5OpenId,
            String companyCode, String systemCode) {
        User condition = new User();
        condition.setAppOpenId(appOpenId);
        condition.setH5OpenId(h5OpenId);
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        List<User> userList = userDAO.selectList(condition);
        User user = null;
        if (CollectionUtils.isNotEmpty(userList)) {
            user = userList.get(0);
            if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
                throw new BizException("10002", "用户被锁定");
            }
        }
        return user;
    }

    /** 
     * @see com.std.user.bo.IUserBO#isMobileExist(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void isMobileExist(String mobile, String kind, String companyCode,
            String systemCode) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind(kind);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    @Override
    public void isNicknameExist(String nickname, String kind,
            String companyCode, String systemCode) {
        if (StringUtils.isNotBlank(nickname)) {
            // 判断格式
            User condition = new User();
            condition.setNickname(nickname);
            condition.setKind(kind);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "昵称已经被使用");
            }
        }
    }

    // @Override
    // public User getUserByMobile(String mobile, String kind, String
    // companyCode,
    // String systemCode) {
    // User data = null;
    // if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(kind)) {
    // User condition = new User();
    // condition.setMobile(mobile);
    // condition.setKind(kind);
    // condition.setCompanyCode(companyCode);
    // condition.setSystemCode(systemCode);
    // List<User> list = userDAO.selectList(condition);
    // if (CollectionUtils.isNotEmpty(list)) {
    // data = list.get(0);
    // }
    // if (null == data) {
    // throw new BizException("xn702002", "推荐人不存在");
    // }
    // }
    // return data;
    // }

    @Override
    public String getUserId(String mobile, String kind, String companyCode,
            String systemCode) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(kind)) {
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind(kind);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            List<User> list = userDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                User data = list.get(0);
                userId = data.getUserId();
            } else
                throw new BizException("xn702002", "手机号[" + mobile + "]用户不存在");
        }
        return userId;
    }

    @Override
    public User getUser(String mobile, String kind, String companyCode,
            String systemCode) {
        User user = null;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(kind)) {
            User condition = new User();
            condition.setMobile(mobile);
            condition.setKind(kind);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            List<User> list = userDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                user = list.get(0);
            }
        }
        return user;
    }

    @Override
    public String doRegister(String mobile, String nickname, String loginPwd,
            User refereeUser, String kind, String province, String city,
            String area, String address, Double divRate1, Double divRate2,
            Double tradeRate, String companyCode, String systemCode) {
        String userId = OrderNoGenerater.generate("U");
        User user = new User();
        user.setUserId(userId);
        user.setLoginName(mobile);
        user.setMobile(mobile);
        user.setKind(kind);

        user.setLoginPwd(MD5Util.md5(loginPwd));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        user.setNickname(nickname);
        if (refereeUser != null) {
            user.setUserReferee(refereeUser.getUserId());
            user.setUserRefereeLevel(refereeUser.getLevel());
        }
        user.setLevel(EUserLevel.ONE.getCode());
        user.setStatus(EUserStatus.NORMAL.getCode());
        user.setProvince(province);
        user.setCity(city);
        user.setArea(area);
        user.setAddress(address);
        user.setDivRate1(divRate1);
        user.setDivRate2(divRate2);
        user.setTradeRate(tradeRate);
        Date date = new Date();
        user.setCreateDatetime(date);

        user.setCompanyCode(companyCode);
        user.setSystemCode(systemCode);
        userDAO.insert(user);
        return userId;
    }

    @Override
    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String kind, String loginPwd, String nickname,
            String photo, String gender, String userReferee,
            String companyCode, String systemCode) {
        String userId = OrderNoGenerater.generate("U");
        User user = new User();
        user.setUserId(userId);
        user.setUnionId(unionId);
        user.setH5OpenId(h5OpenId);
        user.setAppOpenId(appOpenId);

        user.setLoginName(mobile);
        user.setMobile(mobile);
        user.setKind(kind);
        user.setLoginPwd(MD5Util.md5(loginPwd));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));

        user.setNickname(nickname);
        user.setPhoto(photo);
        user.setGender(gender);
        user.setUserReferee(userReferee);
        user.setLevel(EUserLevel.ONE.getCode());
        user.setStatus(EUserStatus.NORMAL.getCode());

        user.setCreateDatetime(new Date());
        user.setCompanyCode(companyCode);
        user.setSystemCode(systemCode);
        userDAO.insert(user);
        return userId;
    }

    @Override
    public String doAddUser(XN805042Req req) {
        String userId = OrderNoGenerater.generate("U");
        User user = new User();
        user.setUserId(userId);
        user.setLoginName(req.getLoginName());
        user.setMobile(req.getMobile());
        user.setNickname(userId.substring(userId.length() - 8, userId.length()));

        if (StringUtils.isBlank(req.getLoginPwd())) {
            req.setLoginPwd(EUserPwd.InitPwd.getCode());
        }
        user.setLoginPwd(MD5Util.md5(req.getLoginPwd()));
        user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(req
            .getLoginPwd()));

        user.setKind(req.getKind());
        user.setLevel(EUserLevel.ONE.getCode());
        user.setUserReferee(req.getUserReferee());

        user.setIdKind(req.getIdKind());
        user.setIdNo(req.getIdNo());
        user.setRealName(req.getRealName());

        user.setRoleCode(req.getRoleCode());

        if (StringUtils.isBlank(req.getDivRate())) {
            req.setDivRate("0");
        }
        user.setDivRate1(Double.valueOf(req.getDivRate()));
        user.setStatus(EUserStatus.NORMAL.getCode());
        user.setPdf(req.getPdf());

        user.setProvince(req.getProvince());
        user.setCity(req.getCity());
        user.setArea(req.getArea());
        user.setLatitude(req.getLatitude());
        user.setLongitude(req.getLongitude());

        Date date = new Date();
        user.setCreateDatetime(date);
        user.setUpdater(req.getUpdater());
        user.setUpdateDatetime(date);
        user.setRemark(req.getRemark());

        user.setCompanyCode(req.getCompanyCode());
        user.setSystemCode(req.getSystemCode());
        userDAO.insert(user);
        return userId;
    }

    @Override
    public String doApplyRegUser(XN805043Req req, String roleCode) {
        String userId = OrderNoGenerater.generate("U");
        // User user = new User();
        // user.setUserId(userId);
        // user.setLoginName(req.getLoginName());
        // user.setMobile(req.getMobile());
        // user.setNickname(userId.substring(userId.length() - 8,
        // userId.length()));
        //
        // user.setLoginPwd(MD5Util.md5(req.getLoginPwd()));
        // user.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(req
        // .getLoginPwd()));
        // user.setKind(req.getKind());
        // user.setLevel(EUserLevel.ONE.getCode());
        // user.setUserReferee(req.getUserReferee());
        //
        // user.setIdKind(req.getIdKind());
        // user.setIdNo(req.getIdNo());
        // user.setRealName(req.getRealName());
        //
        // user.setRoleCode(roleCode);
        //
        // user.setDivRate1(Double.valueOf(req.getDivRate()));
        // user.setStatus(EUserStatus.TO_APPROVE.getCode());
        // user.setPdf(req.getPdf());
        //
        // user.setProvince(req.getProvince());
        // user.setCity(req.getCity());
        // user.setArea(req.getArea());
        // user.setLatitude(req.getLatitude());
        // user.setLongitude(req.getLongitude());
        //
        // Date date = new Date();
        // user.setCreateDatetime(date);
        // user.setUpdater(req.getUpdater());
        // user.setUpdateDatetime(date);
        // user.setRemark(req.getRemark());
        //
        // user.setCompanyCode(req.getCompanyCode());
        // user.setSystemCode(req.getSystemCode());
        // userDAO.insert(user);
        return userId;
    }

    @Override
    public String saveUser(String mobile, String kind, String companyCode,
            String systemCode) {
        String userId = null;
        if (StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(kind)) {
            userId = OrderNoGenerater.generate("U");
            User data = new User();
            data.setUserId(userId);
            data.setMobile(mobile);
            data.setKind(kind);
            data.setCompanyCode(companyCode);
            data.setSystemCode(systemCode);
            userDAO.insert(data);
        }
        return userId;
    }

    /**
     * @see com.ibis.pz.user.IUserBO#refreshIdentity(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int refreshIdentity(String userId, String realName, String idKind,
            String idNo) {
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);
        data.setIdKind(idKind);
        data.setIdNo(idNo);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateIdentity(data);
        }
        return count;
    }

    @Override
    public int refreshRealName(String userId, String realName) {
        User data = new User();
        data.setUserId(userId);
        data.setRealName(realName);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateRealName(data);
        }
        return count;
    }

    @Override
    public int refreshDivRate(String userId, Double divRate1, Double divRate2) {
        User data = new User();
        data.setUserId(userId);
        data.setDivRate1(divRate1);
        data.setDivRate2(divRate2);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateDivRate(data);
        }
        return count;
    }

    @Override
    public int refreshTradeRate(String userId, Double tradeRate) {
        User data = new User();
        data.setUserId(userId);
        data.setTradeRate(tradeRate);
        int count = 0;
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateTradeRate(data);
        }
        return count;
    }

    @Override
    public int refreshTradePwd(String userId, String tradePwd) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setTradePwd(MD5Util.md5(tradePwd));
            data.setTradePwdStrength(PwdUtil.calculateSecurityLevel(tradePwd));
            count = userDAO.updateTradePwd(data);
        }
        return count;
    }

    @Override
    public User getUser(String userId) {
        User data = null;
        if (StringUtils.isNotBlank(userId)) {
            User condition = new User();
            condition.setUserId(userId);
            data = userDAO.select(condition);
        }
        return data;
    }

    @Override
    public List<User> getUsersByUserReferee(String userReferee) {
        List<User> userList = new ArrayList<User>();
        if (StringUtils.isNotBlank(userReferee)) {
            User condition = new User();
            condition.setUserReferee(userReferee);
            userList = userDAO.selectList(condition);
        }
        return userList;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#getUserByMobile(java.lang.String)
     */
    @Override
    public User getUserByLoginName(String loginName, String systemCode) {
        User data = null;
        if (StringUtils.isNotBlank(loginName)) {
            User condition = new User();
            condition.setLoginName(loginName);
            List<User> list = userDAO.selectList(condition);
            if (list != null && list.size() > 1) {
                throw new BizException("li01006", "登录名重复");
            }
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    /** 
     * @see com.ibis.pz.user.IUserBO#queryUserList(com.User.pz.domain.UserDO)
     */
    @Override
    public List<User> queryUserList(User data) {
        return userDAO.selectList(data);
    }

    @Override
    public int refreshLoginPwd(String userId, String loginPwd) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setLoginPwd(MD5Util.md5(loginPwd));
            data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
            count = userDAO.updateLoginPwd(data);
        }
        return count;
    }

    @Override
    public int refreshMobile(String userId, String mobile) {
        int count = 0;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(mobile)) {
            User data = new User();
            data.setUserId(userId);
            data.setMobile(mobile);
            count = userDAO.updateMobile(data);
        }
        return count;
    }

    @Override
    public int refreshBindMobile(String userId, String loginName,
            String mobile, String loginPwd, String loginPwdStrength) {
        int count = 0;
        User data = new User();
        data.setUserId(userId);
        data.setLoginName(loginName);
        data.setMobile(mobile);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(loginPwdStrength);
        if (data != null && StringUtils.isNotBlank(data.getUserId())) {
            count = userDAO.updateBindMobile(data);
        }
        return count;
    }

    @Override
    public void isLoginNameExist(String loginName, String kind,
            String companyCode, String systemCode) {
        if (StringUtils.isNotBlank(loginName)) {
            // 判断格式
            User condition = new User();
            condition.setLoginName(loginName);
            condition.setKind(kind);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "登录名已经存在");
            }
        }
    }

    @Override
    public boolean isUserExist(String userId, String systemCode) {
        User condition = new User();
        condition.setUserId(userId);
        condition.setSystemCode(systemCode);
        if (userDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    /** 
     * @see com.std.user.bo.IUserBO#checkUserReferee(java.lang.String)
     */
    @Override
    public void checkUserReferee(String userReferee, String systemCode) {
        if (StringUtils.isNotBlank(userReferee)) {
            // 判断格式
            User condition = new User();
            condition.setUserId(userReferee);
            long count = getTotalCount(condition);
            if (count <= 0) {
                throw new BizException("li01003", "推荐人不存在");
            }
        }
    }

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(tradePwd)) {
            User user = this.getUser(userId);
            if (StringUtils.isBlank(user.getTradePwdStrength())) {
                throw new BizException("jd00001", "请您先设置资金密码！");
            }
            User condition = new User();
            condition.setUserId(userId);
            condition.setTradePwd(MD5Util.md5(tradePwd));
            if (this.getTotalCount(condition) != 1) {
                throw new BizException("jd00001", "资金密码错误");
            }
        } else {
            throw new BizException("jd00001", "资金密码错误");
        }
    }

    @Override
    public void checkLoginPwd(String userId, String loginPwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(loginPwd)) {
            User condition = new User();
            condition.setUserId(userId);
            condition.setLoginPwd(MD5Util.md5(loginPwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", "原登录密码错误");
            }
        } else {
            throw new BizException("jd00001", "原登录密码错误");
        }
    }

    @Override
    public void checkLoginPwd(String userId, String loginPwd, String alertStr) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(loginPwd)) {
            User condition = new User();
            condition.setUserId(userId);
            condition.setLoginPwd(MD5Util.md5(loginPwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", alertStr + "错误");
            }
        } else {
            throw new BizException("jd00001", alertStr + "错误");
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#checkIdentify(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void checkIdentify(String kind, String idKind, String idNo,
            String realName) {
        User condition = new User();
        condition.setKind(kind);
        condition.setIdKind(idKind);
        condition.setIdNo(idNo);
        condition.setRealName(realName);
        List<User> userList = userDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(userList)) {
            User data = userList.get(0);
            throw new BizException("xn000001", "用户[" + data.getMobile()
                    + "]已使用该身份信息，请重新填写");
        }
    }

    @Override
    public void refreshStatus(String userId, EUserStatus status,
            String updater, String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setStatus(status.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updateStatus(data);
        }
    }

    @Override
    public void refreshRole(String userId, String roleCode, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setRoleCode(roleCode);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updateRole(data);
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#refreshStatus(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void refreshPdf(String userId, String pdf, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setPdf(pdf);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            userDAO.updatePdf(data);
        }
    }

    @Override
    public void refreshLoginName(String userId, String loginName) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setLoginName(loginName);
            userDAO.updateLoginName(data);
        }
    }

    @Override
    public void refreshNickname(String userId, String nickname) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setNickname(nickname);
            userDAO.updateNickname(data);
        }
    }

    @Override
    public void refreshPhoto(String userId, String photo) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setPhoto(photo);
            userDAO.updatePhoto(data);
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#refreshUser(com.std.user.domain.User)
     */
    @Override
    public void refreshUser(User data) {
        if (data != null) {
            userDAO.update(data);
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#refreshUserSupple(com.std.user.domain.User)
     */
    @Override
    public void refreshUserSupple(User data) {
        if (null != data) {
            userDAO.updateSupple(data);
        }
    }

    /** 
     * @see com.std.user.bo.IUserBO#refreshCompany(java.lang.String, java.lang.String)
     */
    @Override
    public void refreshCompany(String userId, String companyCode) {
        User data = new User();
        data.setUserId(userId);
        data.setCompanyCode(companyCode);
        userDAO.updateCompany(data);
    }

    /** 
     * @see com.std.user.bo.IUserBO#refreshLevel(com.std.user.domain.User)
     */
    @Override
    public void refreshLevel(User data) {
        userDAO.updateLevel(data);
    }

    @Override
    public void refreshWxInfo(String userId, String unionId, String h5OpenId,
            String appOpenId, String nickname, String photo, String gender) {
        User dbUser = getUser(userId);
        dbUser.setUnionId(unionId);
        if (StringUtils.isNotBlank(h5OpenId)) {
            dbUser.setH5OpenId(h5OpenId);
        }
        if (StringUtils.isNotBlank(appOpenId)) {
            dbUser.setAppOpenId(appOpenId);
        }
        dbUser.setNickname(nickname);
        dbUser.setPhoto(photo);
        dbUser.setGender(gender);
        userDAO.updateWxInfo(dbUser);
    }

    /** 
     * @see com.std.user.bo.IUserBO#queryUserList(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<User> queryUserList(String mobile, String kind,
            String systemCode) {
        User condition = new User();
        condition.setMobile(mobile);
        condition.setKind(kind);
        condition.setSystemCode(systemCode);
        return userDAO.selectList(condition);
    }

    @Override
    public void approveUser(String userId, String approver, String status,
            Double divRate, String remark) {
        User user = new User();
        user.setUserId(userId);
        user.setDivRate1(divRate);
        user.setStatus(status);
        user.setUpdater(approver);
        user.setUpdateDatetime(new Date());
        user.setRemark(remark);
        userDAO.approveUser(user);
    }

    public Long totalUser(User condition) {
        return userDAO.selectTotalCount(condition);
    }

    /** 
     * @see com.std.user.bo.IUserBO#doCheckOpenId(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doCheckOpenId(String unionId, String h5OpenId,
            String appOpenId, String companyCode, String systemCode) {
        User condition = new User();
        condition.setUnionId(unionId);
        condition.setH5OpenId(h5OpenId);
        condition.setAppOpenId(appOpenId);
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        long count = getTotalCount(condition);
        if (count > 0) {
            throw new BizException("xn702002", "微信编号已存在");
        }
    }

    @Override
    public void refreshEmail(String userId, String email) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setEmail(email);
            userDAO.updateEmail(data);
        }
    }

    @Override
    public void refreshRemark(String userId, String remark) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setRemark(remark);
            userDAO.updateRemark(data);
        }
    }

    @Override
    public void refreshLastLogin(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setLastLogin(new Date());
            userDAO.updateLastLogin(data);
        }
    }

    @Override
    public void refreshGoogleSecret(String userId, String secret) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setGoogleSecret(secret);
            userDAO.updateGoogleSecret(data);
        }
    }

    @Override
    public void refreshUserReferee(String userId, String userReferee,
            String userRefereeLevel) {
        if (StringUtils.isNotBlank(userId)) {
            User data = new User();
            data.setUserId(userId);
            data.setUserReferee(userReferee);
            data.setUserRefereeLevel(userRefereeLevel);
            userDAO.updateUserReferee(data);
        }
    }

}
