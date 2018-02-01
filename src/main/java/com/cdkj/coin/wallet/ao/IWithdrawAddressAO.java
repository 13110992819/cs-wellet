package com.cdkj.coin.wallet.ao;

import java.util.List;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.WithdrawAddress;

public interface IWithdrawAddressAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addWithdrawAddress(String currency, String address,
            String label, String userId, String isCerti);

    public int dropWithdrawAddress(String code);

    public int editWithdrawAddress(WithdrawAddress data);

    public Paginable<WithdrawAddress> queryWithdrawAddressPage(int start,
            int limit, WithdrawAddress condition);

    public List<WithdrawAddress> queryWithdrawAddressList(
            WithdrawAddress condition);

    public WithdrawAddress getWithdrawAddress(String code);

}
