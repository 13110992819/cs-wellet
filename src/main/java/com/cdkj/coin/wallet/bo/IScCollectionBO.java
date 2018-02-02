package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.siacoin.ScCollection;

public interface IScCollectionBO extends IPaginableBO<ScCollection> {

    public String saveScCollection(String to, BigDecimal value, String txHash,
            String refNo);

    public List<ScCollection> queryScCollectionList(ScCollection condition);

    public ScCollection getScCollection(String code);

    public ScCollection getScCollectionByTxHash(String txHash);

    public ScCollection getScCollectionByRefNo(String refNo);

    public int colectionNotice(ScCollection data, String fromAddress,
            BigDecimal txfee, Date scDatetime);

    // 归集地址使用次数及归集总额查询
    public ScAddress getAddressUseInfo(String toAddress);

    public BigDecimal getTotalCollect();

}
