package com.cdkj.coin.wallet.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.dao.IBtcAddressDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;

@Repository("btcAddressDAOImpl")
public class IBtcAddressDAOImpl extends AMybatisTemplate implements
        IBtcAddressDAO {

    @Override
    public int insert(BtcAddress data) {
        return super.insert(NAMESPACE.concat("insert_btcAddress"), data);
    }

    @Override
    public int delete(BtcAddress data) {
        return 0;
    }

    @Override
    public BtcAddress select(BtcAddress condition) {
        return super.select(NAMESPACE.concat("select_btcAddress"), condition,
            BtcAddress.class);
    }

    @Override
    public long selectTotalCount(BtcAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_btcAddress_count"), condition);
    }

    @Override
    public List<BtcAddress> selectList(BtcAddress condition) {
        return super.selectList(NAMESPACE.concat("select_btcAddress"),
            condition, BtcAddress.class);
    }

    @Override
    public List<BtcAddress> selectList(BtcAddress condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_btcAddress"), start,
            count, condition, BtcAddress.class);
    }

    @Override
    public int updateAbandon(BtcAddress data) {
        return super.update(NAMESPACE.concat("update_abandon"), data);
    }

    @Override
    public int updateStatus(BtcAddress data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
