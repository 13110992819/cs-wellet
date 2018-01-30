/**
 * @Title ITencentBO.java 
 * @Package com.cdkj.coin.bo 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月16日 下午4:36:01 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.bo;

import com.cdkj.coin.wallet.dto.res.XN625000Res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月16日 下午4:36:01 
 * @history:
 */
public interface ITencentBO {

    // 腾讯云注册
    public void register(String userId, String nickname, String companyCode,
            String systemCode);

    // 获取签名信息
    public XN625000Res getSign(String userId, String companyCode,
            String systemCode);

    // 创建群组
    public void createGroup(String groupId, String buyUser, String sellUser);

    // 向群组中发送系统消息
    public void sendMessage(String groupId, String content);

    // 向群组中发送普通消息
    public void sendNormalMessage(String groupId, String content);

    // 设置用户昵称
    public void setNickname(String userId, String nickname);

}
