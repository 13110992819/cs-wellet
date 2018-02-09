package com.cdkj.coin.wallet.dao;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.domain.Collection;
import com.cdkj.coin.wallet.ethereum.EthAddress;

public interface ICollectionDAO extends IBaseDAO<Collection> {
    String NAMESPACE = ICollectionDAO.class.getName().concat(".");

    public int updateNoticeETH(Collection data);

    public EthAddress selectAddressUseInfo(Collection data);

    public BigDecimal selectTotalAmount(Collection data);

    public int updateNoticeSC(Collection data);

    public int updateNoticeBTC(Collection data);
}
