package com.cdkj.coin.wallet.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.wallet.ao.ICollectionAO;
import com.cdkj.coin.wallet.bo.ICollectionBO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IEthTransactionBO;
import com.cdkj.coin.wallet.bo.IScAddressBO;
import com.cdkj.coin.wallet.bo.IScTransactionBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.Collection;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.siacoin.SiadClient;

@Service
public class CollectionAOImpl implements ICollectionAO {

    private static final Logger logger = LoggerFactory
        .getLogger(CollectionAOImpl.class);

    @Autowired
    private ICollectionBO collectionBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private IScAddressBO scAddressBO;

    @Autowired
    private IScTransactionBO scTransactionBO;

    @Override
    public Paginable<Collection> queryCollectionPage(int start, int limit,
            Collection condition) {
        return collectionBO.getPaginable(start, limit, condition);
    }

    @Override
    public Collection getCollection(String code) {
        return collectionBO.getCollection(code);
    }

    @Override
    public BigDecimal getTotalCollect() {
        return collectionBO.getTotalCollect();
    }

    @Override
    public void collectionManual(BigDecimal balanceStart, String currency) {

        if (ECoin.ETH.getCode().equals(currency)) {
            doCollectionManualETH(balanceStart);
        } else if (ECoin.SC.getCode().equals(currency)) {
            doCollectionManualSC(balanceStart);
        } else if (ECoin.BTC.getCode().equals(currency)) {

        }

    }

    private void doCollectionManualSC(BigDecimal balanceStart) {
        // 获取钱包余额
        BigDecimal balance = SiadClient.getSiacoinBalance();

        // 余额大于配置值时，进行归集
        if (balance.compareTo(SiadClient.toHasting(balanceStart)) < 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "SC钱包余额"
                    + SiadClient.fromHasting(balance).toString() + "，无需归集");
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
        collectionBO.saveCollection(ECoin.SC, null, null, value, txHash, null);
    }

    private void doCollectionManualETH(BigDecimal balanceStart) {
        int start = 0;
        int limit = 10;
        while (true) {
            // 取出符合条件的地址列表
            List<EthAddress> ethAddresseList = ethAddressBO
                .queryManualCollectionAddressPage(balanceStart, start, limit);
            if (CollectionUtils.isEmpty(ethAddresseList)) {
                break;
            }
            // 开始归集逻辑
            for (EthAddress ethAddress : ethAddresseList) {
                try {
                    doETHCollection(ethAddress);
                } catch (Exception e) {
                    logger.info("地址" + ethAddress.getAddress() + "手动归集失败，原因："
                            + e.getMessage());
                }
            }
            start++;
        }

    }

    private void doETHCollection(EthAddress ethAddress) {

        String fromAddress = ethAddress.getAddress();

        // 获取今日归集地址
        EthAddress wEthAddress = ethAddressBO.getWEthAddressToday();
        String toAddress = wEthAddress.getAddress();

        // 预估矿工费用
        BigDecimal balance = ethAddress.getBalance();
        BigDecimal gasPrice = ethTransactionBO.getGasPrice();
        BigDecimal gasUse = new BigDecimal(21000);
        BigDecimal txFee = gasPrice.multiply(gasUse);
        BigDecimal value = balance.subtract(txFee);
        logger.info("地址余额=" + balance + "，以太坊平均价格=" + gasPrice + "，预计矿工费="
                + txFee + "，预计到账金额=" + value);
        if (value.compareTo(BigDecimal.ZERO) < 0
                || value.compareTo(BigDecimal.ZERO) == 0) {
            throw new BizException("xn625000", "余额不足以支付矿工费，不能归集");
        }
        // 归集广播
        EthAddress secret = ethAddressBO.getEthAddressSecret(ethAddress
            .getCode());
        String txHash = ethTransactionBO.broadcast(fromAddress, secret,
            toAddress, value);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "归集—交易广播失败");
        }
        // 归集记录落地
        collectionBO.saveCollection(ECoin.ETH, fromAddress, toAddress, value,
            txHash, null);
    }
}
