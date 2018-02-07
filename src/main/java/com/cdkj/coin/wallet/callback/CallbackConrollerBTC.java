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

import com.cdkj.coin.wallet.ao.IBtcAddressAO;
import com.cdkj.coin.wallet.ao.IBtcUtxoAO;
import com.cdkj.coin.wallet.bitcoin.CtqBtcUtxo;
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.ECtqBtcUtxoStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月26日 下午1:44:16 
 * @history:
 */
@Controller
public class CallbackConrollerBTC {

    private static Logger logger = Logger.getLogger(CallbackConrollerBTC.class);

    @Autowired
    IBtcAddressAO btcAddressAO;

    @Autowired
    IBtcUtxoAO btcUtxoAO;

    @Autowired
    ICtqBO ctqBO;

    private CtqBtcUtxo ctqBtcUtxo;

    // BTC交易通知
    @RequestMapping("/btc/tx/notice")
    public synchronized void doEthCallback(HttpServletRequest request,
            HttpServletResponse response) {
        List<Long> hashList = new ArrayList<Long>();
        try {
            logger.info("*****比特币交易通知开始*****");
            logger.info(request.getParameter("btcUtxoList"));
            String txJson = request.getParameter("btcUtxoList");
            Gson gson = new Gson();
            List<CtqBtcUtxo> list = gson.fromJson(txJson,
                new TypeToken<List<CtqBtcUtxo>>() {
                }.getType());

            for (CtqBtcUtxo ctqBtcUtxo : list) {

                // UTXO关联的地址
                String address = ctqBtcUtxo.getAddress();

                // UTXO关联的地址类型
                EAddressType addressType = btcAddressAO.getType(address);

                // 橙提取UTXO的状态
                String ctqBtcUtxoStatus = ctqBtcUtxo.getStatus();

                // addressType=X
                // 1、ctqBtcUtxoStatus为输出未推送 本地UTXO未落地 则说明是分发地址充值
                // 2、ctqBtcUtxoStatus为输入未推送 本地UTXO已落地 则说明是归集

                // addressType=M
                // 1、ctqBtcUtxoStatus为输出未推送 本地UTXO未落地 则说明是散取地址取现定存
                // 2、ctqBtcUtxoStatus为输入未推送 本地UTXO已落地 则说明是取现

                if (EAddressType.X == addressType) {
                    if (ECtqBtcUtxoStatus.OUT_UN_PUSH.getCode().equals(
                        ctqBtcUtxo)) {
                        String code = btcUtxoAO.chargeNotice(ctqBtcUtxo);
                        if (StringUtils.isNotBlank(code)) {
                            btcUtxoAO.collection(code);
                        }
                        hashList.add(ctqBtcUtxo.getHash());
                    }
                } else if (EAddressType.M == addressType) {
                    btcUtxoAO.withdrawNotice(ctqBtcUtxo);
                    hashList.add(ctqBtcUtxo.getId());
                } else if (EAddressType.X == fromType
                        && EAddressType.W == toType) {
                    // fromAddress=X toAddress=W 归集
                    btcUtxoAO.collectionNotice(ctqBtcUtxo);
                    hashList.add(ctqBtcUtxo.getHash());
                } else if (EAddressType.M == toType) {
                    // toAddress=M 每日定存
                    btcUtxoAO.depositNotice(ctqBtcUtxo);
                    hashList.add(ctqBtcUtxo.getHash());
                } else if (EAddressType.W == fromType) {
                    // fromAddress=W 每日转移
                    hashList.add(ctqBtcUtxo.getHash());
                }

                logger.info("处理交易：" + ctqBtcUtxo.getHash());
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
}
