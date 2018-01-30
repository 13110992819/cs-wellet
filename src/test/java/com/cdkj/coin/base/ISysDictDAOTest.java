package com.cdkj.coin.base;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.cdkj.coin.wallet.dao.ISYSDictDAO;
import com.cdkj.coin.wallet.domain.SYSDict;

public class ISysDictDAOTest extends ADAOTest {

    @SpringBeanByType
    private ISYSDictDAO sysDictDAO;

    @Test
    public void insert() {
        SYSDict data = new SYSDict();
        data.setType("1");
        data.setParentKey("parentKey");
        data.setDkey("dkey");
        data.setDvalue("dvalue");

        data.setUpdater("updater");
        data.setUpdateDatetime(new Date());
        data.setRemark("remark");
        data.setCompanyCode("companyCode");
        data.setSystemCode("systemCode");
        sysDictDAO.insert(data);
        logger.info("insert : {}", data.getId());

    }

    @Test
    public void select() {
        SYSDict condition = new SYSDict();
        condition.setId(1L);
        SYSDict data = sysDictDAO.select(condition);
        logger.info("select : {}", data.getId());
    }

    @Test
    public void selectList() {
        SYSDict condition = new SYSDict();
        condition.setType("0");
        condition.setParentKey("A");
        Long count = sysDictDAO.selectTotalCount(condition);
        logger.info("selectList : {}", count);
    }


}
