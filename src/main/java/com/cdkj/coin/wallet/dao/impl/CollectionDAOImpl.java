package com.cdkj.coin.wallet.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.dao.ICollectionDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.wallet.domain.Collection;
import com.cdkj.coin.wallet.ethereum.EthAddress;

@Repository("collectionDAOImpl")
public class CollectionDAOImpl extends AMybatisTemplate implements
        ICollectionDAO {

    @Override
    public int insert(Collection data) {
        return super.insert(NAMESPACE.concat("insert_collection"), data);
    }

    @Override
    public int delete(Collection data) {
        return super.delete(NAMESPACE.concat("delete_collection"), data);
    }

    @Override
    public Collection select(Collection condition) {
        return super.select(NAMESPACE.concat("select_collection"), condition,
            Collection.class);
    }

    @Override
    public long selectTotalCount(Collection condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_collection_count"), condition);
    }

    @Override
    public List<Collection> selectList(Collection condition) {
        return super.selectList(NAMESPACE.concat("select_collection"),
            condition, Collection.class);
    }

    @Override
    public List<Collection> selectList(Collection condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_collection"), start,
            count, condition, Collection.class);
    }

    @Override
    public int updateNoticeETH(Collection data) {
        return super.update(NAMESPACE.concat("update_notice_eth"), data);
    }

    @Override
    public int updateNoticeSC(Collection data) {
        return super.update(NAMESPACE.concat("update_notice_sc"), data);
    }

    @Override
    public int updateNoticeBTC(Collection data) {
        return super.update(NAMESPACE.concat("update_notice_btc"), data);
    }

    @Override
    public EthAddress selectAddressUseInfo(Collection data) {
        return super.select(NAMESPACE.concat("select_addressUseInfo"), data,
            EthAddress.class);
    }

    @Override
    public BigDecimal selectTotalCollect() {
        return super.select(NAMESPACE.concat("select_totalCollect"), null,
            BigDecimal.class);
    }

}
