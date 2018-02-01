package com.cdkj.coin.wallet.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.dao.IEthAddressDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.wallet.ethereum.EthAddress;

@Repository("ethAddressDAOImpl")
public class EthAddressDAOImpl extends AMybatisTemplate implements
        IEthAddressDAO {

    @Override
    public int insert(EthAddress data) {
        return super.insert(NAMESPACE.concat("insert_ethAddress"), data);
    }

    @Override
    public int delete(EthAddress data) {
        return super.delete(NAMESPACE.concat("delete_ethAddress"), data);
    }

    @Override
    public EthAddress select(EthAddress condition) {
        return super.select(NAMESPACE.concat("select_ethAddress"), condition,
            EthAddress.class);
    }

    @Override
    public long selectTotalCount(EthAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_ethAddress_count"), condition);
    }

    @Override
    public List<EthAddress> selectList(EthAddress condition) {
        return super.selectList(NAMESPACE.concat("select_ethAddress"),
            condition, EthAddress.class);
    }

    @Override
    public List<EthAddress> selectList(EthAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_ethAddress"), start,
            count, condition, EthAddress.class);
    }

    @Override
    public int updateAbandon(EthAddress data) {
        return super.update(NAMESPACE.concat("update_abandon"), data);
    }

    @Override
    public int updateBalance(EthAddress data) {
        return super.update(NAMESPACE.concat("update_balance"), data);
    }

    @Override
    public EthAddress selectSecret(EthAddress condition) {
        return super.select(NAMESPACE.concat("select_ethAddress_secret"),
            condition, EthAddress.class);
    }

    @Override
    public BigDecimal selectTotalBalance(EthAddress condition) {
        return super.select(NAMESPACE.concat("select_totalBalance"), condition,
            BigDecimal.class);
    }

    @Override
    public int updateStatus(EthAddress data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
