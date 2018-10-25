package com.jack.service;

import com.google.common.collect.Lists;
import com.jack.config.RootConfig;
import com.jack.pojo.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Jackaroo Zhang on 2018/10/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class AdminServiceTest {

    private final Logger logger = LoggerFactory.getLogger(AdminServiceTest.class);

    @Autowired
    private AdminService service;

    @Test
    public void testAssignRolesForAdmin() {
        Admin admin = new Admin();
        admin.setAdminId(1L);
        boolean result = service.assignRolesForAdmin(admin, Lists.asList(1L, 2L, new Long[]{3L, 4L}));
        logger.info("Test: assign roles for admin, Result: " + result);
    }

}


