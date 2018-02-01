package com.cdkj.coin.wallet.bo;

import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.siacoin.CtqScTransaction;
import com.cdkj.coin.wallet.siacoin.ScTransaction;

public interface IScTransactionBO extends IPaginableBO<ScTransaction> {

    public int saveScTransaction(CtqScTransaction ctqScTransaction, String refNo);

    public List<ScTransaction> queryScTransactionList(ScTransaction condition);

    public ScTransaction getScTransaction(String transactionid);

}
