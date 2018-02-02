package com.cdkj.coin.wallet.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.wallet.ao.IExchangeCurrencyAO;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.IExchangeCurrencyBO;
import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.ExchangeCurrency;

@Service
public class ExchangeCurrencyAOImpl implements IExchangeCurrencyAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IExchangeCurrencyBO exchangeCurrencyBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public Paginable<ExchangeCurrency> queryExchangeCurrencyPage(int start,
            int limit, ExchangeCurrency condition) {
        Paginable<ExchangeCurrency> page = exchangeCurrencyBO.getPaginable(
            start, limit, condition);
        return page;
    }

    @Override
    public ExchangeCurrency getExchangeCurrency(String code) {
        ExchangeCurrency exchangeCurrency = exchangeCurrencyBO
            .getExchangeCurrency(code);
        return exchangeCurrency;
    }

    @Override
    public Double getExchangeRate(String fromCurrency, String toCurrency) {
        return exchangeCurrencyBO.getExchangeRate(fromCurrency, toCurrency);
    }

    // @Override
    // @Transactional
    // public void doTransfer(String fromUserId, String fromCurrency,
    // String toUserId, String toCurrency, BigDecimal transAmount) {
    // // 转化前提是否满足
    // if (ECurrency.CNY.getCode().equals(toCurrency)) {
    // throw new BizException("xn000000", "转化币种不能是人民币");
    // }
    // Account fromAccount = accountBO.getAccountByUser(fromUserId,
    // fromCurrency);
    // Account toAccount = accountBO.getAccountByUser(toUserId, toCurrency);
    // Double rate = this.getExchangeRate(fromCurrency, toCurrency);
    // BigDecimal toAmount = AmountUtil.mul(transAmount, rate);
    //
    // // 开始资金划转
    // String bizNote = transAmount
    // + ECurrency.getCurrencyMap().get(fromCurrency).getValue()
    // + "转化为" + toAmount
    // + ECurrency.getCurrencyMap().get(toCurrency).getValue();
    // String fromBizNote = bizNote;
    // String toBizNote = bizNote;
    // if (fromCurrency.equals(toCurrency)) {
    // fromBizNote = "代发代销至账户[" + toAccount.getRealName() + "]";
    // toBizNote = "账户[" + fromAccount.getRealName() + "]代发代销";
    // }
    // String code = exchangeCurrencyBO.saveExchange(fromUserId, transAmount,
    // fromCurrency, toUserId, toAmount, toCurrency,
    // fromAccount.getCompanyCode(), fromAccount.getSystemCode());
    // accountBO.changeAmount(fromAccount.getAccountNumber(),
    // EChannelType.NBZ, null, null, code, EJourBizType.Transfer_CURRENCY,
    // fromBizNote, transAmount.negate());
    // accountBO.changeAmount(toAccount.getAccountNumber(), EChannelType.NBZ,
    // null, null, code, EJourBizType.Transfer_CURRENCY, toBizNote,
    // toAmount);
    // }

}
