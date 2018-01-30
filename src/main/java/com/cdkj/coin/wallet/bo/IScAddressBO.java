package com.cdkj.coin.wallet.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.EthAddress;
import com.cdkj.coin.wallet.enums.EEthAddressType;

public interface IScAddressBO extends IPaginableBO<EthAddress> {

    // 生成地址（有私钥）
    public String generateAddress(EEthAddressType type, String accountId);

    // 导入（保存）地址
    public String saveEthAddress(EEthAddressType type, String userId,
            String address, String label, String password, BigDecimal balance,
            Date availableDatetimeStart, Date availableDatetimeEnd,
            String status, String keystoreName, String keystoreContent);

    // 获取今日归集地址
    public EthAddress getWEthAddressToday();

    // 更新状态
    public int refreshStatus(EthAddress address, String status);

    public EthAddress getEthAddress(EEthAddressType type, String address);

    public EthAddress getEthAddressByUserId(String userId);

    public boolean isEthAddressExist(String address);

    public List<EthAddress> queryEthAddressList(EthAddress condition);

    public EthAddress getEthAddress(String code);

    public int abandonAddress(EthAddress ethAddress);

}
