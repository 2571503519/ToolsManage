package com.jack.dao;

import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.AdminRole;
import com.jack.pojo.entity.Resource;
import com.jack.pojo.entity.Role;
import com.jack.util.State;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/13.
 */
@Repository
public interface AdminMapper {

     boolean saveAdmin(Admin admin);

     Admin findAdminByPrimaryKey(Long adminId);

     boolean updateAdmin(Admin admin);

     boolean deleteAdmin(Long adminId);

     Admin findAdminByUsername(String username);

     List<Role> findRolesByUsername(String username);

     List<Role> findRolesByAdminId(Long adminId);

     List<Resource> findResourcesByUsername(String username);

     List<Admin> findAdminsConditionally(Admin state);

     int saveAdminRoles(@Param("adminRoleList") List<AdminRole> adminRoleList);
}
