/**
 * @Title SYSDictDAOImpl.java 
 * @Package com.xnjr.moom.dao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 上午10:22:32 
 * @version V1.0   
 */
package com.cdkj.coin.wallet.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.coin.wallet.dao.ISYSDictDAO;
import com.cdkj.coin.wallet.dao.base.support.AMybatisTemplate;
import com.cdkj.coin.wallet.domain.SYSDict;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 上午10:22:32 
 * @history:
 */
@Repository("sysDictDAOImpl")
public class SYSDictDAOImpl extends AMybatisTemplate implements ISYSDictDAO {

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(SYSDict data) {
        return super.insert(NAMESPACE.concat("insert_sysDict"), data);
    }

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(SYSDict data) {
        return super.delete(NAMESPACE.concat("delete_sysDict"), data);
    }

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public SYSDict select(SYSDict condition) {
        return super.select(NAMESPACE.concat("select_sysDict"), condition,
            SYSDict.class);
    }

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(SYSDict condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_sysDict_count"),
            condition);
    }

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<SYSDict> selectList(SYSDict condition) {
        return super.selectList(NAMESPACE.concat("select_sysDict"), condition,
            SYSDict.class);
    }

    /** 
     * @see com.cdkj.coin.wallet.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<SYSDict> selectList(SYSDict condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_sysDict"), start,
            count, condition, SYSDict.class);
    }

    /**
     * @see com.cdkj.coin.wallet.dao.ISYSDictDAO#update(com.cdkj.coin.wallet.domain.SYSDict)
     */
    @Override
    public int update(SYSDict data) {
        return super.update(NAMESPACE.concat("update_sysDict"), data);
    }

}
