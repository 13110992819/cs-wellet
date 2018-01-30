package com.cdkj.coin.wallet.ao;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.EthCollection;

public interface IEthCollectionAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<EthCollection> queryEthCollectionPage(int start,
            int limit, EthCollection condition);

    public EthCollection getEthCollection(String code);

    public BigDecimal getTotalCollect();

    // 手动归集
    public void collectionManual(BigDecimal balanceStart);

}
