package com.cdkj.coin.wallet.dao;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.domain.WithdrawAddress;

public interface IWithdrawAddressDAO extends IBaseDAO<WithdrawAddress> {
	String NAMESPACE = IWithdrawAddressDAO.class.getName().concat(".");
}