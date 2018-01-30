package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.EthAddress;
import com.cdkj.coin.wallet.domain.EthCollection;

public interface IEthCollectionBO extends IPaginableBO<EthCollection> {

    public String saveEthCollection(String from, String to, BigDecimal value,
            String txHash, String refNo);

    public List<EthCollection> queryEthCollectionList(EthCollection condition);

    public EthCollection getEthCollection(String code);

    public EthCollection getEthCollectionByTxHash(String txHash);

    public EthCollection getEthCollectionByRefNo(String refNo);

    public int colectionNotice(EthCollection data, BigDecimal txfee,
            Date ethDatetime);

    // 归集地址使用次数及归集总额查询
    public EthAddress getAddressUseInfo(String toAddress);

    public BigDecimal getTotalCollect();

}
