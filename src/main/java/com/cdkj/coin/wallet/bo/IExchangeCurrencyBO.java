package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.ExchangeCurrency;
import com.cdkj.coin.wallet.domain.User;

/**
 * @author: xieyj 
 * @since: 2017年3月30日 下午1:01:17 
 * @history:
 */
public interface IExchangeCurrencyBO extends IPaginableBO<ExchangeCurrency> {

    public List<ExchangeCurrency> queryExchangeCurrencyList(
            ExchangeCurrency condition);

    public List<ExchangeCurrency> queryExchangeCurrencyList(String payGroup);

    public ExchangeCurrency getExchangeCurrency(String code);

    // 1fromCurrency=多少toCurrency
    public Double getExchangeRate(String fromCurrency, String toCurrency);

    public String applyExchange(User user, BigDecimal fromAmount,
            String fromCurrency, String toCurrency);

    public void approveExchangeYes(ExchangeCurrency dbOrder, String approver,
            String approveNote);

    public void approveExchangeNo(ExchangeCurrency dbOrder, String approver,
            String approveNote);

    /*
     * 虚拟币划转兑换记录
     */
    public String saveExchange(String fromUserId, BigDecimal fromAmount,
            String fromCurrency, String toUserId, BigDecimal toAmount,
            String toCurrency, String companyCode, String systemCode);

    public String payExchange(String fromUserId, String toUserId,
            BigDecimal rmbAmount, BigDecimal toAmount, String currency,
            String payType, String systemCode);

    public int paySuccess(String code, String status, String payCode,
            Long payAmount);

}
