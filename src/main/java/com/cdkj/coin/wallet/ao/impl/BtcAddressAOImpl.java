package com.cdkj.coin.wallet.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.coin.wallet.ao.IBtcAddressAO;
import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.IBtcAddressBO;
import com.cdkj.coin.wallet.bo.ICollectionBO;
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.bo.IScTransactionBO;
import com.cdkj.coin.wallet.bo.IWithdrawBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EMAddressStatus;
import com.cdkj.coin.wallet.enums.ESysUser;
import com.cdkj.coin.wallet.enums.ESystemAccount;
import com.cdkj.coin.wallet.enums.EWAddressStatus;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;

/**
 * @author: xieyj 
 * @since: 2018年2月7日 下午3:23:31 
 * @history:
 */
@Service
public class BtcAddressAOImpl implements IBtcAddressAO {
    private static final Logger logger = LoggerFactory
        .getLogger(BtcAddressAOImpl.class);

    @Autowired
    private IBtcAddressBO btcAddressBO;

    @Autowired
    private ICollectionBO collectionBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IScTransactionBO scTransactionBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ICtqBO ctqBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    @Transactional
    public String importWAddress(String address, String updater, String remark) {
        if (btcAddressBO.isBtcAddressExist(address)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "地址"
                    + address + "已经在平台内被使用，请仔细核对");
        }
        // 地址有效性校验
        // if (!SiadClient.verifyAddress(address)) {
        // throw new BizException(EBizErrorCode.DEFAULT.getCode(), "地址"
        // + address + "不符合Bitcoin规则，请仔细核对");
        // }
        String code = btcAddressBO.saveBtcAddress(EAddressType.W, address,
            null, ESysUser.SYS_USER_COLD.getCode(),
            ESystemAccount.SYS_ACOUNT_SC_COLD.getCode(),
            EWAddressStatus.NORMAL.getCode(), updater, remark);
        // 通知橙提取
        ctqBO.uploadBtcAddress(address);
        return code;
    }

    @Override
    public void abandonAddress(String code, String updater, String remark) {
        BtcAddress btcAddress = btcAddressBO.getBtcAddress(code);
        if (!EAddressType.M.getCode().equals(btcAddress.getType())
                && !EAddressType.W.getCode().equals(btcAddress.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "该类型地址不能弃用");
        }
        if (EMAddressStatus.INVALID.getCode().equals(btcAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址已失效，无需重复弃用");
        }
        btcAddressBO.abandonAddress(btcAddress, updater, remark);
    }

    @Override
    public EAddressType getType(String address) {
        EAddressType type = EAddressType.Y;
        BtcAddress condition = new BtcAddress();
        condition.setAddress(address);
        List<BtcAddress> results = btcAddressBO.queryBtcAddressList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            BtcAddress btcAddress = results.get(0);
            type = EAddressType.getAddressType(btcAddress.getType());
        }
        return type;
    }

    @Override
    public String generateMAddress(String updater, String remark) {
        String address = btcAddressBO.generateAddress(EAddressType.M,
            ESysUser.SYS_USER.getCode(),
            ESystemAccount.SYS_ACOUNT_BTC.getCode(), updater, remark);
        // 通知橙提取
        ctqBO.uploadBtcAddress(address);
        return address;
    }

    @Override
    @Transactional
    public Paginable<BtcAddress> queryBtcAddressPage(int start, int limit,
            BtcAddress condition) {
        Paginable<BtcAddress> results = btcAddressBO.getPaginable(start, limit,
            condition);
        for (BtcAddress btcAddress : results.getList()) {
            // 归集地址统计
            if (EAddressType.W.getCode().equals(btcAddress.getType())) {
                EthAddress xAddress = collectionBO.getAddressUseInfo(btcAddress
                    .getAddress());
                btcAddress.setUseCount(xAddress.getUseCount());
                btcAddress.setUseAmount(xAddress.getUseAmount());
            }
            // 散取地址统计
            if (EAddressType.M.getCode().equals(btcAddress.getType())) {
                EthAddress xAddress = withdrawBO.getAddressUseInfo(btcAddress
                    .getAddress());
                btcAddress.setUseCount(xAddress.getUseCount());
                btcAddress.setUseAmount(xAddress.getUseAmount());
            }
        }
        return results;
    }

    @Override
    public BtcAddress getBtcAddress(String code) {
        return btcAddressBO.getBtcAddress(code);
    }

}
