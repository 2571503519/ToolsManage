package com.jack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jack.dao.AdminMapper;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import com.jack.pojo.entity.Role;
import com.jack.service.AdminService;
import com.jack.util.MD5Util;
import com.jack.util.PageQuery;
import com.jack.util.State;
import com.jack.util.TmResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/21.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminByUsername(String username) {
        if (username == null) return null;
        Admin admin = adminMapper.findAdminByUsername(username);
        return admin;
    }

    @Override
    public Admin findAdminByAdminId(Long adminId) {
        return adminMapper.findAdminByPrimaryKey(adminId);
    }

    @Override
    public List<Role> findRolesByUsername(String username) {
        return adminMapper.findRolesByUsername(username);
    }

    @Override
    public List<Resource> findResourcesByUsername(String username) {
        List<Resource> resources = adminMapper.findResourcesByUsername(username);

        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i) == null)
                resources.remove(i);
        }

        return resources;
    }

    @Override
    public PageInfo<Admin> queryAdminList(PageQuery pageQuery, Admin admin) {
        if (pageQuery == null) return null;
        // PageHelper中分页从0和从1开始效果相同
        PageHelper.startPage(pageQuery.getPage(), pageQuery.getPageSize());
        // 查询参数
        String queryParam = pageQuery.getQueryParam();
        // TODO: 设置查询条件
        List<Admin> adminList = adminMapper.findAdminsConditionally(admin);

        return new PageInfo<>(adminList);
    }

    @Override
    public boolean addAdmin(Admin admin) {
        Admin existedAdmin = findAdminByUsername(admin.getUsername());
        if (existedAdmin != null) {
            return false;
        }

        // 预设默认值
        admin.setPassword(MD5Util.encrypt(admin.getPassword()));
        admin.setState(State.AdminState.NORMAL);

        return adminMapper.saveAdmin(admin);
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

}
