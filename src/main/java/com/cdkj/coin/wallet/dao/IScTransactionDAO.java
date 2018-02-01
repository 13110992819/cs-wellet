package com.cdkj.coin.wallet.dao;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.siacoin.ScTransaction;

public interface IScTransactionDAO extends IBaseDAO<ScTransaction> {
    String NAMESPACE = IScTransactionDAO.class.getName().concat(".");

}
