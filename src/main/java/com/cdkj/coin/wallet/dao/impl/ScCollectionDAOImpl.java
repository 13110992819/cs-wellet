package com.cdkj.coin.wallet.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.dao.IScCollectionDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.siacoin.ScCollection;

@Repository("scCollectionDAOImpl")
public class ScCollectionDAOImpl extends AMybatisTemplate implements
        IScCollectionDAO {

    @Override
    public int insert(ScCollection data) {
        return super.insert(NAMESPACE.concat("insert_scCollection"), data);
    }

    @Override
    public int delete(ScCollection data) {
        return super.delete(NAMESPACE.concat("delete_scCollection"), data);
    }

    @Override
    public ScCollection select(ScCollection condition) {
        return super.select(NAMESPACE.concat("select_scCollection"), condition,
            ScCollection.class);
    }

    @Override
    public long selectTotalCount(ScCollection condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_scCollection_count"), condition);
    }

    @Override
    public List<ScCollection> selectList(ScCollection condition) {
        return super.selectList(NAMESPACE.concat("select_scCollection"),
            condition, ScCollection.class);
    }

    @Override
    public List<ScCollection> selectList(ScCollection condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_scCollection"), start,
            count, condition, ScCollection.class);
    }

    @Override
    public int updateNotice(ScCollection data) {
        return super.update(NAMESPACE.concat("update_notice"), data);
    }

    @Override
    public ScAddress selectAddressUseInfo(ScCollection data) {
        return super.select(NAMESPACE.concat("select_addressUseInfo"), data,
            ScAddress.class);
    }

    @Override
    public BigDecimal selectTotalCollect() {
        return super.select(NAMESPACE.concat("select_totalCollect"), null,
            BigDecimal.class);
    }

}
