package com.jack.dao;

import com.google.common.base.Optional;
import com.jack.pojo.entity.AdminRole;
import com.jack.pojo.entity.Role;
import com.jack.pojo.entity.RoleResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class RoleMapperTest {

    private final Logger logger = LoggerFactory.getLogger(RoleMapperTest.class);

    @Autowired
    private RoleMapper mapper;

    @Test
    public void testSaveRole() {
        Role role = new Role();
        role.setRoleName("管理员");
        role.setRoleCode("admin");
        boolean res = mapper.saveRole(role);
        logger.info("Test: save a role, Result: {}, roleId = {}", res, role.getRoleId());
    }

    @Test
    public void testFindRoleByPrimaryKey() {
        Role role = mapper.findRoleByPrimaryKey(1L);
        if (role != null) {
            logger.info("Test: query a role, Result: {}", role.getRoleName());
        } else {
            logger.info("Test: query a role, Result: null");
        }
    }

    @Test
    public void testUpdateRole() {
        Role role = mapper.findRoleByPrimaryKey(1L);
        if (role != null) {
            role.setRoleName("普通用户");
            boolean res = mapper.updateRole(role);
            logger.info("Test: update a role, Result: {}", res);
        } else {
            logger.info("Test: query a role, Result: null");
        }
    }

    @Test
    public void testDeleteRole() {
        boolean res = mapper.deleteRole(1L);
        logger.info("Test: delete a role, Result: {}", res);
    }

    @Test
    public void testFindRoleResourceList() {
        RoleResource roleResource = new RoleResource();
        roleResource.setRoleCode("admin");
        List<RoleResource> roleResources = mapper.findRoleResourceList(roleResource);
        if (roleResources == null || roleResources.size() == 0) {
            logger.info("result is null");
            return;
        }
        roleResources.stream().forEach(item -> {
            logger.info(item.toString());
        });
    }

    @Test
    public void testAdminRoleExisted() {
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminId(12L);
        adminRole.setRoleId(3L);
        AdminRole existedAdminRole = mapper.adminRoleExisted(adminRole);
        logger.info("Test: adminRole is existed, Result: " + existedAdminRole);
    }

}
