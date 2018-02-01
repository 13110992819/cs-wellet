package com.cdkj.coin.wallet.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.dao.IScTransactionDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.wallet.siacoin.ScTransaction;

@Repository("scTransactionDAOImpl")
public class ScTransactionDAOImpl extends AMybatisTemplate implements
        IScTransactionDAO {

    @Override
    public int insert(ScTransaction data) {
        return super.insert(NAMESPACE.concat("insert_scTransaction"), data);
    }

    @Override
    public int delete(ScTransaction data) {
        return 0;
    }

    @Override
    public ScTransaction select(ScTransaction condition) {
        return super.select(NAMESPACE.concat("select_scTransaction"),
            condition, ScTransaction.class);
    }

    @Override
    public long selectTotalCount(ScTransaction condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_scTransaction_count"), condition);
    }

    @Override
    public List<ScTransaction> selectList(ScTransaction condition) {
        return super.selectList(NAMESPACE.concat("select_scTransaction"),
            condition, ScTransaction.class);
    }

    @Override
    public List<ScTransaction> selectList(ScTransaction condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_scTransaction"),
            start, count, condition, ScTransaction.class);
    }

}
