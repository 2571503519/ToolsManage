package com.jack.service;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.jack.config.RootConfig;
import com.jack.exception.PageQueryException;
import com.jack.pojo.entity.Role;
import com.jack.pojo.entity.RoleResource;
import com.jack.util.PageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class RoleServiceTest {

    private final Logger logger = LoggerFactory.getLogger(RoleServiceTest.class);

    @Autowired
    private RoleService service;

    @Test
    public void testFindRoleByRoleId() {
        Role role = service.findRoleByRoleId(Optional.of(1L));
        if (role != null) {
            logger.info("Test: find role by roleId, Result: " + role);
        } else {
            logger.info("Result is null");
        }
    }

    @Test
    public void testFindRoleResourceList() {
        PageQuery pageQuery = null;
        RoleResource roleResource = null;
        try {
            pageQuery = PageQuery.build(1, 3);
            roleResource = new RoleResource();
            roleResource.setRoleName("admin");
        } catch (PageQueryException pqe) {
            logger.error(pqe.getMessage());
            return;
        }

        PageInfo<RoleResource> pageInfo = service.findRoleResourceList(
                pageQuery, Optional.of(roleResource)
        );
        logger.info("Result: Page content size = " + pageInfo.getList().size());
    }

    @Test
    public void testSaveRole() {
        Role role = new Role();
        role.setRoleName("super");
        boolean res = service.saveRole(role);
        logger.info("Test: save a role, Result: " + res);
    }

    @Test
    public void testUpdateRole() {
        Role role = new Role();
        role.setRoleId(5L);
        role.setRoleName("VIP用户");
        boolean res = service.updateRole(role);
        logger.info("Test: update a role, Result: " + res);
    }

    @Test
    public void testAssignAdminsForRole() {
        Role role = new Role();
        role.setRoleId(1L);
        List<Long> adminIds = Lists.asList(9L, new Long[] {10L, 11L});

        boolean res = service.assignAdminsForRole(role, adminIds);
        logger.info("Test: assign admins for role, Result: " + res);
    }

}
