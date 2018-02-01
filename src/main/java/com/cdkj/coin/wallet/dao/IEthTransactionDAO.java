package com.cdkj.coin.wallet.dao;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.ethereum.EthTransaction;

public interface IEthTransactionDAO extends IBaseDAO<EthTransaction> {
	String NAMESPACE = IEthTransactionDAO.class.getName().concat(".");
}