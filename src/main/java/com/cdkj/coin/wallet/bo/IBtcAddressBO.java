package com.cdkj.coin.wallet.bo;

import java.util.List;

import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.enums.EAddressType;

public interface IBtcAddressBO extends IPaginableBO<BtcAddress> {

    // 生成地址（有私钥）
    public String generateAddress(EAddressType type, String userId,
            String accountNumber, String updater, String remark);

    // 导入（保存）地址
    public String saveBtcAddress(EAddressType type, String address,
            String privatekey, String userId, String accountNumber,
            String status, String updater, String remark);

    // 获取今日归集地址
    public BtcAddress getWBtcAddressToday();

    // 更新状态
    public int refreshStatus(BtcAddress address, String status);

    public BtcAddress getBtcAddress(EAddressType type, String address);

    public BtcAddress getBtcAddressByUserId(String userId);

    public BtcAddress getBtcAddressByAccountNumber(String accountNumber);

    public boolean isBtcAddressExist(String address);

    public List<BtcAddress> queryBtcAddressList(BtcAddress condition);

    public BtcAddress getBtcAddress(String code);

    public int abandonAddress(BtcAddress adddress, String updater, String remark);

}
