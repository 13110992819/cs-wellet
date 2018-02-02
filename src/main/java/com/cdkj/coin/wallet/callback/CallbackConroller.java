package com.cdkj.coin.wallet.callback;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdkj.coin.wallet.ao.IEthAddressAO;
import com.cdkj.coin.wallet.ao.IEthTransactionAO;
import com.cdkj.coin.wallet.ao.IScAddressAO;
import com.cdkj.coin.wallet.ao.IScTransactionAO;
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.ethereum.CtqEthTransaction;
import com.cdkj.coin.wallet.siacoin.CtqScTransaction;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月26日 下午1:44:16 
 * @history:
 */
@Controller
public class CallbackConroller {

    private static Logger logger = Logger.getLogger(CallbackConroller.class);

    @Autowired
    IEthAddressAO ethAddressAO;

    @Autowired
    IEthTransactionAO ethTransactionAO;

    @Autowired
    IScAddressAO scAddressAO;

    @Autowired
    IScTransactionAO scTransactionAO;

    @Autowired
    ICtqBO ctqBO;

    // ETH交易通知
    @RequestMapping("/eth/tx/notice")
    public synchronized void doEthCallback(HttpServletRequest request,
            HttpServletResponse response) {
        List<String> hashList = new ArrayList<String>();
        try {
            logger.info("*****以太坊交易通知开始*****");
            logger.info(request.getParameter("ethTxlist"));
            String txJson = request.getParameter("ethTxlist");
            Gson gson = new Gson();
            List<CtqEthTransaction> list = gson.fromJson(txJson,
                new TypeToken<List<CtqEthTransaction>>() {
                }.getType());

            for (CtqEthTransaction ctqEthTransaction : list) {
                String fromAddress = ctqEthTransaction.getFrom();
                String toAddress = ctqEthTransaction.getTo();
                EAddressType fromType = ethAddressAO.getType(fromAddress);
                EAddressType toType = ethAddressAO.getType(toAddress);

                if (EAddressType.M == fromType) { // fromAddress=M 提现
                    ethTransactionAO.withdrawNotice(ctqEthTransaction);
                    hashList.add(ctqEthTransaction.getHash());
                    if (EAddressType.X == toType) { // toAddress=X 充值
                        String code = ethTransactionAO
                            .chargeNotice(ctqEthTransaction);
                        if (StringUtils.isNotBlank(code)) {
                            ethTransactionAO.collection(
                                ctqEthTransaction.getTo(), code);
                        }
                    }
                    // hashList.add(ctqEthTransaction.getHash());
                } else if (EAddressType.X == toType) { // toAddress=X 充值
                    String code = ethTransactionAO
                        .chargeNotice(ctqEthTransaction);
                    if (StringUtils.isNotBlank(code)) {
                        ethTransactionAO.collection(ctqEthTransaction.getTo(),
                            code);
                    }
                    hashList.add(ctqEthTransaction.getHash());
                } else if (EAddressType.X == fromType
                        && EAddressType.W == toType) {
                    // fromAddress=X toAddress=W 归集
                    ethTransactionAO.collectionNotice(ctqEthTransaction);
                    hashList.add(ctqEthTransaction.getHash());
                } else if (EAddressType.M == toType) {
                    // toAddress=M 每日定存
                    ethTransactionAO.depositNotice(ctqEthTransaction);
                    hashList.add(ctqEthTransaction.getHash());
                } else if (EAddressType.W == fromType) {
                    // fromAddress=W 每日转移
                    hashList.add(ctqEthTransaction.getHash());
                }

                logger.info("处理交易：" + ctqEthTransaction.getHash());
            }
            logger.info("*****业务处理完成*****");
        } catch (Exception e) {
            logger.info("交易通知异常,原因：" + e.getMessage());
        } finally {
            logger.info("*****橙提取交易确认,交易个数为" + hashList.size() + "*****");
            if (CollectionUtils.isNotEmpty(hashList)) {
                ctqBO.confirmEth(hashList);
            }
            logger.info("*****complete*****");
        }
    }

    // SC交易通知
    @RequestMapping("/sc/tx/notice")
    public synchronized void doScCallback(HttpServletRequest request,
            HttpServletResponse response) {
        List<String> hashList = new ArrayList<String>();
        try {
            logger.info("*****Siacoin交易通知开始*****");
            logger.info(request.getParameter("scTxlist"));
            String txJson = request.getParameter("scTxlist");
            Gson gson = new Gson();
            List<CtqScTransaction> list = gson.fromJson(txJson,
                new TypeToken<List<CtqScTransaction>>() {
                }.getType());

            for (CtqScTransaction ctqScTransaction : list) {
                String fromAddress = ctqScTransaction.getFrom();
                String toAddress = ctqScTransaction.getTo();
                EAddressType fromType = scAddressAO.getType(fromAddress);
                EAddressType toType = scAddressAO.getType(toAddress);

                if (EAddressType.M == fromType) { // fromAddress=M 提现
                    // ethTransactionAO.withdrawNotice(scEthTransaction);
                    // hashList.add(scEthTransaction.getHash());
                    // if (EAddressType.X == toType) { // toAddress=X 充值
                    // String code = ethTransactionAO
                    // .chargeNotice(ctqEthTransaction);
                    // if (StringUtils.isNotBlank(code)) {
                    // ethTransactionAO.collection(
                    // ctqEthTransaction.getTo(), code);
                    // }
                    // }
                    // hashList.add(ctqEthTransaction.getHash());
                } else if (EAddressType.X == toType) { // toAddress=X 充值
                    String code = scTransactionAO
                        .chargeNotice(ctqScTransaction);
                    if (StringUtils.isNotBlank(code)) {
                        scTransactionAO.collection(code);
                    }
                    hashList.add(ctqScTransaction.getTransactionid());
                } else if (EAddressType.W == toType) {
                    // toAddress=W 归集
                    scTransactionAO.collectionNotice(ctqScTransaction);
                    hashList.add(ctqScTransaction.getTransactionid());
                } else if (EAddressType.M == toType) {
                    // toAddress=M 每日定存
                    // ethTransactionAO.depositNotice(ctqEthTransaction);
                    hashList.add(ctqScTransaction.getTransactionid());
                } else if (EAddressType.W == fromType) {
                    // fromAddress=W 每日转移
                    hashList.add(ctqScTransaction.getTransactionid());
                }

                logger.info("处理交易：" + ctqScTransaction.getTransactionid());
            }
            logger.info("*****业务处理完成*****");
        } catch (Exception e) {
            logger.info("交易通知异常,原因：" + e.getMessage());
        } finally {
            logger.info("*****橙提取交易确认,交易个数为" + hashList.size() + "*****");
            if (CollectionUtils.isNotEmpty(hashList)) {
                ctqBO.confirmSc(hashList);
            }
            logger.info("*****complete*****");
        }
    }
}
