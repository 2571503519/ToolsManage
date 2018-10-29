package com.jack.service;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Optional;
import com.jack.exception.PageQueryException;
import com.jack.pojo.entity.AdminRole;
import com.jack.pojo.entity.Role;
import com.jack.pojo.entity.RoleResource;
import com.jack.util.PageQuery;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/24.
 */
public interface RoleService {

    List<Role> findRoleList();

    /**
     * 查询角色-资源列表，通过角色名查询
     * @param pageQuery 分页条件
     * @param roleResource 查询条件，为空则返回空结果集；不为空但是查询参数为空则返回所有的结果
     * @throws PageQueryException 如果分页参数错误，则抛出该异常
     * @return 查询到的结果为空则返回空的集合，否则返回结果集合
     */
    PageInfo<RoleResource> findRoleResourceList(PageQuery pageQuery, Optional<RoleResource> roleResource);

    Role findRoleByRoleId(Optional<Long> roleId);

    Role findRoleByRoleCode(Optional<String> roleCode);

    boolean saveRole(Role role);

    boolean updateRole(Role role);

    boolean assignAdminsForRole(Role role, List<Long> adminIds);

    boolean adminRoleExisted(AdminRole adminRole);

}
