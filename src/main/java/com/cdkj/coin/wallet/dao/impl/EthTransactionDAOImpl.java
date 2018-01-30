package com.cdkj.coin.wallet.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.dao.IEthTransactionDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.wallet.domain.EthTransaction;

@Repository("ethTransactionDAOImpl")
public class EthTransactionDAOImpl extends AMybatisTemplate implements
        IEthTransactionDAO {

    @Override
    public int insert(EthTransaction data) {
        return super.insert(NAMESPACE.concat("insert_ethTransaction"), data);
    }

    @Override
    public int delete(EthTransaction data) {
        return super.delete(NAMESPACE.concat("delete_ethTransaction"), data);
    }

    @Override
    public EthTransaction select(EthTransaction condition) {
        return super.select(NAMESPACE.concat("select_ethTransaction"),
            condition, EthTransaction.class);
    }

    @Override
    public long selectTotalCount(EthTransaction condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_ethTransaction_count"), condition);
    }

    @Override
    public List<EthTransaction> selectList(EthTransaction condition) {
        return super.selectList(NAMESPACE.concat("select_ethTransaction"),
            condition, EthTransaction.class);
    }

    @Override
    public List<EthTransaction> selectList(EthTransaction condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_ethTransaction"),
            start, count, condition, EthTransaction.class);
    }

}
