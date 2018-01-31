package com.cdkj.coin.wallet.bo;

import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.ScAddress;
import com.cdkj.coin.wallet.enums.EAddressType;

public interface IScAddressBO extends IPaginableBO<ScAddress> {

    // 生成地址（有私钥）
    public String generateAddress(EAddressType type, String userId,
            String accountNumber, String updater, String remark);

    // 导入（保存）地址
    public String saveScAddress(EAddressType type, String address,
            String userId, String accountNumber, String status, String updater,
            String remark);

    // 更新状态
    public int refreshStatus(ScAddress address, String status);

    public ScAddress getScAddress(EAddressType type, String address);

    public ScAddress getScAddressByUserId(String userId);

    public ScAddress getScAddressByAccountNumber(String accountNumber);

    public boolean isScAddressExist(String address);

    public List<ScAddress> queryScAddressList(ScAddress condition);

    public ScAddress getScAddress(String code);

    public int abandonAddress(ScAddress scAddress);

}
