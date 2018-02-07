package com.cdkj.coin.wallet.dao;

import com.cdkj.coin.wallet.bitcoin.BtcUtxo;
import com.cdkj.coin.wallet.dao.base.IBaseDAO;

public interface IBtcUtxoDAO extends IBaseDAO<BtcUtxo> {
    String NAMESPACE = IBtcUtxoDAO.class.getName().concat(".");

    public int updateStatus(BtcUtxo data);
}
