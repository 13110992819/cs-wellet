package com.cdkj.coin.wallet.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.wallet.ao.ISmsOutAO;
import com.cdkj.coin.wallet.bo.ISmsOutBO;
import com.cdkj.coin.wallet.bo.IUserBO;
import com.cdkj.coin.wallet.common.PhoneUtil;
import com.cdkj.coin.wallet.domain.User;
import com.cdkj.coin.wallet.enums.ECaptchaType;
import com.cdkj.coin.wallet.enums.EUserKind;
import com.cdkj.coin.wallet.exception.BizException;

@Service
public class SmsOutAOImpl implements ISmsOutAO {

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IUserBO userBO;

    @Override
    public void sendSmsCaptcha(String mobile, String bizType,
            String companyCode, String systemCode) {
        if (ECaptchaType.C_REG.getCode().equals(bizType)) {
            userBO.isMobileExist(mobile, EUserKind.Customer.getCode(),
                companyCode, systemCode);
        }
        smsOutBO.sendSmsCaptcha(mobile, bizType, companyCode, systemCode);
    }

    @Override
    public void sendEmailCaptcha(String email, String bizType,
            String companyCode, String systemCode) {
        if ("805081".equals(bizType)) {
            User condition = new User();
            condition.setEmail(email);
            if (userBO.getTotalCount(condition) > 0) {
                throw new BizException("xn000000", "邮箱已经被使用");
            }
        }
        smsOutBO.sendEmailCaptcha(email, bizType, companyCode, systemCode);
    }

    @Override
    public void sendContent(String tokenId, String userId, String content) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn0000", "该用户编号不存在");
        }
        smsOutBO.sendSmsOut(user.getMobile(), content, user.getCompanyCode(),
            user.getSystemCode());
    }

    @Override
    public void sendContent(String tokenId, String mobile, String content,
            String companyCode, String systemCode) {
        PhoneUtil.checkMobile(mobile);
        smsOutBO.sendSmsOut(mobile, content, companyCode, systemCode);
    }
}
