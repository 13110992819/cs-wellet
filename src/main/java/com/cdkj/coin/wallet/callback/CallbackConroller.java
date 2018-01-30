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
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.enums.EEthAddressType;
import com.cdkj.coin.wallet.eth.CtqEthTransaction;
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
    ICtqBO ctqBO;

    // 交易通知
    @RequestMapping("/eth/tx/notice")
    public synchronized void doCallback(HttpServletRequest request,
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
                EEthAddressType fromType = ethAddressAO.getType(fromAddress);
                EEthAddressType toType = ethAddressAO.getType(toAddress);

                if (EEthAddressType.M == fromType) { // fromAddress=M 提现
                    ethTransactionAO.withdrawNotice(ctqEthTransaction);
                    hashList.add(ctqEthTransaction.getHash());
                    if (EEthAddressType.X == toType) { // toAddress=X 充值
                        String code = ethTransactionAO
                            .chargeNotice(ctqEthTransaction);
                        if (StringUtils.isNotBlank(code)) {
                            ethTransactionAO.collection(
                                ctqEthTransaction.getTo(), code);
                        }
                    }
                    // hashList.add(ctqEthTransaction.getHash());
                } else if (EEthAddressType.X == toType) { // toAddress=X 充值
                    String code = ethTransactionAO
                        .chargeNotice(ctqEthTransaction);
                    if (StringUtils.isNotBlank(code)) {
                        ethTransactionAO.collection(ctqEthTransaction.getTo(),
                            code);
                    }
                    hashList.add(ctqEthTransaction.getHash());
                } else if (EEthAddressType.X == fromType
                        && EEthAddressType.W == toType) {
                    // fromAddress=X toAddress=W 归集
                    ethTransactionAO.collectionNotice(ctqEthTransaction);
                    hashList.add(ctqEthTransaction.getHash());
                } else if (EEthAddressType.M == toType) {
                    // toAddress=M 每日定存
                    ethTransactionAO.depositNotice(ctqEthTransaction);
                    hashList.add(ctqEthTransaction.getHash());
                } else if (EEthAddressType.W == fromType) {
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
                ctqBO.confirm(hashList);
            }
            logger.info("*****complete*****");
        }
    }
}
