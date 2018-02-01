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
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.bo.IScAddressBO;
import com.cdkj.coin.wallet.bo.ISmsOutBO;
import com.cdkj.coin.wallet.bo.IUserBO;
import com.cdkj.coin.wallet.bo.IWithdrawBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.ESysUser;
import com.cdkj.coin.wallet.enums.ESystemAccount;
import com.cdkj.coin.wallet.enums.EYAddressStatus;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.siacoin.ScAddress;

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

    // @Autowired
    // private IScCollectionBO scCollectionBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    // @Autowired
    // private IScTransactionBO scTransactionBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICtqBO ctqBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Override
    public void abandonAddress(String code) {
        ScAddress scAddress = scAddressBO.getScAddress(code);
        if (EYAddressStatus.INVALID.getCode().equals(scAddress.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "地址已失效，无需重复弃用");
        }
        scAddressBO.abandonAddress(scAddress);
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
            ESysUser.SYS_USER_ETH.getCode(),
            ESystemAccount.SYS_ACOUNT_ETH.getCode(), updater, remark);
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
            // 地址拥有者信息
            scAddress.setUser(userBO.getUser(scAddress.getUserId()));
        }
        return results;
    }

    @Override
    public ScAddress getScAddress(String code) {
        return scAddressBO.getScAddress(code);
    }

}
