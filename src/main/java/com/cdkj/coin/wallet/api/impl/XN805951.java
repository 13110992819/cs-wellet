package com.cdkj.coin.wallet.api.impl;

import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.common.QnTokenImpl;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.dto.req.XN805951Req;
import com.cdkj.coin.wallet.dto.res.XN805951Res;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/**
 * 根据系统编号获取七牛uploadToken
 * @author: xieyj 
 * @since: 2016年10月11日 上午9:45:51 
 * @history:
 */
public class XN805951 extends AProcessor {
    private QnTokenImpl qnTokenImpl = SpringContextHolder
        .getBean(QnTokenImpl.class);

    private XN805951Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805951Res(qnTokenImpl.getUploadToken(req.getCompanyCode(),
            req.getSystemCode()));
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805951Req.class);
        StringValidater
            .validateBlank(req.getCompanyCode(), req.getSystemCode());
    }
}
