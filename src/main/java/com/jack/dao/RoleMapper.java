package com.jack.dao;

import com.jack.pojo.entity.AdminRole;
import com.jack.pojo.entity.Role;
import com.jack.pojo.entity.RoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/20.
 */
@Repository
public interface RoleMapper {

    boolean saveRole(Role role);

    boolean updateRole(Role role);

    Role findRoleByPrimaryKey(Long roleId);

    Role findRoleByRoleCode(String roleCode);

    AdminRole adminRoleExisted(AdminRole adminRole);

    boolean deleteRole(Long roleId);

    List<RoleResource> findRoleResourceList(RoleResource roleResource);

}
