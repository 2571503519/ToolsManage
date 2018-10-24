package com.jack.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jack.dao.RoleMapper;
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

    @Override
    public List<Role> findRoleList() {
        // TODO: 获取角色列表
        return null;
    }

    @Override
    public PageInfo<RoleResource> findRoleResourceList(PageQuery pageQuery, Optional<RoleResource> roleResource) {
        if (pageQuery != null && pageQuery.getPage() != null && pageQuery.getPageSize() != null) {
            PageHelper.startPage(
                    pageQuery.getPage(), pageQuery.getPageSize()
            );
        }

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

}
