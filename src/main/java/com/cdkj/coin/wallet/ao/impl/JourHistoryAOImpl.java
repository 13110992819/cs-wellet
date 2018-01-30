package com.cdkj.coin.wallet.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.wallet.ao.IJourHistoryAO;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.IHLOrderBO;
import com.cdkj.coin.wallet.bo.IJourHistoryBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.common.DateUtil;
import com.cdkj.coin.wallet.domain.Jour;
import com.cdkj.coin.wallet.exception.BizException;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:16:58 
 * @history:
 */
@Service
public class JourHistoryAOImpl implements IJourHistoryAO {

    @Autowired
    private IJourHistoryBO jourHistoryBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IHLOrderBO hlOrderBO;

    @Override
    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition) {
        String bizType = condition.getBizType();
        if (StringUtils.isNotBlank(bizType)) {
            String[] bizTypeArrs = bizType.split(",");
            List<String> bizTypeList = new ArrayList<String>();
            for (int i = 0; i < bizTypeArrs.length; i++) {
                bizTypeList.add(bizTypeArrs[i]);
            }
            condition.setBizType(null);
            condition.setBizTypeList(bizTypeList);
        }
        return jourHistoryBO.getPaginable(start, limit, condition);
    }

    @Override
    public Paginable<Jour> queryFrontJourPage(int start, int limit,
            Jour condition) {
        if (DateUtil.daysBetween(condition.getCreateDatetimeStart(),
            condition.getCreateDatetimeEnd()) >= 7) {
            throw new BizException("xn702000", "请选择7天内查询时间");
        }
        String bizType = condition.getBizType();
        if (StringUtils.isNotBlank(bizType)) {
            String[] bizTypeArrs = bizType.split(",");
            List<String> bizTypeList = new ArrayList<String>();
            for (int i = 0; i < bizTypeArrs.length; i++) {
                bizTypeList.add(bizTypeArrs[i]);
            }
            condition.setBizType(null);
            condition.setBizTypeList(bizTypeList);
        }
        return jourHistoryBO.getPaginable(start, limit, condition);
    }

    @Override
    public Jour getJour(String code, String systemCode) {
        return jourHistoryBO.getJour(code, systemCode);
    }
}
