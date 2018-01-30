/**
 * @Title IEthAddressAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:38:33 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.ao;

import java.math.BigDecimal;
import java.util.Date;

import com.cdkj.coin.wallet.bo.base.Paginable;
import com.cdkj.coin.wallet.domain.EthAddress;
import com.cdkj.coin.wallet.enums.EEthAddressType;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:38:33 
 * @history:
 */
public interface IEthAddressAO {

    // 新增用户地址
    public void addEthAddress(String address, String label, String userId,
            String smsCaptcha, String isCerti, String tradePwd,
            String googleCaptcha);

    // 弃用地址
    public void abandonAddress(String code);

    // 根据地址获取地址类型
    public EEthAddressType getType(String address);

    // 生成散取️地址（有私钥）
    public String generateMAddress();

    // 导入估计地址（无私钥）
    public String importWAddress(String address, Date availableDatetimeStart,
            Date availableDatetimeEnd);

    public Paginable<EthAddress> queryEthAddressPage(int start, int limit,
            EthAddress condition);

    public EthAddress getEthAddress(String code);

    public BigDecimal getTotalBalance(EEthAddressType type);
}
