/**
 * @Title ScAddressAOImpl.java 
 * @Package com.cdkj.coin.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:43:34 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.coin.wallet.ao.IScAddressAO;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.ICollectionBO;
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.bo.IScAddressBO;
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
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.siacoin.SiadClient;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:43:34 
 * @history:
 */
@Service
public class ScAddressAOImpl implements IScAddressAO {
    private static final Logger logger = LoggerFactory
        .getLogger(ScAddressAOImpl.class);

    @Autowired
    private IScAddressBO scAddressBO;

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
        if (scAddressBO.isScAddressExist(address)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "地址"
                    + address + "已经在平台内被使用，请仔细核对");
        }
        // 地址有效性校验
        if (!SiadClient.verifyAddress(address)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "地址"
                    + address + "不符合Siacoin规则，请仔细核对");
        }
        String code = scAddressBO.saveScAddress(EAddressType.W, address,
            ESysUser.SYS_USER_COLD.getCode(),
            ESystemAccount.SYS_ACOUNT_SC_COLD.getCode(),
            EWAddressStatus.NORMAL.getCode(), updater, remark);
        // 通知橙提取
        ctqBO.uploadScAddress(address, EAddressType.M.getCode());
        return code;
    }

    @Override
    public void abandonAddress(String code, String updater, String remark) {
        ScAddress scAddress = scAddressBO.getScAddress(code);
        if (!EAddressType.M.getCode().equals(scAddress.getType())
                && !EAddressType.W.getCode().equals(scAddress.getType())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "该类型地址不能弃用");
        }
        if (EMAddressStatus.INVALID.getCode().equals(scAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址已失效，无需重复弃用");
        }
        scAddressBO.abandonAddress(scAddress, updater, remark);
    }

    @Override
    public EAddressType getType(String address) {
        EAddressType type = EAddressType.Y;
        ScAddress condition = new ScAddress();
        condition.setAddress(address);
        List<ScAddress> results = scAddressBO.queryScAddressList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            ScAddress scAddress = results.get(0);
            type = EAddressType.getAddressType(scAddress.getType());
        }
        return type;
    }

    @Override
    public String generateMAddress(String updater, String remark) {
        String address = scAddressBO.generateAddress(EAddressType.M,
            ESysUser.SYS_USER.getCode(),
            ESystemAccount.SYS_ACOUNT_SC.getCode(), updater, remark);
        // 通知橙提取
        ctqBO.uploadScAddress(address, EAddressType.M.getCode());
        return address;
    }

    @Override
    @Transactional
    public Paginable<ScAddress> queryScAddressPage(int start, int limit,
            ScAddress condition) {
        Paginable<ScAddress> results = scAddressBO.getPaginable(start, limit,
            condition);
        for (ScAddress scAddress : results.getList()) {
            // 归集地址统计
            if (EAddressType.W.getCode().equals(scAddress.getType())) {
                EthAddress xAddress = collectionBO.getAddressUseInfo(scAddress
                    .getAddress());
                scAddress.setUseCount(xAddress.getUseCount());
                scAddress.setUseAmount(xAddress.getUseAmount());
            }
            // 散取地址统计
            if (EAddressType.M.getCode().equals(scAddress.getType())) {
                EthAddress xAddress = withdrawBO.getAddressUseInfo(scAddress
                    .getAddress());
                scAddress.setUseCount(xAddress.getUseCount());
                scAddress.setUseAmount(xAddress.getUseAmount());
            }
        }
        return results;
    }

    @Override
    public ScAddress getScAddress(String code) {
        return scAddressBO.getScAddress(code);
    }

}
