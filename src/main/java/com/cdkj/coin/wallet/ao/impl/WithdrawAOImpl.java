package com.cdkj.coin.wallet.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.WalletUtils;

import com.cdkj.coin.wallet.ao.IScTransactionAO;
import com.cdkj.coin.wallet.ao.IWithdrawAO;
import com.cdkj.coin.wallet.bitcoin.BitcoinOfflineRawTxBuilder;
import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.bitcoin.BtcUtxo;
import com.cdkj.coin.wallet.bitcoin.OfflineTxInput;
import com.cdkj.coin.wallet.bitcoin.OfflineTxOutput;
import com.cdkj.coin.wallet.bitcoin.util.BtcBlockExplorer;
import com.cdkj.coin.wallet.bo.IAccountBO;
import com.cdkj.coin.wallet.bo.IBtcAddressBO;
import com.cdkj.coin.wallet.bo.IBtcUtxoBO;
import com.cdkj.coin.wallet.bo.ICtqBO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IEthTransactionBO;
import com.cdkj.coin.wallet.bo.IJourBO;
import com.cdkj.coin.wallet.bo.ISYSConfigBO;
import com.cdkj.coin.wallet.bo.IScAddressBO;
import com.cdkj.coin.wallet.bo.IScTransactionBO;
import com.cdkj.coin.wallet.bo.IWithdrawBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.common.AmountUtil;
import com.cdkj.coin.wallet.common.SysConstants;
import com.cdkj.coin.wallet.domain.Account;
import com.cdkj.coin.wallet.domain.Jour;
import com.cdkj.coin.wallet.domain.Withdraw;
import com.cdkj.coin.wallet.dto.res.XN802758Res;
import com.cdkj.coin.wallet.enums.EAddressType;
import com.cdkj.coin.wallet.enums.EBoolean;
import com.cdkj.coin.wallet.enums.EBtcUtxoRefType;
import com.cdkj.coin.wallet.enums.EBtcUtxoStatus;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.enums.EJourBizTypeUser;
import com.cdkj.coin.wallet.enums.EJourKind;
import com.cdkj.coin.wallet.enums.EMAddressStatus;
import com.cdkj.coin.wallet.enums.EWithdrawStatus;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.ethereum.EthTransaction;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.siacoin.CtqScTransaction;
import com.cdkj.coin.wallet.siacoin.Input;
import com.cdkj.coin.wallet.siacoin.Output;
import com.cdkj.coin.wallet.siacoin.ScAddress;
import com.cdkj.coin.wallet.siacoin.ScTransaction;
import com.cdkj.coin.wallet.siacoin.SiadClient;
import com.cdkj.coin.wallet.siacoin.Transaction;

@Service
public class WithdrawAOImpl implements IWithdrawAO {

    private static Logger logger = Logger.getLogger(WithdrawAOImpl.class);

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IWithdrawBO withdrawBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private ICtqBO ctqBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IScAddressBO scAddressBO;

    @Autowired
    private IEthTransactionBO ethTransactionBO;

    @Autowired
    private IScTransactionBO scTransactionBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IScTransactionAO scTransactionAO;

    @Autowired
    private IBtcAddressBO btcAddressBO;

    @Autowired
    private IBtcUtxoBO btcUtxoBO;

    @Autowired
    private BtcBlockExplorer btcBlockExplorer;

    @Override
    @Transactional
    public String applyOrder(String accountNumber, BigDecimal amount,
            BigDecimal fee, String payCardInfo, String payCardNo,
            String applyUser, String applyNote) {
        Account dbAccount = accountBO.getAccount(accountNumber);
        if (amount.compareTo(fee) == 0 || amount.compareTo(fee) == -1) {
            throw new BizException("xn000000", "提现金额需大于手续费");
        }
        if (ECoin.ETH.getCode().equals(dbAccount.getCurrency())) {
            if (!WalletUtils.isValidAddress(payCardNo)) {
                throw new BizException("xn000000", "提现地址不符合以太坊规则，请仔细核对");
            }
            List<String> typeList = new ArrayList<String>();
            typeList.add(EAddressType.X.getCode());
            typeList.add(EAddressType.M.getCode());
            typeList.add(EAddressType.W.getCode());

            EthAddress condition1 = new EthAddress();
            condition1.setAddress(payCardNo);
            condition1.setTypeList(typeList);

            if (ethAddressBO.getTotalCount(condition1) > 0) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "提现地址已经在本平台被使用，请仔细核对！");
            }
        } else if (ECoin.SC.getCode().equals(dbAccount.getCurrency())) {
            if (!SiadClient.verifyAddress(payCardNo)) {
                throw new BizException("xn000000", "提现地址不符合Sia规则，请仔细核对");
            }

            ScAddress condition1 = new ScAddress();
            condition1.setAddress(payCardNo);
            if (scAddressBO.getTotalCount(condition1) > 0) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "提现地址已经在本平台被使用，请仔细核对！");
            }
        }

        if (dbAccount.getAmount().subtract(dbAccount.getFrozenAmount())
            .compareTo(amount) == -1) {
            throw new BizException("xn000000", "可用余额不足");
        }

        // 判断本月是否次数已满，且现在只能有一笔取现未支付记录
        withdrawBO.doCheckTimes(dbAccount);

        // 生成取现订单
        String withdrawCode = withdrawBO.applyOrder(dbAccount, amount, fee,
            payCardInfo, payCardNo, applyUser, applyNote);
        // 冻结取现金额
        dbAccount = accountBO.frozenAmount(dbAccount, amount,
            EJourBizTypeUser.AJ_WITHDRAW_FROZEN.getCode(),
            EJourBizTypeUser.AJ_WITHDRAW_FROZEN.getValue(), withdrawCode);

        return withdrawCode;

    }

    @Override
    @Transactional
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote, String systemCode) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (!EWithdrawStatus.toApprove.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待审批状态，无法审批");
        }
        if (EBoolean.YES.getCode().equals(approveResult)) {
            approveOrderYES(data, approveUser, approveNote);
        } else {
            approveOrderNO(data, approveUser, approveNote);
        }
    }

    @Override
    @Transactional
    public void broadcast(String code, String mAddressCode, String approveUser) {
        // 获取取现订单详情
        Withdraw withdraw = withdrawBO.getWithdraw(code);
        Account account = accountBO.getAccount(withdraw.getAccountNumber());
        if (ECoin.ETH.getCode().equals(account.getCurrency())) {
            if (StringUtils.isBlank(mAddressCode)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "散取地址不能为空");
            }
            doEthBroadcast(withdraw, mAddressCode, approveUser);
        } else if (ECoin.SC.getCode().equals(account.getCurrency())) {
            doScBroadcast(withdraw, approveUser);
        } else if (ECoin.BTC.getCode().equals(account.getCurrency())) {
            doBtcBroadcast(withdraw, approveUser);
        }
    }

    // btc广播逻辑：
    // 1、验证参数(待验证)
    // 2、预估矿工费得到取现总金额(加找零算法)
    // 3、签名
    // 4、调用接口广播
    private void doBtcBroadcast(Withdraw withdraw, String approveUser) {

        BigDecimal minMinerFee = AmountUtil.toBtc(sysConfigBO
            .getBigDecimalValue(SysConstants.MIN_MINER_FEE_BTC));

        BigDecimal maxMinerFee = AmountUtil.toBtc(sysConfigBO
            .getBigDecimalValue(SysConstants.MAX_MINER_FEE_BTC));

        // 实际到账金额=取现金额-取现手续费
        BigDecimal realAmount = withdraw.getAmount()
            .subtract(withdraw.getFee());

        // 本次提现至少需要有这么多UTXO
        BigDecimal utxoMinCount = realAmount.add(minMinerFee);

        // 提现至用户的私有地址
        String withdrawAddress = withdraw.getPayCardNo();

        // 获取散取地址的UTXO总额，判断是否足够提现
        BigDecimal enableCount = btcUtxoBO
            .getTotalEnableUTXOCount(EAddressType.M);
        if (enableCount.compareTo(utxoMinCount) < 0) {
            // 相等或小于都应该是提现不成功，应为要有矿工费
            // 大于也不一定成功，因为矿工费不能太小，容易交易失败
            throw new BizException("xn0000", "提现账户余额不足");
        }

        // 足够提现，降序遍历可使用的M类地址UTXO，组装Input
        BitcoinOfflineRawTxBuilder rawTxBuilder = new BitcoinOfflineRawTxBuilder();
        BigDecimal shouldWithdrawCount = BigDecimal.ZERO;
        List<BtcUtxo> inputBtcUtxoList = new ArrayList<BtcUtxo>();
        int pageNum = 0;
        while (true) {
            BtcUtxo condition = new BtcUtxo();
            condition.setAddressType(EAddressType.M.getCode());
            condition.setStatus(EBtcUtxoStatus.ENABLE.getCode());
            condition.setOrder("count", "decs");
            Paginable<BtcUtxo> pageBtcUtxo = btcUtxoBO.getPaginable(pageNum,
                20, condition);// 默认每次20条
            List<BtcUtxo> list = pageBtcUtxo.getList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (BtcUtxo utxo : list) {
                    String txid = utxo.getTxid();
                    Integer vout = utxo.getVout();
                    // 应取现总额
                    shouldWithdrawCount = shouldWithdrawCount.add(utxo
                        .getCount());
                    BtcAddress btcAddress = btcAddressBO.getBtcAddress(
                        EAddressType.M, utxo.getAddress());
                    // 构造签名交易，输入
                    OfflineTxInput offlineTxInput = new OfflineTxInput(txid,
                        vout, utxo.getScriptPubKey(),
                        btcAddress.getPrivatekey());
                    rawTxBuilder.in(offlineTxInput);
                    inputBtcUtxoList.add(utxo);
                    if (shouldWithdrawCount.compareTo(utxoMinCount) > 0) {// 当大于取现金额时，跳出循环
                        break;
                    }
                }
            } else {
                break;
            }
            pageNum++;// 不够再遍历
        }
        // 组装Output，设置找零账户
        // 如何估算手续费，先预先给一个size,然后拿这个size进行签名
        // 对签名的数据进行解码，拿到真实大小，然后进行矿工费的修正
        int preSize = BitcoinOfflineRawTxBuilder.calculateSize(rawTxBuilder
            .getSize().intValue(), 1);
        int feePerByte = btcBlockExplorer.getFee();
        // 计算出手续费
        int preFee = preSize * feePerByte;

        // 构造输出
        OfflineTxOutput offlineTxOutput = new OfflineTxOutput(withdrawAddress,
            AmountUtil.fromBtc(shouldWithdrawCount.subtract(BigDecimal
                .valueOf(preFee))));
        rawTxBuilder.out(offlineTxOutput);

        // 计算需要的找零, 现在是随机找零到一个提现地址
        BigDecimal backCount = shouldWithdrawCount.subtract(realAmount);
        if (backCount.compareTo(BigDecimal.ZERO) > 0) {
            String backAddress = inputBtcUtxoList.get(0).getAddress();
            OfflineTxOutput backOutput = new OfflineTxOutput(backAddress,
                AmountUtil.fromBtc(backCount));
            rawTxBuilder.out(backOutput);
        }
        try {
            String signResult = rawTxBuilder.offlineSign();
            // 广播
            String trueTxid = btcBlockExplorer.broadcastRawTx(signResult);
            if (trueTxid != null) {
                if (CollectionUtils.isNotEmpty(inputBtcUtxoList)) {
                    for (BtcUtxo data : inputBtcUtxoList) {
                        btcUtxoBO.refreshBroadcast(data, EBtcUtxoStatus.USING,
                            EBtcUtxoRefType.WITHDRAW, withdraw.getCode());
                    }
                }
                logger.info("广播成功：交易hash=" + trueTxid);
                withdrawBO.broadcastOrder(withdraw, trueTxid, approveUser);
            } else {
                throw new BizException(EBizErrorCode.UTXO_COLLECTION_ERROR);
            }
        } catch (Exception e) {
            throw new BizException("-100", e.getMessage());
        }
    }

    private void doEthBroadcast(Withdraw withdraw, String mAddressCode,
            String approveUser) {
        // 获取今日散取地址
        EthAddress mEthAddress = ethAddressBO.getEthAddress(mAddressCode);
        if (!EAddressType.M.getCode().endsWith(mEthAddress.getType())) {
            throw new BizException("无效的ETH地址，只有散取地址才能进行取现广播！");
        }
        if (EMAddressStatus.IN_USE.getCode().equals(mEthAddress.getStatus())) {
            throw new BizException("该散取地址正在广播使用，请稍后再试！");
        }
        if (EMAddressStatus.INVALID.getCode().equals(mEthAddress.getStatus())) {
            throw new BizException("该散取地址已被弃用！");
        }

        String address = mEthAddress.getAddress();
        EthAddress secret = ethAddressBO.getEthAddressSecret(mEthAddress
            .getCode());

        // 实际到账金额=取现金额-取现手续费
        BigDecimal realAmount = withdraw.getAmount()
            .subtract(withdraw.getFee());

        // 预估矿工费用
        BigDecimal gasPrice = ethTransactionBO.getGasPrice();
        BigDecimal gasUse = new BigDecimal(21000);
        BigDecimal txFee = gasPrice.multiply(gasUse);

        // 查询散取地址余额
        BigDecimal balance = ethAddressBO.getEthBalance(address);
        logger.info("地址" + address + "余额：" + balance.toString());
        if (balance.compareTo(realAmount.add(txFee)) < 0) {
            throw new BizException("xn625000", "散取地址" + address
                    + "余额不足以支付提现金额和矿工费！");
        }
        // 广播
        if (!WalletUtils.isValidAddress(withdraw.getPayCardNo())) {
            throw new BizException("xn625000", "无效的取现地址："
                    + withdraw.getPayCardInfo());
        }
        String txHash = ethTransactionBO.broadcast(address, secret,
            withdraw.getPayCardNo(), realAmount);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "交易签名失败，请仔细检查散取地址是否符合提现要求");
        }
        logger.info("广播成功：交易hash=" + txHash);
        withdrawBO.broadcastOrder(withdraw, txHash, approveUser);

        // 修改取现地址状态为广播中
        ethAddressBO.refreshStatus(mEthAddress,
            EMAddressStatus.IN_USE.getCode());

    }

    private void doScBroadcast(Withdraw withdraw, String approveUser) {

        // 实际到账金额=取现金额-取现手续费
        BigDecimal realAmount = withdraw.getAmount()
            .subtract(withdraw.getFee());
        // 默认矿工费用
        BigDecimal txFee = new BigDecimal("22500000000000000000000");
        // 查询钱包余额
        BigDecimal balance = SiadClient.getSiacoinBalance();

        logger.info("Sia钱包余额：" + balance.toString());
        if (balance.compareTo(realAmount.add(txFee)) < 0) {
            throw new BizException("xn625000", "Sia钱包余额不足以支付提现金额和矿工费！");
        }
        // 广播
        if (!SiadClient.verifyAddress(withdraw.getPayCardNo())) {
            throw new BizException("xn625000", "无效的取现地址："
                    + withdraw.getPayCardInfo());
        }
        String txHash = SiadClient.sendSingleAddress(withdraw.getPayCardNo(),
            realAmount);
        if (StringUtils.isBlank(txHash)) {
            throw new BizException("xn625000", "交易广播失败");
        }
        logger.info("广播成功：交易hash=" + txHash);
        withdrawBO.broadcastOrder(withdraw, txHash, approveUser);

    }

    @Override
    @Transactional
    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder, String systemCode) {
        Withdraw data = withdrawBO.getWithdraw(code);
        if (!EWithdrawStatus.Approved_YES.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是待支付状态，无法支付");
        }
        if (EBoolean.YES.getCode().equals(payResult)) {
            payOrderYES(data, payUser, payNote, channelOrder);
        } else {
            payOrderNO(data, payUser, payNote, channelOrder);
        }
    }

    private void approveOrderYES(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_YES,
            approveUser, approveNote);
    }

    private void approveOrderNO(Withdraw data, String approveUser,
            String approveNote) {
        withdrawBO.approveOrder(data, EWithdrawStatus.Approved_NO, approveUser,
            approveNote);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeUser.AJ_WITHDRAW_UNFROZEN.getCode(), "取现失败退回",
            data.getCode());

    }

    private void payOrderNO(Withdraw data, String payUser, String payNote,
            String payCode) {
        withdrawBO.payOrder(data, EWithdrawStatus.Pay_NO, payUser, payNote,
            payCode, payCode, BigDecimal.ZERO);
        Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // 释放冻结流水
        accountBO.unfrozenAmount(dbAccount, data.getAmount(),
            EJourBizTypeUser.AJ_WITHDRAW.getCode(), "取现失败退回", data.getCode());
    }

    private void payOrderYES(Withdraw data, String payUser, String payNote,
            String payCode) {
        // withdrawBO.payOrder(data, EWithdrawStatus.Pay_YES, payUser, payNote,
        // payCode, payCode, BigDecimal.ZERO);
        // Account dbAccount = accountBO.getAccount(data.getAccountNumber());
        // // 先解冻，然后扣减余额
        // accountBO.unfrozenAmount(dbAccount, data.getAmount(),
        // EJourBizTypeUser.AJ_WITHDRAW.getCode(),
        // EJourBizTypeUser.AJ_WITHDRAW.getValue(), data.getCode());
        // accountBO.changeAmount(dbAccount.getAccountNumber(),
        // EChannelType.Offline, null, null, data.getCode(),
        // EJourBizTypeUser.AJ_WITHDRAW.getCode(),
        // EJourBizTypeUser.AJ_WITHDRAW.getValue(), data.getAmount());
        // Account account = accountBO.getAccount(data.getAccountNumber());
        // if (ECurrency.CNY.getCode().equals(account.getCurrency())) {
        // // 冷钱包减钱
        // accountBO.changeAmount(
        // ESystemAccount.SYS_ACOUNT_ETH_COLD.getCode(),
        // EChannelType.Offline, null, null, data.getCode(),
        // EJourBizTypeCold.AJ_PAY.getCode(), "ETH取现", data.getAmount()
        // .negate());
        // }
    }

    @Override
    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition) {
        Paginable<Withdraw> page = withdrawBO.getPaginable(start, limit,
            condition);
        return page;
    }

    @Override
    public List<Withdraw> queryWithdrawList(Withdraw condition) {
        List<Withdraw> list = withdrawBO.queryWithdrawList(condition);
        return list;
    }

    @Override
    public Withdraw getWithdraw(String code, String systemCode) {
        Withdraw withdraw = withdrawBO.getWithdraw(code);
        return withdraw;
    }

    // /**
    // * 取现申请检查，验证参数，返回手续费
    // * @param accountType
    // * @param amount
    // * @param systemCode
    // * @param companyCode
    // * @return
    // * @create: 2017年5月17日 上午7:53:01 xieyj
    // * @history:
    // */
    // private BigDecimal doGetFee(String accountType, BigDecimal amount,
    // String systemCode, String companyCode) {
    // Map<String, String> argsMap = sysConfigBO.getConfigsMap(systemCode,
    // companyCode);
    // String qxfl = null;
    // if (EAccountType.Customer.getCode().equals(accountType)) {
    // qxfl = SysConstants.CUSERQXFL;
    // } else {// 暂定其他账户类型不收手续费
    // return BigDecimal.ZERO;
    // }
    // // 取现单笔最大金额
    // String qxDbzdjeValue = argsMap.get(SysConstants.QXDBZDJE);
    // if (StringUtils.isNotBlank(qxDbzdjeValue)) {
    // BigDecimal qxDbzdje = BigDecimal.valueOf(Double
    // .valueOf(qxDbzdjeValue));
    // if (amount.compareTo(qxDbzdje) == 1) {
    // throw new BizException("xn000000", "取现单笔最大金额不能超过"
    // + qxDbzdjeValue + "元。");
    // }
    // }
    // String feeRateValue = argsMap.get(qxfl);
    // Double feeRate = 0D;
    // if (StringUtils.isNotBlank(feeRateValue)) {
    // feeRate = Double.valueOf(feeRateValue);
    // }
    // return AmountUtil.mul(amount, feeRate);
    // }

    @Override
    public XN802758Res getWithdrawCheckInfo(String code) {

        XN802758Res res = new XN802758Res();

        // 取现订单详情
        Withdraw withdraw = withdrawBO.getWithdraw(code);
        Account account = accountBO.getAccount(withdraw.getAccountNumber());

        // 取现对应流水记录
        Jour jour = new Jour();
        jour.setRefNo(withdraw.getCode());
        jour.setKind(EJourKind.BALANCE.getCode());
        List<Jour> jourList = jourBO.queryJourList(jour);

        if (ECoin.ETH.getCode().equals(account.getCurrency())) {
            // 取现对应广播记录
            EthTransaction ethTransaction = new EthTransaction();
            ethTransaction.setRefNo(withdraw.getCode());
            List<EthTransaction> resultList = ethTransactionBO
                .queryEthTransactionList(ethTransaction);
            res.setEthTransList(resultList);
        } else if (ECoin.SC.getCode().equals(account.getCurrency())) {
            // 取现对应广播记录
            ScTransaction scTransaction = new ScTransaction();
            scTransaction.setRefNo(withdraw.getCode());
            List<ScTransaction> resultList = scTransactionBO
                .queryScTransactionList(scTransaction);
            res.setScTransList(resultList);
        }

        res.setWithdraw(withdraw);
        res.setJourList(jourList);

        return res;
    }

    @Override
    public BigDecimal getTotalWithdraw(ECoin coin) {
        return withdrawBO.getTotalWithdraw(coin);
    }

    public void doCheckBroadcastStatus() {
        while (true) {
            Withdraw condition = new Withdraw();
            condition.setStatus(EWithdrawStatus.Broadcast.getCode());
            List<Withdraw> withdraws = withdrawBO.queryWithdrawList(condition);
            if (CollectionUtils.isEmpty(withdraws)) {
                break;
            }
            for (Withdraw withdraw : withdraws) {
                if (ECoin.SC.getCode().equals(withdraw.getCurrency())) {
                    Transaction tx = SiadClient.getTransaction(withdraw
                        .getChannelOrder());
                    if (tx != null && tx.getInputs().size() == 1
                            && tx.getOutputs().size() == 2) {
                        logger
                            .info("扫描到SC取现广播成功，开始处理取现订单" + withdraw.getCode());
                        Input fromInfo = tx.getInputs().get(0);
                        Output toInfo = tx.getOutputs().get(0);
                        Output minerInfo = tx.getOutputs().get(1);

                        CtqScTransaction scTx = new CtqScTransaction();
                        scTx.setTransactionid(tx.getTransactionid());
                        scTx.setConfirmationheight(tx.getConfirmationheight());
                        scTx.setConfirmationtimestamp(tx
                            .getConfirmationtimestamp());
                        scTx.setFrom(fromInfo.getRelatedaddress());
                        scTx.setTo(toInfo.getRelatedaddress());
                        scTx.setValue(toInfo.getValue());
                        scTx.setMinerfee(minerInfo.getValue());

                        scTransactionAO.withdrawNotice(scTx);
                    }
                }
            }

        }
    }
}
