package com.cdkj.coin.wallet.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.dao.IWithdrawAddressDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.wallet.domain.WithdrawAddress;

@Repository("withdrawAddressDAOImpl")
public class WithdrawAddressDAOImpl extends AMybatisTemplate implements
        IWithdrawAddressDAO {

    @Override
    public int insert(WithdrawAddress data) {
        return super.insert(NAMESPACE.concat("insert_withdrawAddress"), data);
    }

    @Override
    public int delete(WithdrawAddress data) {
        return super.delete(NAMESPACE.concat("delete_withdrawAddress"), data);
    }

    @Override
    public WithdrawAddress select(WithdrawAddress condition) {
        return super.select(NAMESPACE.concat("select_withdrawAddress"),
            condition, WithdrawAddress.class);
    }

    @Override
    public long selectTotalCount(WithdrawAddress condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_withdrawAddress_count"), condition);
    }

    @Override
    public List<WithdrawAddress> selectList(WithdrawAddress condition) {
        return super.selectList(NAMESPACE.concat("select_withdrawAddress"),
            condition, WithdrawAddress.class);
    }

    @Override
    public List<WithdrawAddress> selectList(WithdrawAddress condition,
            int start, int count) {
        return super.selectList(NAMESPACE.concat("select_withdrawAddress"),
            start, count, condition, WithdrawAddress.class);
    }

}
