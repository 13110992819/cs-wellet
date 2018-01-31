package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.EthAddress;
import com.cdkj.coin.wallet.enums.EAddressType;

public interface IEthAddressBO extends IPaginableBO<EthAddress> {

    // 生成地址（有私钥）
    public String generateAddress(EAddressType type, String userId,
            String accountNumber);

    // 导入（保存）地址
    public String saveEthAddress(EAddressType type, String userId,
            String accountNumber, String address, String password,
            BigDecimal balance, Date availableDatetimeStart,
            Date availableDatetimeEnd, String status, String keystoreName,
            String keystoreContent);

    // 获取今日归集地址
    public EthAddress getWEthAddressToday();

    // 查询以太坊地址余额
    public BigDecimal getEthBalance(String address);

    // 更新余额
    public int refreshBalance(EthAddress address);

    // 更新状态
    public int refreshStatus(EthAddress address, String status);

    public EthAddress getEthAddress(EAddressType type, String address);

    public EthAddress getEthAddressByUserId(String userId);

    public EthAddress getEthAddressByAccountNumber(String accountNumber);

    public boolean isEthAddressExist(String address);

    public EthAddress getEthAddressSecret(String code);

    public List<EthAddress> queryEthAddressList(EthAddress condition);

    public EthAddress getEthAddress(String code);

    public int abandonAddress(EthAddress ethAddress);

    public BigDecimal getTotalBalance(EAddressType type);

    public List<EthAddress> queryManualCollectionAddressPage(
            BigDecimal balanceStart, int start, int limit);

}
