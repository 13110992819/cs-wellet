package com.cdkj.coin.wallet.ao;

import com.cdkj.coin.wallet.bitcoin.BtcAddress;
import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.enums.EAddressType;

/**
 * @author: xieyj 
 * @since: 2018年2月7日 下午3:22:30 
 * @history:
 */
public interface IBtcAddressAO {

    // 导入归集地址
    public String importWAddress(String address, String updater, String remark);

    // 弃用地址
    public void abandonAddress(String code, String updater, String remark);

    // 根据地址获取地址类型
    public EAddressType getType(String address);

    // 生成散取️地址
    public String generateMAddress(String updater, String remark);

    public Paginable<BtcAddress> queryBtcAddressPage(int start, int limit,
            BtcAddress condition);

    public BtcAddress getBtcAddress(String code);

}
