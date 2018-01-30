/**
 * @Title IUserDAO.java 
 * @Package com.ibis.pz 
 * @Description 
 * @author miyb  
 * @date 2015-2-6 上午10:22:02 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dao;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.domain.User;

/** 
 * @author: miyb 
 * @since: 2015-2-6 上午10:22:02 
 * @history:
 */
public interface IUserDAO extends IBaseDAO<User> {
    String NAMESPACE = IUserDAO.class.getName().concat(".");

    // 更改姓名
    public int updateRealName(User data);

    // 实名认证
    public int updateIdentity(User data);

    // 设置支付密码
    public int updateTradePwd(User data);

    // 设置登录密码
    public int updateLoginPwd(User data);

    // 更新手机号
    public int updateMobile(User data);

    // 绑定手机号
    public int updateBindMobile(User data);

    // 更新状态
    public int updateStatus(User data);

    // 更新角色
    public int updateRole(User data);

    // 更新Pdf
    public int updatePdf(User data);

    // 更新用户名
    public int updateLoginName(User data);

    // 更新昵称
    public int updateNickname(User data);

    // 更新头像
    public int updatePhoto(User data);

    // 更新公司编号
    public int updateCompany(User data);

    public int update(User data);

    public int updateLevel(User data);

    // 更新用户手机号和真实信息
    public int updateSupple(User data);

    // 微信登录更新用户信息
    public int updateWxInfo(User data);

    public int updateWxOpenId(User data);

    public int approveUser(User data);

    public int updateDivRate(User data);

    public int updateTradeRate(User data);

    public int updateEmail(User data);

    public int updateRemark(User data);

    public int updateLastLogin(User data);

    public int updateGoogleSecret(User data);

    // 修改推荐人
    public int updateUserReferee(User data);
}
