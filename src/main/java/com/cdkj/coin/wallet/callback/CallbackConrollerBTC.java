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
        List<CtqBtcUtxo> utxoList = new ArrayList<CtqBtcUtxo>();
        try {

            logger.info("*****比特币交易通知开始*****");
            logger.info(request.getParameter("btcUtxolist"));

            String txJson = request.getParameter("btcUtxolist");
            Gson gson = new Gson();
            List<CtqBtcUtxo> list = gson.fromJson(txJson,
                new TypeToken<List<CtqBtcUtxo>>() {
                }.getType());

            for (CtqBtcUtxo ctqBtcUtxo : list) {

                logger.info("处理交易：" + ctqBtcUtxo.getRefNo());

                // UTXO关联的地址
                String address = ctqBtcUtxo.getAddress();

                // UTXO关联的地址类型
                EAddressType addressType = btcAddressAO.getType(address);

                // 橙提取UTXO的状态
                String ctqBtcUtxoStatus = ctqBtcUtxo.getStatus();

                // addressType=X 关联地址是分发地址
                if (EAddressType.X == addressType) {
                    // 1、ctqBtcUtxoStatus为输出未推送 则说明是分发地址充值通知
                    if (ECtqBtcUtxoStatus.OUT_UN_PUSH.getCode().equals(
                        ctqBtcUtxoStatus)) {
                        String code = btcUtxoAO.chargeNotice(ctqBtcUtxo);
                        if (StringUtils.isNotBlank(code)) {
                            btcUtxoAO.collection(code);
                        }
                        utxoList.add(ctqBtcUtxo);
                    } else
                    // 2、ctqBtcUtxoStatus为输入未推送 则说明是归集通知
                    if (ECtqBtcUtxoStatus.IN_UN_PUSH.getCode().equals(
                        ctqBtcUtxoStatus)) {
                        btcUtxoAO.collectionNotice(ctqBtcUtxo);
                        utxoList.add(ctqBtcUtxo);
                    }
                } else
                // addressType=M 关联地址是散取地址
                if (EAddressType.M == addressType) {
                    // 1、ctqBtcUtxoStatus为输出未推送 则说明是散取地址取现定存通知
                    if (ECtqBtcUtxoStatus.OUT_UN_PUSH.getCode().equals(
                        ctqBtcUtxoStatus)) {
                        btcUtxoAO.depositNotice(ctqBtcUtxo);
                        utxoList.add(ctqBtcUtxo);
                    } else
                    // 2、ctqBtcUtxoStatus为输入未推送 则说明是取现通知
                    if (ECtqBtcUtxoStatus.IN_UN_PUSH.getCode().equals(
                        ctqBtcUtxoStatus)) {
                        btcUtxoAO.withdrawNotice(ctqBtcUtxo);
                        utxoList.add(ctqBtcUtxo);
                    }
                }
            }
            logger.info("*****业务处理完成*****");
        } catch (Exception e) {
            logger.info("交易通知异常,原因：" + e.getMessage());
        } finally {
            logger.info("*****橙提取交易处理,交易个数为" + utxoList.size() + "*****");
            if (CollectionUtils.isNotEmpty(utxoList)) {
                ctqBO.confirmBTC(utxoList);
            }
            logger.info("*****complete*****");
        }
    }
}
