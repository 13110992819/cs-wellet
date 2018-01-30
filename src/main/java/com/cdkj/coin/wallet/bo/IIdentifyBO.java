package com.cdkj.coin.wallet.bo;

import com.cdkj.coin.wallet.dto.res.XN798011Res;
import com.cdkj.coin.wallet.dto.res.XN798012Res;
import com.cdkj.coin.wallet.dto.res.XN798013Res;
import com.cdkj.coin.wallet.dto.res.XN798014Res;

public interface IIdentifyBO {
    /**
     * 三方实名认证
     * @param userId
     * @param realName
     * @param idKind
     * @param idNo
     * @return 
     * @create: 2015年10月21日 下午6:12:32 myb858
     * @history:
     */
    public void doTwoIdentify(String systemCode, String companyCode,
            String userId, String realName, String idKind, String idNo);

    /**
     * 四要素三方实名认证
     * @param userId
     * @param realName
     * @param idKind
     * @param idNo
     * @param cardNo
     * @param bindMobile 
     * @create: 2017年1月4日 上午10:48:03 xieyj
     * @history:
     */
    public void doFourIdentify(String userId, String realName, String idKind,
            String idNo, String cardNo, String bindMobile);

    // 支付宝芝麻认证
    public XN798011Res doAlipayZhimaVerify(String systemCode,
            String companyCode, String userId, String idKind, String idNo,
            String realName);

    // 支付宝芝麻认证
    public XN798012Res doAlipayZhimaQuery(String systemCode,
            String companyCode, String bizNo);

    // 芝麻信用芝麻认证第一步，返回bizNo
    public XN798013Res doZhimaVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String returnUrl, String localCheck, String remark);

    // 芝麻信用芝麻认证查询
    public XN798014Res doZhimaQuery(String systemCode, String companyCode,
            String bizNo);

}
