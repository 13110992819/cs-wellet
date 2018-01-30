package com.cdkj.coin.wallet.ao;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.ExchangeCurrency;

public interface IExchangeCurrencyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 获取虚拟币价值。1fromCurrency等于多少toCurrency
    public Double getExchangeRate(String fromCurrency, String toCurrency);

    public Paginable<ExchangeCurrency> queryExchangeCurrencyPage(int start,
            int limit, ExchangeCurrency condition);

    public ExchangeCurrency getExchangeCurrency(String code);

    // // 不同用户间同币种或不同币种划转资金
    // public void doTransfer(String fromUserId, String fromCurrency,
    // String toUserId, String toCurrency, BigDecimal amount);

}
