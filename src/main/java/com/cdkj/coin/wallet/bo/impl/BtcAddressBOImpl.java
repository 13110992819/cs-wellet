package com.cdkj.coin.wallet.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.bitcoin.BtcAddressRes;
import com.cdkj.coin.wallet.bitcoin.BtcClient;
import com.cdkj.coin.wallet.bo.IBtcAddressBO;
import com.cdkj.coin.wallet.bo.base.PaginableBOImpl;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.dao.IBtcAddressDAO;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EMAddressStatus;
import com.cdkj.coin.wallet.enums.EXAddressStatus;
import com.cdkj.coin.wallet.enums.EYAddressStatus;
import com.cdkj.coin.wallet.exception.BizException;

@Component
public class BtcAddressBOImpl extends PaginableBOImpl<BtcAddress> implements
        IBtcAddressBO {

    private static Logger logger = Logger.getLogger(BtcAddressBOImpl.class);

    @Autowired
    private IBtcAddressDAO btcAddressDAO;

    @Override
    public String generateAddress(EAddressType type, String userId,
            String accountNumber, String updater, String remark) {
        if (!EAddressType.X.getCode().equals(type.getCode())
                && !EAddressType.M.getCode().equals(type.getCode())) {
            throw new BizException("不支持生成该类型的btc地址");
        }
        // 生成btc地址
        BtcAddressRes btcAddress = BtcClient.getSingleAddress();
        // 落地地址信息
        saveBtcAddress(type, btcAddress.getAddress(),
            btcAddress.getPrivatekey(), userId, accountNumber,
            EMAddressStatus.NORMAL.getCode(), updater, remark);
        return btcAddress.getAddress();
    }

    @Override
    public String saveBtcAddress(EAddressType type, String address,
            String privatekey, String userId, String accountNumber,
            String status, String updater, String remark) {
        String code = OrderNoGenerater.generate("Btc");
        Date now = new Date();
        BtcAddress data = new BtcAddress();
        data.setCode(code);
        data.setType(type.getCode());
        data.setAddress(address);

        data.setUserId(userId);
        data.setAccountNumber(accountNumber);
        data.setStatus(status);
        data.setCreateDatetime(now);
        data.setUpdater(updater);
        data.setUpdateDatetime(now);
        data.setRemark(remark);
        btcAddressDAO.insert(data);
        return code;
    }

    @Override
    public List<BtcAddress> queryBtcAddressList(BtcAddress condition) {
        return btcAddressDAO.selectList(condition);
    }

    @Override
    public BtcAddress getBtcAddress(String code) {
        BtcAddress data = null;
        if (StringUtils.isNotBlank(code)) {
            BtcAddress condition = new BtcAddress();
            condition.setCode(code);
            data = btcAddressDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "Btc地址不存在");
            }
        }
        return data;
    }

    @Override
    public BtcAddress getBtcAddress(EAddressType type, String address) {
        BtcAddress data = null;
        BtcAddress condition = new BtcAddress();
        condition.setType(type.getCode());
        condition.setAddress(address);
        List<BtcAddress> results = btcAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    @Override
    public BtcAddress getBtcAddressByUserId(String userId) {
        BtcAddress data = null;
        BtcAddress condition = new BtcAddress();
        condition.setUserId(userId);
        List<BtcAddress> results = btcAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    @Override
    public BtcAddress getBtcAddressByAccountNumber(String accountNumber) {
        BtcAddress data = null;
        BtcAddress condition = new BtcAddress();
        condition.setAccountNumber(accountNumber);
        List<BtcAddress> results = btcAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    @Override
    public int abandonAddress(BtcAddress address, String updater, String remark) {
        int count = 0;
        if (address != null) {
            Date now = new Date();
            address.setStatus(EXAddressStatus.INVALID.getCode());
            address.setUpdater(updater);
            address.setUpdateDatetime(now);
            address.setRemark(remark);
            btcAddressDAO.updateAbandon(address);
        }
        return count;
    }

    @Override
    public BtcAddress getWBtcAddressToday() {
        BtcAddress condition = new BtcAddress();
        condition.setType(EAddressType.W.getCode());
        condition.setStatus(EYAddressStatus.NORMAL.getCode());
        condition.setOrder("create_datetime", "desc");
        List<BtcAddress> wList = btcAddressDAO.selectList(condition);
        if (CollectionUtils.isEmpty(wList)) {
            throw new BizException("xn625000", "未找到今日可用的归集btc地址");
        }
        return wList.get(0);
    }

    @Override
    public int refreshStatus(BtcAddress address, String status) {
        int count = 0;
        if (address != null) {
            address.setStatus(status);
            address.setUpdateDatetime(new Date());
            btcAddressDAO.updateStatus(address);
        }
        return count;
    }

    @Override
    public boolean isBtcAddressExist(String address) {
        boolean flag = false;
        if (StringUtils.isNotBlank(address)) {
            BtcAddress condition = new BtcAddress();
            condition.setAddress(address);
            if (btcAddressDAO.selectTotalCount(condition) > 0) {
                flag = true;
            }
        }
        return flag;
    }
}
