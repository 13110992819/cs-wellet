package com.cdkj.coin.wallet.dao;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.siacoin.ScCollection;

public interface IScCollectionDAO extends IBaseDAO<ScCollection> {
    String NAMESPACE = IScCollectionDAO.class.getName().concat(".");

    public int updateNotice(ScCollection data);

    public ScAddress selectAddressUseInfo(ScCollection data);

    public BigDecimal selectTotalCollect();
}
