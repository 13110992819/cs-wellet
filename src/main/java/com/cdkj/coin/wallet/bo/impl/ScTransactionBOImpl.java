package com.cdkj.coin.wallet.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.coin.wallet.bo.IScTransactionBO;
import com.cdkj.coin.wallet.bo.base.PaginableBOImpl;
import com.cdkj.coin.wallet.dao.IScTransactionDAO;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.siacoin.CtqScTransaction;
import com.cdkj.coin.wallet.siacoin.ScTransaction;

@Component
public class ScTransactionBOImpl extends PaginableBOImpl<ScTransaction>
        implements IScTransactionBO {

    @Autowired
    private IScTransactionDAO scTransactionDAO;

    @Override
    public int saveScTransaction(CtqScTransaction ctqScTransaction, String refNo) {
        int count = 0;
        if (ctqScTransaction != null) {
            ScTransaction condition = new ScTransaction();
            condition.setTransactionid(ctqScTransaction.getTransactionid());
            if (scTransactionDAO.selectTotalCount(condition) <= 0) {
                ScTransaction transaction = new ScTransaction();
                transaction.setTransactionid(ctqScTransaction
                    .getTransactionid());
                transaction.setConfirmationheight(ctqScTransaction
                    .getConfirmationheight());
                transaction.setConfirmationtimestamp(ctqScTransaction
                    .getConfirmationtimestamp());
                transaction.setFrom(ctqScTransaction.getFrom());
                transaction.setTo(ctqScTransaction.getTo());
                transaction.setValue(ctqScTransaction.getValue());
                transaction.setMinerfee(ctqScTransaction.getMinerfee());
                transaction.setRefNo(refNo);
                count = scTransactionDAO.insert(transaction);
            }
        }
        return count;
    }

    @Override
    public List<ScTransaction> queryScTransactionList(ScTransaction condition) {
        return scTransactionDAO.selectList(condition);
    }

    @Override
    public ScTransaction getScTransaction(String transactionid) {
        ScTransaction data = null;
        if (StringUtils.isNotBlank(transactionid)) {
            ScTransaction condition = new ScTransaction();
            condition.setTransactionid(transactionid);
            data = scTransactionDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "SC交易记录不存在");
            }
        }
        return data;
    }

}
