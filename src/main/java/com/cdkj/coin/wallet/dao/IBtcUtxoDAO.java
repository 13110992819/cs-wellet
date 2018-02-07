package com.cdkj.coin.wallet.dao;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.bitcoin.BtcUtxo;
import com.cdkj.coin.wallet.dao.base.IBaseDAO;

public interface IBtcUtxoDAO extends IBaseDAO<BtcUtxo> {
    String NAMESPACE = IBtcUtxoDAO.class.getName().concat(".");

    public int updateStatus(BtcUtxo data);

    public BigDecimal selectTotalUTXOCount(BtcUtxo data);
}
