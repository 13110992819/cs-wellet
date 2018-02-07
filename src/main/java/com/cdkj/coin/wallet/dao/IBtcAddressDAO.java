package com.cdkj.coin.wallet.dao;

import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.dao.base.IBaseDAO;

public interface IBtcAddressDAO extends IBaseDAO<BtcAddress> {
    String NAMESPACE = IBtcAddressDAO.class.getName().concat(".");

    public int updateAbandon(BtcAddress data);

    public int updateStatus(BtcAddress data);
}
