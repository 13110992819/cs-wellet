/**
 * @Title SYSConfigDAOImpl.java 
 * @Package com.xnjr.moom.dao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 上午10:23:11 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.dao.ISYSConfigDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.wallet.domain.SYSConfig;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 上午10:23:11 
 * @history:
 */
@Repository("sysConfigDAOImpl")
public class SYSConfigDAOImpl extends AMybatisTemplate implements ISYSConfigDAO {

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(SYSConfig data) {
        return super.insert(NAMESPACE.concat("insert_sysConfig"), data);
    }

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(SYSConfig data) {
        // TODO Auto-generated method stub
        return 0;
    }

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public SYSConfig select(SYSConfig condition) {
        return super.select(NAMESPACE.concat("select_sysConfig"), condition,
            SYSConfig.class);
    }

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(SYSConfig condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_sysConfig_count"), condition);
    }

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<SYSConfig> selectList(SYSConfig condition) {
        return super.selectList(NAMESPACE.concat("select_sysConfig"),
            condition, SYSConfig.class);
    }

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<SYSConfig> selectList(SYSConfig condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_sysConfig"), start,
            count, condition, SYSConfig.class);
    }

    @Override
    public int updateValue(SYSConfig data) {
        return super.update(NAMESPACE.concat("update_value"), data);
    }

}
