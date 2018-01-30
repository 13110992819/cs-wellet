package com.cdkj.coin.wallet.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.IIdentifyBO;
import com.cdkj.coin.wallet.dto.req.XN798001Req;
import com.cdkj.coin.wallet.dto.req.XN798006Req;
import com.cdkj.coin.wallet.dto.req.XN798011Req;
import com.cdkj.coin.wallet.dto.req.XN798012Req;
import com.cdkj.coin.wallet.dto.req.XN798013Req;
import com.cdkj.coin.wallet.dto.req.XN798014Req;
import com.cdkj.coin.wallet.dto.res.XN798001Res;
import com.cdkj.coin.wallet.dto.res.XN798006Res;
import com.cdkj.coin.wallet.dto.res.XN798011Res;
import com.cdkj.coin.wallet.dto.res.XN798012Res;
import com.cdkj.coin.wallet.dto.res.XN798013Res;
import com.cdkj.coin.wallet.dto.res.XN798014Res;
import com.cdkj.coin.wallet.http.BizConnecter;
import com.cdkj.coin.wallet.http.JsonUtils;

@Component
public class IdentifyBOImpl implements IIdentifyBO {
    static Logger logger = Logger.getLogger(IdentifyBOImpl.class);

    @Override
    public void doTwoIdentify(String systemCode, String companyCode,
            String userId, String realName, String idKind, String idNo) {
        if (StringUtils.isNotBlank(realName)) {
            // try {
            XN798001Req req = new XN798001Req();
            req.setSystemCode(systemCode);
            req.setCompanyCode(companyCode);
            req.setUserId(userId);
            req.setRealName(realName);
            req.setIdKind(idKind);
            req.setIdNo(idNo);
            req.setRemark("二要素实名认证");
            BizConnecter.getBizData("798001", JsonUtils.object2Json(req),
                XN798001Res.class);
            // } catch (Exception e) {
            // logger.error("调用实名认证服务异常");
            // }
        }
    }

    /** 
     * @see com.std.user.bo.IIdentifyBO#doFourIdentify(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doFourIdentify(String userId, String realName, String idKind,
            String idNo, String cardNo, String bindMobile) {
        if (StringUtils.isNotBlank(realName)) {
            // try {
            XN798006Req req = new XN798006Req();
            req.setSystemId("3");
            req.setUserId(userId);
            req.setRealName(realName);
            req.setIdKind(idKind);
            req.setIdNo(idNo);
            req.setCardNo(cardNo);
            req.setBindMobile(bindMobile);
            req.setRemark("来自正汇钱包实名认证");
            BizConnecter.getBizData("798006", JsonUtils.object2Json(req),
                XN798006Res.class);
            // } catch (Exception e) {
            // logger.error("调用实名认证服务异常");
            // }
        }

    }

    @Override
    public XN798011Res doAlipayZhimaVerify(String systemCode,
            String companyCode, String userId, String idKind, String idNo,
            String realName) {
        XN798011Req req = new XN798011Req();
        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        req.setUserId(userId);
        req.setRealName(realName);
        req.setIdKind(idKind);
        req.setIdNo(idNo);
        req.setRemark("芝麻人脸识别认证");
        return BizConnecter.getBizData("798011", JsonUtils.object2Json(req),
            XN798011Res.class);
    }

    @Override
    public XN798012Res doAlipayZhimaQuery(String systemCode,
            String companyCode, String bizNo) {
        XN798012Req req = new XN798012Req();
        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        req.setBizNo(bizNo);
        return BizConnecter.getBizData("798012", JsonUtils.object2Json(req),
            XN798012Res.class);
    }

    @Override
    public XN798013Res doZhimaVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String returnUrl, String localCheck, String remark) {
        XN798013Req req = new XN798013Req();
        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        req.setUserId(userId);
        req.setIdKind(idKind);
        req.setIdNo(idNo);
        req.setRealName(realName);
        req.setReturnUrl(returnUrl);
        req.setLocalCheck(localCheck);
        req.setRemark(remark);
        return BizConnecter.getBizData("798013", JsonUtils.object2Json(req),
            XN798013Res.class);
    }

    @Override
    public XN798014Res doZhimaQuery(String systemCode, String companyCode,
            String bizNo) {
        XN798014Req req = new XN798014Req();
        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        req.setBizNo(bizNo);
        return BizConnecter.getBizData("798014", JsonUtils.object2Json(req),
            XN798014Res.class);
    }
}
