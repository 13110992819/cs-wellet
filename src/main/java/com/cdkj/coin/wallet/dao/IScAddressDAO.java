package com.cdkj.coin.wallet.dao;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.domain.ScAddress;

public interface IScAddressDAO extends IBaseDAO<ScAddress> {
    String NAMESPACE = IScAddressDAO.class.getName().concat(".");

    public int updateAbandon(ScAddress data);

    public int updateStatus(ScAddress data);
}
