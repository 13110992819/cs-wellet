package com.cdkj.coin.wallet.ao.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.wallet.ao.IScCollectionAO;
import com.cdkj.coin.wallet.bo.IScAddressBO;
import com.cdkj.coin.wallet.bo.IScCollectionBO;
import com.cdkj.coin.wallet.bo.IScTransactionBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.siacoin.ScCollection;
import com.cdkj.coin.wallet.siacoin.SiadClient;

@Service
public class ScCollectionAOImpl implements IScCollectionAO {

    private static final Logger logger = LoggerFactory
        .getLogger(ScCollectionAOImpl.class);

    @Autowired
    private IScCollectionBO scCollectionBO;

    @Autowired
    private IScAddressBO scAddressBO;

    @Autowired
    private IScTransactionBO scTransactionBO;

    @Override
    public Paginable<ScCollection> queryScCollectionPage(int start, int limit,
            ScCollection condition) {
        return scCollectionBO.getPaginable(start, limit, condition);
    }

    @Override
    public ScCollection getScCollection(String code) {
        return scCollectionBO.getScCollection(code);
    }

    @Override
    public BigDecimal getTotalCollect() {
        return scCollectionBO.getTotalCollect();
    }

    @Override
    public void collectionManual(BigDecimal balanceStart) {

        // 获取钱包余额
        BigDecimal balance = SiadClient.getSiacoinBalance();
        BigDecimal limit = balanceStart;

        // 余额大于配置值时，进行归集
        if (balance.compareTo(SiadClient.toHasting(limit)) < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "余额太少，无需归集");
        }
        // 获取今日归集地址
        ScAddress wScAddress = scAddressBO.getWScAddressToday();
        String toAddress = wScAddress.getAddress();

        // 默认矿工费用
        BigDecimal txFee = new BigDecimal("22500000000000000000000");
        BigDecimal value = balance.subtract(txFee);
        logger.info("地址余额=" + balance + "，预计矿工费=" + txFee + "，预计到账金额=" + value);
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "余额不足以支付矿工费，不能归集");
        }
        // 归集广播
        String txHash = SiadClient.sendSingleAddress(toAddress, value);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "归集—交易广播失败");
        }
        // 归集记录落地
        scCollectionBO.saveScCollection(toAddress, value, txHash, null);
    }
}
