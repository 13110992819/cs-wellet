package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.Collection;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.ethereum.EthAddress;

public interface ICollectionBO extends IPaginableBO<Collection> {

    public String saveCollection(ECoin coin, String from, String to,
            BigDecimal value, String txHash, String refNo);

    public List<Collection> queryCollectionList(Collection condition);

    public Collection getCollection(String code);

    public Collection getCollectionByTxHash(String txHash);

    public Collection getCollectionByRefNo(String refNo);

    public int colectionNoticeETH(Collection data, BigDecimal txfee,
            Date ethDatetime);

    public int colectionNoticeBTC(Collection data, BigDecimal txfee,
            Date btcDatetime);

    public int colectionNoticeSC(Collection data, String fromAddress,
            BigDecimal txfee, Date ethDatetime);

    // 归集地址使用次数及归集总额查询
    public EthAddress getAddressUseInfo(String toAddress);

    public BigDecimal getTotalCollect();

}
