package com.cdkj.coin.wallet.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.dao.IScAddressDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.wallet.siacoin.ScAddress;

@Repository("scAddressDAOImpl")
public class ScAddressDAOImpl extends AMybatisTemplate implements IScAddressDAO {

    @Override
    public int insert(ScAddress data) {
        return super.insert(NAMESPACE.concat("insert_scAddress"), data);
    }

    @Override
    public int delete(ScAddress data) {
        return 0;
    }

    @Override
    public ScAddress select(ScAddress condition) {
        return super.select(NAMESPACE.concat("select_scAddress"), condition,
            ScAddress.class);
    }

    @Override
    public long selectTotalCount(ScAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_scAddress_count"), condition);
    }

    @Override
    public List<ScAddress> selectList(ScAddress condition) {
        return super.selectList(NAMESPACE.concat("select_scAddress"),
            condition, ScAddress.class);
    }

    @Override
    public List<ScAddress> selectList(ScAddress condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_scAddress"), start,
            count, condition, ScAddress.class);
    }

    @Override
    public int updateAbandon(ScAddress data) {
        return super.update(NAMESPACE.concat("update_abandon"), data);
    }

    @Override
    public int updateStatus(ScAddress data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

}
