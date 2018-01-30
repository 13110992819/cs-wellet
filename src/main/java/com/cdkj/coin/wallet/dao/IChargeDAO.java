package com.cdkj.coin.wallet.dao;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.domain.Charge;

public interface IChargeDAO extends IBaseDAO<Charge> {
    String NAMESPACE = IChargeDAO.class.getName().concat(".");

    void payOrder(Charge data);
}
