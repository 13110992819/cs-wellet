package com.cdkj.coin.wallet.bo;

import java.util.List;

import com.cdkj.coin.wallet.bitcoin.BtcUtxo;
import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.enums.EBtcUtxoStatus;

public interface IBtcUtxoBO extends IPaginableBO<BtcUtxo> {

    public boolean isBtcUtxoExist(String txid, Integer vout);

    public int saveBtcUtxo(BtcUtxo data);

    public int refreshStatus(BtcUtxo data, EBtcUtxoStatus status);

    public List<BtcUtxo> queryBtcUtxoList(BtcUtxo condition);

    public BtcUtxo getBtcUtxo(String txid, Integer vout);

    public List<BtcUtxo> selectUnPush();

}
