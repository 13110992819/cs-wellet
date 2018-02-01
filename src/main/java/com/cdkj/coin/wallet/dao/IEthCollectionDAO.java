package com.cdkj.coin.wallet.dao;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.ethereum.EthCollection;

public interface IEthCollectionDAO extends IBaseDAO<EthCollection> {
    String NAMESPACE = IEthCollectionDAO.class.getName().concat(".");

    public int updateNotice(EthCollection data);

    public EthAddress selectAddressUseInfo(EthCollection data);

    public BigDecimal selectTotalCollect();
}
