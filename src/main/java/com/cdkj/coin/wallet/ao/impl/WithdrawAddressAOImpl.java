package com.cdkj.coin.wallet.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletUtils;

import com.cdkj.coin.wallet.ao.IWithdrawAddressAO;
import com.cdkj.coin.wallet.bo.IEthAddressBO;
import com.cdkj.coin.wallet.bo.IScAddressBO;
import com.cdkj.coin.wallet.bo.IWithdrawAddressBO;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.WithdrawAddress;
import com.cdkj.coin.wallet.enums.EBoolean;
import com.cdkj.coin.wallet.enums.ECoin;
import com.cdkj.coin.wallet.enums.EYAddressStatus;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.EBizErrorCode;
import com.cdkj.coin.wallet.siacoin.SiadClient;

@Service
public class WithdrawAddressAOImpl implements IWithdrawAddressAO {

    @Autowired
    private IWithdrawAddressBO withdrawAddressBO;

    @Autowired
    private IEthAddressBO ethAddressBO;

    @Autowired
    private IScAddressBO scAddressBO;

    @Override
    public String addWithdrawAddress(String currency, String address,
            String label, String userId, String isCerti) {
        if (ECoin.ETH.getCode().equals(currency)) {
            // 地址有效性校验
            if (!WalletUtils.isValidAddress(address)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(), "地址"
                        + address + "不符合以太坊规则，请仔细核对");
            }
            if (ethAddressBO.isEthAddressExist(address)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "Bcoin平台使用的地址，请仔细检查新增地址是否是您的" + ECoin.ETH + "私有地址");
            }
        } else if (ECoin.SC.getCode().equals(currency)) {
            // 地址有效性校验
            if (!SiadClient.verifyAddress(address)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(), "地址"
                        + address + "不符合Sia规则，请仔细核对");
            }
            if (scAddressBO.isScAddressExist(address)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "Bcoin平台使用的地址，请仔细检查新增地址是否是您的" + ECoin.SC + "私有地址");
            }
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "不支持的币种");
        }

        WithdrawAddress condition = new WithdrawAddress();
        condition.setCurrency(currency);
        condition.setUserId(userId);
        condition.setAddress(address);
        if (withdrawAddressBO.getTotalCount(condition) > 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "请勿重复添加地址");
        }

        String status = EYAddressStatus.NORMAL.getCode();
        if (EBoolean.YES.getCode().equals(isCerti)) {
            status = EYAddressStatus.CERTI.getCode();
        }

        return withdrawAddressBO.saveWithdrawAddress(currency, address, label,
            userId, status);
    }

    @Override
    public int editWithdrawAddress(WithdrawAddress data) {
        return withdrawAddressBO.refreshWithdrawAddress(data);
    }

    @Override
    public int dropWithdrawAddress(String code) {
        return withdrawAddressBO.removeWithdrawAddress(code);
    }

    @Override
    public Paginable<WithdrawAddress> queryWithdrawAddressPage(int start,
            int limit, WithdrawAddress condition) {
        return withdrawAddressBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<WithdrawAddress> queryWithdrawAddressList(
            WithdrawAddress condition) {
        return withdrawAddressBO.queryWithdrawAddressList(condition);
    }

    @Override
    public WithdrawAddress getWithdrawAddress(String code) {
        return withdrawAddressBO.getWithdrawAddress(code);
    }
}
