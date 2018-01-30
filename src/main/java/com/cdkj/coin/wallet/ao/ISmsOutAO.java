package com.cdkj.coin.wallet.ao;

public interface ISmsOutAO {
    /**
     * 发送短信验证码
     * @param mobile
     * @param bizType
     * @param companyCode
     * @param systemCode 
     * @create: 2016年12月15日 上午7:28:43 xieyj
     * @history:
     */
    public void sendSmsCaptcha(String mobile, String bizType,
            String companyCode, String systemCode);

    public void sendEmailCaptcha(String email, String bizType,
            String companyCode, String systemCode);

    /**
     * 发送内容
     * @param tokenId
     * @param userId
     * @param content 
     * @create: 2016年11月10日 上午10:30:53 xieyj
     * @history:
     */
    public void sendContent(String tokenId, String userId, String content);

    public void sendContent(String tokenId, String mobile, String content,
            String companyCode, String systemCode);
}
