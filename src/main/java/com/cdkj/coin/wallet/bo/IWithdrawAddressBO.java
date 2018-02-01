package com.cdkj.coin.wallet.bo;

import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.WithdrawAddress;

public interface IWithdrawAddressBO extends IPaginableBO<WithdrawAddress> {

    public String saveWithdrawAddress(String currency, String address,
            String label, String userId, String status);

    public int removeWithdrawAddress(String code);

    public int refreshWithdrawAddress(WithdrawAddress data);

    public List<WithdrawAddress> queryWithdrawAddressList(
            WithdrawAddress condition);

    public WithdrawAddress getWithdrawAddress(String code);

}
