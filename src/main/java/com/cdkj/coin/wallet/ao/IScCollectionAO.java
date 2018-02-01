package com.cdkj.coin.wallet.ao;

import java.math.BigDecimal;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.siacoin.ScCollection;

public interface IScCollectionAO {

    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<ScCollection> queryScCollectionPage(int start, int limit,
            ScCollection condition);

    public ScCollection getScCollection(String code);

    public BigDecimal getTotalCollect();

    // 手动归集
    public void collectionManual(BigDecimal balanceStart);

}
