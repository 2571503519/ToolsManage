package com.jack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jack.dao.AdminMapper;
import com.jack.dao.RoleMapper;
import com.jack.exception.PageQueryException;
import com.jack.pojo.entity.AdminRole;
import com.jack.pojo.entity.Role;
import com.jack.pojo.entity.RoleResource;
import com.jack.service.RoleService;
import com.jack.util.PageQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/24.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Role> findRoleList() {
        // TODO: 获取角色列表
        return null;
    }

    @Override
    public PageInfo<RoleResource> findRoleResourceList(PageQuery pageQuery, Optional<RoleResource> roleResource) {
        // 分页参数校验
        if (pageQuery != null && pageQuery.getPage() >= 0 && pageQuery.getPageSize() > 0) {
            PageHelper.startPage(
                    pageQuery.getPage(), pageQuery.getPageSize()
            );
        } else {
            throw new PageQueryException(pageQuery.getPage(), pageQuery.getPageSize());
        }
        // 查询条件校验
        if (roleResource.isPresent()) {
            List<RoleResource> roleResourceList = roleMapper.findRoleResourceList(roleResource.get());
            // 如何结果为Null，则返回空集合，否则返回查询出的集合
            return roleResourceList == null ? new PageInfo<>(Collections.EMPTY_LIST) : new PageInfo<>(roleResourceList);
        } else {
            return new PageInfo<>(Collections.EMPTY_LIST);
        }
    }

    @Override
    public Role findRoleByRoleId(Optional<Long> roleId) {
        // 非空判断
        if (roleId.isPresent()) {
            // 校验参数
            Preconditions.checkArgument(roleId.get() > 0, "roleId must be positive");
            return roleMapper.findRoleByPrimaryKey(roleId.get());
        }
        return null;
    }

    @Override
    public boolean saveRole(Role role) {
        return roleMapper.saveRole(role);
    }

    @Override
    public boolean updateRole(Role role) {
        return roleMapper.updateRole(role);
    }

    @Override
    public boolean assignAdminsForRole(Role role, List<Long> adminIds) {
        // 构造AdminRole对象的集合
        List<AdminRole> adminRoleList = Lists.newArrayList();
        AdminRole adminRole = null;
        for (Long adminId : adminIds) {
            adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(role.getRoleId());
            adminRoleList.add(adminRole);
        }

        int rows = adminMapper.saveAdminRoles(adminRoleList);
        return rows > 0 ? true : false;
    }


}
