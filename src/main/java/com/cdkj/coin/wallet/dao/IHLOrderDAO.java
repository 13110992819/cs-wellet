package com.cdkj.coin.wallet.dao;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.domain.HLOrder;

public interface IHLOrderDAO extends IBaseDAO<HLOrder> {
    String NAMESPACE = IHLOrderDAO.class.getName().concat(".");

    void approveOrder(HLOrder order);

}
