package com.cdkj.coin.wallet.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.coin.wallet.ao.IEthCollectionAO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IEthCollectionBO;
import com.cdkj.coin.wallet.bo.IEthTransactionBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.EthAddress;
import com.cdkj.coin.wallet.domain.EthCollection;
import com.cdkj.coin.wallet.exception.BizException;

@Service
public class EthCollectionAOImpl implements IEthCollectionAO {

    private static final Logger logger = LoggerFactory
        .getLogger(EthCollectionAOImpl.class);

    @Autowired
    private IEthCollectionBO ethCollectionBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Override
    public Paginable<EthCollection> queryEthCollectionPage(int start,
            int limit, EthCollection condition) {
        return ethCollectionBO.getPaginable(start, limit, condition);
    }

    @Override
    public EthCollection getEthCollection(String code) {
        return ethCollectionBO.getEthCollection(code);
    }

    @Override
    public BigDecimal getTotalCollect() {
        return ethCollectionBO.getTotalCollect();
    }

    @Override
    public void collectionManual(BigDecimal balanceStart) {

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
                    doCollection(ethAddress);
                } catch (Exception e) {
                    logger.info("地址" + ethAddress.getAddress() + "手动归集失败，原因："
                            + e.getMessage());
                }
            }
            start++;
        }

    }

    private void doCollection(EthAddress ethAddress) {

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
        ethCollectionBO.saveEthCollection(fromAddress, toAddress, value,
            txHash, null);
    }
}
