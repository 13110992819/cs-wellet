package com.cdkj.coin.wallet.bitcoin.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cdkj.coin.wallet.bitcoin.original.BTCFee;
import com.cdkj.coin.wallet.bitcoin.original.BTCOriginalTx;
import com.cdkj.coin.wallet.bitcoin.original.BTCTXs;
import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;

@Service
public class BtcBlockExplorer {

    @Autowired
    private ISYSConfigBO sysConfigBO;

    private static OkHttpClient okHttpClient = new OkHttpClient();

    private Integer maxFeePerByteCanAccept = 200;// 平台可接受最大交易手续费

    @Nullable
    public BTCOriginalTx queryTxHash(String txid) {

        try {

            String txURL = this.baseApiURL() + "/tx/" + txid;
            String jsonStr = this.get(txURL);
            if (jsonStr == null) {
                return null;
            }
            return com.alibaba.fastjson.JSON.parseObject(jsonStr,
                BTCOriginalTx.class);

        } catch (Exception e) {

            throw new BizException(EBizErrorCode.BLOCK_GET_ERROR);

        }

    }

    public Integer getFee() {

        String jsonStr = this.get(this.feeUrl());
        if (jsonStr == null) {
            throw new BizException(EBizErrorCode.BLOCK_GET_ERROR);
        }

        try {

            BTCFee fee = JSON.parseObject(jsonStr, BTCFee.class);
            // 应该读取一个配置值，获取手续费如果大于这个值，就取这个值
            Integer maxFeePerByte = maxFeePerByteCanAccept;
            Integer fastFee = fee.getHalfHourFee();

            return fastFee > maxFeePerByte ? maxFeePerByte : fastFee;

        } catch (Exception e) {

            throw new BizException(EBizErrorCode.TX_FEE_ERROR);

        }

    }

    /**
     * 返回
     *
     * @param blockHeight
     * @param pageNum
     * @return
     */
    public BTCTXs getBlockTxs(Long blockHeight, Integer pageNum) {

        String jsonStr = this.get(this.blockTxsUrl(blockHeight, pageNum));
        BTCTXs btctXs = JSON.parseObject(jsonStr, BTCTXs.class);
        return btctXs;

    }

    private String blockTxsUrl(Long blockHeight, Integer pageNum) {

        String blockHassh = this.blockHash(blockHeight);
        return this.baseApiURL() + "/txs/?block=" + blockHassh + "&pageNum="
                + pageNum;

    }

    private String blockHash(Long blockHeight) {

        String requestUrl = this.baseApiURL() + "/block-index/" + blockHeight;
        String jsonStr = this.get(requestUrl);
        Map map = JSONObject.parseObject(jsonStr, Map.class);
        return (String) map.get("blockHash");

    }

    private String broadcastRawTxUrl() {

        return this.baseApiURL() + "/tx/send";

    }

    public Integer getMaxFeePerByteCanAccept() {
        return maxFeePerByteCanAccept;
    }

    /**
     * 成功返回 txid, 失败返回null
     *
     * @param rawTx
     * @return
     */
    @Nullable
    public String broadcastRawTx(String rawTx) {

        try {

            // 2.进行广播
            FormBody formBody = new FormBody.Builder().add("rawtx", rawTx)
                .build();
            Request request = new Request.Builder().post(formBody)
                .url(this.broadcastRawTxUrl()).build();
            //
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            if (response.code() == 200) {

                String jsonStr = response.body().string();
                Map map = JSON.parseObject(jsonStr, HashMap.class);
                String trueTxid = (String) map.get("txid");
                if (trueTxid == null && trueTxid.length() <= 0) {

                    return null;

                } else {

                    return trueTxid;
                }

            } else {

                return null;

            }

        } catch (Exception e) {

            return null;
        }

    }

    //
    private String feeUrl() {
        // {"fastestFee":250,"halfHourFee":240,"hourFee":130}
        return "https://bitcoinfees.earn.com/api/v1/fees/recommended";

    }

    private String get(String url) throws BizException {

        // 200 ok
        // 404 如果没有
        Request req = new Request.Builder().get().url(url).build();
        try {

            Call call = okHttpClient.newCall(req);
            Response response = call.execute();

            if (response.code() == 200) {

                return response.body().string();

            } else if (response.code() == 404) {

                return null;

            } else {

                throw new BizException(EBizErrorCode.BLOCK_GET_ERROR);

            }

        } catch (Exception e) {

            throw new BizException(EBizErrorCode.BLOCK_GET_ERROR);

        }

    }

    private String baseApiURL() {
        return "https://testnet.blockexplorer.com/api";
    }

}
