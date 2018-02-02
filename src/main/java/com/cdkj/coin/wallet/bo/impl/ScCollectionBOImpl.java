package com.cdkj.coin.wallet.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.IScCollectionBO;
import com.cdkj.coin.wallet.bo.base.PaginableBOImpl;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.dao.IScCollectionDAO;
import com.cdkj.coin.wallet.enums.EGeneratePrefix;
import com.cdkj.coin.wallet.enums.EScCollectionStatus;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.siacoin.ScCollection;

@Component
public class ScCollectionBOImpl extends PaginableBOImpl<ScCollection> implements
        IScCollectionBO {

    @Autowired
    private IScCollectionDAO scCollectionDAO;

    @Override
    public String saveScCollection(String to, BigDecimal value, String txHash,
            String refNo) {
        String code = null;
        ScCollection data = new ScCollection();
        code = OrderNoGenerater.generate(EGeneratePrefix.Collection.getCode());
        data.setCode(code);
        data.setToAddress(to);
        data.setAmount(value);
        data.setTxHash(txHash);
        data.setStatus(EScCollectionStatus.Broadcast.getCode());
        data.setCreateDatetime(new Date());
        data.setRefNo(refNo);
        scCollectionDAO.insert(data);
        return code;
    }

    @Override
    public List<ScCollection> queryScCollectionList(ScCollection condition) {
        return scCollectionDAO.selectList(condition);
    }

    @Override
    public ScCollection getScCollection(String code) {
        ScCollection data = null;
        if (StringUtils.isNotBlank(code)) {
            ScCollection condition = new ScCollection();
            condition.setCode(code);
            data = scCollectionDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "归集记录不存在");
            }
        }
        return data;
    }

    @Override
    public ScCollection getScCollectionByTxHash(String txHash) {
        ScCollection condition = new ScCollection();
        condition.setTxHash(txHash);
        List<ScCollection> results = scCollectionDAO.selectList(condition);
        if (CollectionUtils.isEmpty(results)) {
            throw new BizException("xn0000", "归集记录不存在");
        }
        return results.get(0);
    }

    @Override
    public ScCollection getScCollectionByRefNo(String refNo) {
        ScCollection scCollection = null;
        ScCollection condition = new ScCollection();
        condition.setRefNo(refNo);
        List<ScCollection> results = scCollectionDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            scCollection = results.get(0);
        }
        return scCollection;
    }

    @Override
    public int colectionNotice(ScCollection data, String fromAddress,
            BigDecimal txfee, Date scDatetime) {
        int count = 0;
        data.setFromAddress(fromAddress);
        data.setTxFee(txfee);
        data.setStatus(EScCollectionStatus.Broadcast_YES.getCode());
        data.setScDatetime(scDatetime);
        data.setUpdateDatetime(new Date());
        scCollectionDAO.updateNotice(data);
        return count;
    }

    @Override
    public ScAddress getAddressUseInfo(String toAddress) {
        ScCollection data = new ScCollection();
        data.setToAddress(toAddress);
        data.setStatus(EScCollectionStatus.Broadcast_YES.getCode());
        return scCollectionDAO.selectAddressUseInfo(data);
    }

    @Override
    public BigDecimal getTotalCollect() {
        return scCollectionDAO.selectTotalCollect();
    }

}
