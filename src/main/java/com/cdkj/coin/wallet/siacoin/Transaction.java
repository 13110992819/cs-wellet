/**
 * @Title Transaction.java 
 * @Package com.cdkj.coin.siacoin 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年1月30日 下午9:19:15 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.siacoin;

import java.math.BigInteger;
import java.util.List;

/** 
 * @author: haiqingzheng 
 * @since: 2018年1月30日 下午9:19:15 
 * @history:
 */
public class Transaction {

    private String transactionid;

    private BigInteger confirmationheight;

    private BigInteger confirmationtimestamp;

    private List<Input> inputs;

    private List<Output> outputs;

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public BigInteger getConfirmationheight() {
        return confirmationheight;
    }

    public void setConfirmationheight(BigInteger confirmationheight) {
        this.confirmationheight = confirmationheight;
    }

    public BigInteger getConfirmationtimestamp() {
        return confirmationtimestamp;
    }

    public void setConfirmationtimestamp(BigInteger confirmationtimestamp) {
        this.confirmationtimestamp = confirmationtimestamp;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }

}
