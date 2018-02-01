package com.cdkj.coin.wallet.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.IScAddressBO;
import com.cdkj.coin.wallet.bo.base.PaginableBOImpl;
import com.cdkj.coin.wallet.core.OrderNoGenerater;
import com.cdkj.coin.wallet.dao.IScAddressDAO;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EMAddressStatus;
import com.cdkj.coin.wallet.enums.EXAddressStatus;
import com.cdkj.coin.wallet.enums.EYAddressStatus;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.siacoin.SiadClient;

@Component
public class ScAddressBOImpl extends PaginableBOImpl<ScAddress> implements
        IScAddressBO {

    private static Logger logger = Logger.getLogger(ScAddressBOImpl.class);

    @Autowired
    private IScAddressDAO scAddressDAO;

    @Override
    public String generateAddress(EAddressType type, String userId,
            String accountNumber, String updater, String remark) {
        String address = null;
        if (!EAddressType.X.getCode().equals(type.getCode())
                && !EAddressType.M.getCode().equals(type.getCode())) {
            throw new BizException("不支持生成该类型的SC地址");
        }
        // 生成SC地址
        address = SiadClient.getSingleAddress();
        // 落地地址信息
        this.saveScAddress(type, address, userId, accountNumber,
            EMAddressStatus.NORMAL.getCode(), updater, remark);

        return address;
    }

    @Override
    public String saveScAddress(EAddressType type, String address,
            String userId, String accountNumber, String status, String updater,
            String remark) {
        String code = OrderNoGenerater.generate("SC");
        Date now = new Date();
        ScAddress data = new ScAddress();
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
        scAddressDAO.insert(data);
        return code;
    }

    @Override
    public List<ScAddress> queryScAddressList(ScAddress condition) {
        return scAddressDAO.selectList(condition);
    }

    @Override
    public ScAddress getScAddress(String code) {
        ScAddress data = null;
        if (StringUtils.isNotBlank(code)) {
            ScAddress condition = new ScAddress();
            condition.setCode(code);
            data = scAddressDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "SC地址不存在");
            }
        }
        return data;
    }

    @Override
    public ScAddress getScAddress(EAddressType type, String address) {
        ScAddress data = null;
        ScAddress condition = new ScAddress();
        condition.setType(type.getCode());
        condition.setAddress(address);
        List<ScAddress> results = scAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    @Override
    public ScAddress getScAddressByUserId(String userId) {
        ScAddress data = null;
        ScAddress condition = new ScAddress();
        condition.setUserId(userId);
        List<ScAddress> results = scAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    @Override
    public ScAddress getScAddressByAccountNumber(String accountNumber) {
        ScAddress data = null;
        ScAddress condition = new ScAddress();
        condition.setAccountNumber(accountNumber);
        List<ScAddress> results = scAddressDAO.selectList(condition);
        if (CollectionUtils.isNotEmpty(results)) {
            data = results.get(0);
        }
        return data;
    }

    @Override
    public int abandonAddress(ScAddress scAddress, String updater, String remark) {
        int count = 0;
        if (scAddress != null) {
            Date now = new Date();
            scAddress.setStatus(EXAddressStatus.INVALID.getCode());
            scAddress.setUpdater(updater);
            scAddress.setUpdateDatetime(now);
            scAddress.setRemark(remark);
            scAddressDAO.updateAbandon(scAddress);
        }
        return count;
    }

    @Override
    public ScAddress getWScAddressToday() {
        ScAddress condition = new ScAddress();
        condition.setType(EAddressType.W.getCode());
        condition.setStatus(EYAddressStatus.NORMAL.getCode());
        condition.setOrder("create_datetime", "desc");
        List<ScAddress> wList = scAddressDAO.selectList(condition);
        if (CollectionUtils.isEmpty(wList)) {
            throw new BizException("xn625000", "未找到今日可用的归集地址");
        }
        return wList.get(0);
    }

    @Override
    public int refreshStatus(ScAddress address, String status) {
        int count = 0;
        if (address != null) {
            address.setStatus(status);
            address.setUpdateDatetime(new Date());
            scAddressDAO.updateStatus(address);
        }
        return count;
    }

    @Override
    public boolean isScAddressExist(String address) {
        boolean flag = false;
        if (StringUtils.isNotBlank(address)) {
            ScAddress condition = new ScAddress();
            condition.setAddress(address);
            if (scAddressDAO.selectTotalCount(condition) > 0) {
                flag = true;
            }
        }
        return flag;
    }

}
