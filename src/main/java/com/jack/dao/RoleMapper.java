package com.jack.dao;

import com.jack.pojo.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by Jackaroo Zhang on 2018/10/20.
 */
@Repository
public interface RoleMapper {

    boolean saveRole(Role role);

    boolean updateRole(Role role);

    Role findRoleByPrimaryKey(Long roleId);

    boolean deleteRole(Long roleId);

}
