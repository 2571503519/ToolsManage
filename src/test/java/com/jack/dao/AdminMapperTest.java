package com.jack.dao;

import com.google.common.collect.Lists;
import com.jack.dao.admin.AdminMapper;
import com.jack.pojo.entity.Admin;
import com.jack.util.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Jackaroo Zhang on 2018/10/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class AdminMapperTest {

    private final Logger logger = LoggerFactory.getLogger(AdminMapperTest.class);

    @Autowired
    private AdminMapper mapper;

    @Test
    public void testSaveAdmin() {
        Admin admin = new Admin();
        admin.setAdminName("jackaroo");
        admin.setUsername("257015" + Math.random());
        admin.setPassword("257015" + Math.random());
        admin.setIdCard("152654646445646546546");
        admin.setResIds(Lists.asList(1L, 2L, new Long[] {3L, 4L}));
        admin.setDeptId(3L);
        admin.setState(State.AdminState.NORMAL);
        boolean res = mapper.saveAdmin(admin);
        logger.info("Test: Save a admin, Result: {}, adminId={}", res, admin.getAdminId());
    }

    @Test
    public void testFindAdminByPrimaryKey() {
        Admin admin = mapper.findAdminByPrimaryKey(1L);
        if (admin == null) logger.info("Test: admin is null");
        logger.info("Test: find a admin, Result: " + admin);
    }

    @Test
    public void testUpdateAdmin() {
        Admin admin = mapper.findAdminByPrimaryKey(1L);
        if (admin == null) logger.info("Test: admin is null");
        admin.setState(State.AdminState.FORBID);
        admin.setUsername("2570150.9032786105267970");
        boolean res = mapper.updateAdmin(admin);
        logger.info("Test: Update a admin, Result: {}", res);
    }

    @Test
    public void testDeleteAdmin() {
        boolean res = mapper.deleteAdmin(1L);
        logger.info("Test: Delete a admin, Result: {}", res);
    }

}
