package com.jack.dao;

import com.google.common.collect.Lists;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.AdminRole;
import com.jack.pojo.entity.Resource;
import com.jack.pojo.entity.Role;
import com.jack.util.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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

    @Test
    public void testFindRolesByUsername() {
        List<Role> roles = mapper.findRolesByUsername("admin");
        roles.stream().forEach(role -> {
            logger.info("Test: query role by admin's username, Result: {}", role.getRoleName());
        });
    }

    @Test
    public void testFindResourcesByUsername() {
        List<Resource> resources = mapper.findResourcesByUsername("admin");

        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i) == null)
                resources.remove(i);
        }

        resources.stream().forEach(res -> {
            logger.info("Test: query resource by admin's username, Result: {}", res.getResName());
        });
    }

    @Test
    public void testFindAdminsByState() {
        Admin admin = new Admin();
        admin.setState(State.AdminState.NORMAL);
        List<Admin> admins = mapper.findAdminsConditionally(admin);
        if (admins != null) {
            logger.info("Query admins by state:");
            admins.stream().forEach(item -> {
                logger.info(item.toString());
            });
        } else {
            logger.info("Query result is null.");
        }
    }

    @Test
    public void testSaveAdminRoles() {
        List<AdminRole> adminRoleList = Lists.newArrayList();
        AdminRole adminRole = null;
        for (int i = 0; i < 4; i++) {
            adminRole = new AdminRole();
            adminRole.setAdminId((long) (i+1));
            adminRole.setRoleId((long) (i+1));
            adminRoleList.add(adminRole);
        }

        int rows = mapper.saveAdminRoles(adminRoleList);
        logger.info("Test: save adminRole list, Result: effect {} rows", rows);
    }

    @Test
    public void testFindRolesByAdminId() {
        List<Role> roles = mapper.findRolesByAdminId(7L);
        if (roles == null && roles.size() < 1) {
            logger.info("result is null, roles = " + roles);
            return;
        }

        roles.stream().forEach(role -> {
            logger.info("Test: role = " + role);
        });

    }

}
