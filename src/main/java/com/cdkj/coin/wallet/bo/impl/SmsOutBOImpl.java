package com.cdkj.coin.wallet.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.ISmsOutBO;
import com.cdkj.coin.wallet.dto.req.XN804080Req;
import com.cdkj.coin.wallet.dto.req.XN804081Req;
import com.cdkj.coin.wallet.dto.req.XN804082Req;
import com.cdkj.coin.wallet.dto.req.XN804083Req;
import com.cdkj.coin.wallet.dto.res.BooleanRes;
import com.cdkj.coin.wallet.dto.res.PKCodeRes;
import com.cdkj.coin.wallet.http.BizConnecter;
import com.cdkj.coin.wallet.http.JsonUtils;

/** 
 * @author: xieyj 
 * @since: 2016年5月25日 上午8:15:46 
 * @history:
 */
@Component
public class SmsOutBOImpl implements ISmsOutBO {
    static Logger logger = Logger.getLogger(SmsOutBOImpl.class);

    @Override
    public void sendSmsCaptcha(String mobile, String bizType,
            String companyCode, String systemCode) {
        try {
            XN804081Req req = new XN804081Req();
            req.setMobile(mobile);
            req.setBizType(bizType);
            req.setCompanyCode(companyCode);
            req.setSystemCode(systemCode);
            BizConnecter.getBizData("804081", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }

    @Override
    public void sendEmailCaptcha(String email, String bizType,
            String companyCode, String systemCode) {
        try {
            XN804083Req req = new XN804083Req();
            req.setEmail(email);
            req.setBizType(bizType);
            req.setCompanyCode(companyCode);
            req.setSystemCode(systemCode);
            BizConnecter.getBizData("804083", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用邮件发送验证码异常");
        }
    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType,
            String systemCode) {
        XN804082Req req = new XN804082Req();
        req.setMobile(mobile);
        req.setCaptcha(captcha);
        req.setBizType(bizType);
        req.setSystemCode(systemCode);
        BizConnecter.getBizData("804082", JsonUtils.object2Json(req),
            BooleanRes.class);
    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType,
            String companyCode, String systemCode) {
        XN804082Req req = new XN804082Req();
        req.setMobile(mobile);
        req.setCaptcha(captcha);
        req.setBizType(bizType);
        req.setCompanyCode(companyCode);
        req.setSystemCode(systemCode);
        BizConnecter.getBizData("804082", JsonUtils.object2Json(req),
            BooleanRes.class);
    }

    @Override
    public void sendSmsOut(String mobile, String content, String companyCode,
            String systemCode) {
        try {
            XN804080Req req = new XN804080Req();
            req.setMobile(mobile);
            req.setContent(content);
            req.setType("M");
            req.setCompanyCode(companyCode);
            req.setSystemCode(systemCode);
            BizConnecter.getBizData("804080", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }
}
