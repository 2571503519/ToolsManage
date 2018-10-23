package com.jack.service;

import com.github.pagehelper.PageInfo;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import com.jack.pojo.entity.Role;
import com.jack.util.PageQuery;
import com.jack.util.State;
import com.jack.util.TmResponse;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/21.
 */
public interface AdminService {


    Admin findAdminByUsername(String username);

    Admin findAdminByAdminId(Long adminId);

    List<Role> findRolesByUsername(String username);

    List<Resource> findResourcesByUsername(String username);

    PageInfo<Admin> queryAdminList(PageQuery pageQuery, Admin admin);

    boolean addAdmin(Admin admin);

    boolean updateAdmin(Admin admin);


}
