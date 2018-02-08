package com.cdkj.coin.wallet.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.ICollectionBO;
import com.cdkj.coin.wallet.bo.base.PaginableBOImpl;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.dao.ICollectionDAO;
import com.cdkj.coin.wallet.domain.Collection;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.enums.ECollectionStatus;
import com.cdkj.coin.wallet.enums.EGeneratePrefix;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.exception.BizException;

@Component
public class CollectionBOImpl extends PaginableBOImpl<Collection> implements
        ICollectionBO {

    @Autowired
    private ICollectionDAO collectionDAO;

    @Override
    public String saveCollection(ECoin coin, String from, String to,
            BigDecimal value, String txHash, String refNo) {
        String code = null;
        Collection data = new Collection();
        code = OrderNoGenerater.generate(EGeneratePrefix.Collection.getCode());
        data.setCode(code);
        data.setCurrency(coin.getCode());
        data.setFromAddress(from);
        data.setToAddress(to);
        data.setAmount(value);
        data.setTxHash(txHash);
        data.setStatus(ECollectionStatus.Broadcast.getCode());
        data.setCreateDatetime(new Date());
        data.setRefNo(refNo);
        collectionDAO.insert(data);
        return code;
    }

    @Override
    public List<Collection> queryCollectionList(Collection condition) {
        return collectionDAO.selectList(condition);
    }

    @Override
    public Collection getCollection(String code) {
        Collection data = null;
        if (StringUtils.isNotBlank(code)) {
            Collection condition = new Collection();
            condition.setCode(code);
            data = collectionDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "归集记录不存在");
            }
        }
        return data;
    }

    @Override
    public Collection getCollectionByTxHash(String txHash) {
        Collection condition = new Collection();
        condition.setTxHash(txHash);
        List<Collection> results = collectionDAO.selectList(condition);
        if (CollectionUtils.isEmpty(results)) {
            throw new BizException("xn0000", "归集记录不存在");
        }
        return results.get(0);
    }

    @Override
    public Collection getCollectionByRefNo(String refNo) {
        Collection ethCollection = null;
        Collection condition = new Collection();
        condition.setRefNo(refNo);
        List<Collection> results = collectionDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            ethCollection = results.get(0);
        }
        return ethCollection;
    }

    @Override
    public int colectionNoticeETH(Collection data, BigDecimal txfee,
            Date ethDatetime) {
        int count = 0;
        data.setTxFee(txfee);
        data.setStatus(ECollectionStatus.Broadcast_YES.getCode());
        data.setConfirmDatetime(ethDatetime);
        data.setUpdateDatetime(new Date());
        collectionDAO.updateNoticeETH(data);
        return count;
    }

    @Override
    public int colectionNoticeBTC(Collection data, BigDecimal txfee,
            Date confirmDatetime) {
        int count = 0;
        data.setTxFee(txfee);
        data.setStatus(ECollectionStatus.Broadcast_YES.getCode());
        data.setConfirmDatetime(confirmDatetime);
        data.setUpdateDatetime(new Date());
        collectionDAO.updateNoticeBTC(data);
        return count;
    }

    @Override
    public int colectionNoticeSC(Collection data, String fromAddress,
            BigDecimal txfee, Date ethDatetime) {
        int count = 0;
        data.setTxFee(txfee);
        data.setFromAddress(fromAddress);
        data.setStatus(ECollectionStatus.Broadcast_YES.getCode());
        data.setConfirmDatetime(ethDatetime);
        data.setUpdateDatetime(new Date());
        collectionDAO.updateNoticeSC(data);
        return count;
    }

    @Override
    public EthAddress getAddressUseInfo(String toAddress) {
        Collection data = new Collection();
        data.setToAddress(toAddress);
        data.setStatus(ECollectionStatus.Broadcast_YES.getCode());
        return collectionDAO.selectAddressUseInfo(data);
    }

    @Override
    public BigDecimal getTotalCollect() {
        return collectionDAO.selectTotalCollect();
    }

}
