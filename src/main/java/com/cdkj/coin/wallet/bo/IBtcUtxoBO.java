package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.coin.wallet.bitcoin.BtcUtxo;
import com.cdkj.coin.wallet.bitcoin.CtqBtcUtxo;
import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EBtcUtxoRefType;
import com.cdkj.coin.wallet.enums.EBtcUtxoStatus;

public interface IBtcUtxoBO extends IPaginableBO<BtcUtxo> {

    public boolean isBtcUtxoExist(String txid, Integer vout);

    public int saveBtcUtxo(CtqBtcUtxo ctqBtcUtxo, EAddressType addressType);

    public int refreshBroadcast(BtcUtxo data, EBtcUtxoStatus status,
            EBtcUtxoRefType refType, String refNo);

    public int refreshStatus(BtcUtxo data, EBtcUtxoStatus status);

    public List<BtcUtxo> queryBtcUtxoList(BtcUtxo condition);

    public BtcUtxo getBtcUtxo(String txid, Integer vout);

    public List<BtcUtxo> selectUnPush();

    // 可花费的UTXO总和
    public BigDecimal getTotalEnableUTXOCount(EAddressType addressType);

}
