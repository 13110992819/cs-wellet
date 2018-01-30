package com.cdkj.coin.wallet.dao;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.domain.EthAddress;

public interface IEthAddressDAO extends IBaseDAO<EthAddress> {
    String NAMESPACE = IEthAddressDAO.class.getName().concat(".");

    public int updateAbandon(EthAddress data);

    public int updateBalance(EthAddress data);

    public EthAddress selectSecret(EthAddress condition);

    public BigDecimal selectTotalBalance(EthAddress condition);

    public int updateStatus(EthAddress data);
}
