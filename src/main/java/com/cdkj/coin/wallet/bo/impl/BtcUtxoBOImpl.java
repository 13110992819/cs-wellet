package com.cdkj.coin.wallet.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.IBtcUtxoBO;
import com.cdkj.coin.wallet.dao.IBtcUtxoDAO;

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
    public int saveBtcUtxo(BtcUtxo data) {
        int count = 0;
        if (data != null) {
            count = btcUtxoDAO.insert(data);
        }
        return count;
    }

    @Override
    public int refreshStatus(BtcUtxo data, EBTCUtxoStatus status) {
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
        statusList.add(EBTCUtxoStatus.IN_UN_PUSH.getCode());
        statusList.add(EBTCUtxoStatus.OUT_UN_PUSH.getCode());
        BtcUtxo condition = new BtcUtxo();
        condition.setStatusList(statusList);
        return btcUtxoDAO.selectList(condition);
    }
}
