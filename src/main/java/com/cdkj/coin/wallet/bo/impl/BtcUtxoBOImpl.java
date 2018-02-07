package com.cdkj.coin.wallet.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bitcoin.BtcUtxo;
import com.cdkj.coin.wallet.bitcoin.CtqBtcUtxo;
import com.cdkj.coin.wallet.bo.IBtcUtxoBO;
import com.cdkj.coin.wallet.bo.base.PaginableBOImpl;
import com.cdkj.coin.wallet.dao.IBtcUtxoDAO;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EBtcUtxoStatus;
import com.cdkj.coin.wallet.exception.BizException;

@Component
public class BtcUtxoBOImpl extends PaginableBOImpl<BtcUtxo> implements
        IBtcUtxoBO {

    @Autowired
    private IBtcUtxoDAO btcUtxoDAO;

    @Override
    public boolean isBtcUtxoExist(String txid, Integer vout) {
        BtcUtxo condition = new BtcUtxo();
        condition.setTxid(txid);
        condition.setVout(vout);
        if (btcUtxoDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int saveBtcUtxo(CtqBtcUtxo ctqBtcUtxo, EAddressType addressType) {
        int count = 0;
        if (ctqBtcUtxo != null) {

            BtcUtxo data = new BtcUtxo();

            data.setTxid(ctqBtcUtxo.getTxid());
            data.setVout(ctqBtcUtxo.getVout());
            data.setCount(ctqBtcUtxo.getCount());
            data.setScriptPubKey(ctqBtcUtxo.getScriptPubKey());
            data.setAddress(ctqBtcUtxo.getAddress());

            data.setSyncTime(ctqBtcUtxo.getSyncTime());
            data.setBlockHeight(ctqBtcUtxo.getBlockHeight());
            data.setStatus(EBtcUtxoStatus.ENABLE.getCode());
            data.setAddressType(addressType.getCode());

            count = btcUtxoDAO.insert(data);

        }
        return count;
    }

    @Override
    public int refreshStatus(BtcUtxo data, EBtcUtxoStatus status) {
        int count = 0;
        if (data != null) {
            data.setStatus(status.getCode());
            count = btcUtxoDAO.updateStatus(data);
        }
        return count;
    }

    @Override
    public List<BtcUtxo> queryBtcUtxoList(BtcUtxo condition) {
        return btcUtxoDAO.selectList(condition);
    }

    @Override
    public BtcUtxo getBtcUtxo(String txid, Integer vout) {
        BtcUtxo data = null;
        if (StringUtils.isNotBlank(txid)) {
            BtcUtxo condition = new BtcUtxo();
            condition.setTxid(txid);
            condition.setVout(vout);
            data = btcUtxoDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "UTXO记录不存在");
            }
        }
        return data;
    }

    @Override
    public List<BtcUtxo> selectUnPush() {
        List<String> statusList = new ArrayList<String>();
        BtcUtxo condition = new BtcUtxo();
        condition.setStatusList(statusList);
        return btcUtxoDAO.selectList(condition);
    }

    @Override
    public BigDecimal getTotalEnableUTXOCount(EAddressType addressType) {
        BtcUtxo condition = new BtcUtxo();
        condition.setAddressType(addressType.getCode());
        condition.setStatus(EBtcUtxoStatus.ENABLE.getCode());
        return btcUtxoDAO.selectTotalUTXOCount(condition);
    }
}
