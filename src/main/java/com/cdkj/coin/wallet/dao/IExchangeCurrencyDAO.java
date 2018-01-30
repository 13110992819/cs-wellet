package com.cdkj.coin.wallet.dao;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.domain.ExchangeCurrency;

public interface IExchangeCurrencyDAO extends IBaseDAO<ExchangeCurrency> {
    String NAMESPACE = IExchangeCurrencyDAO.class.getName().concat(".");

    int applyExchange(ExchangeCurrency data);

    int approveExchange(ExchangeCurrency data);

    int doExchange(ExchangeCurrency data);

    int paySuccess(ExchangeCurrency data);

}
