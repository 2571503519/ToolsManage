package com.jack.service;

import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import com.jack.pojo.entity.Role;
import com.jack.util.TmResponse;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/21.
 */
public interface AdminService {


    Admin findAdminByUsername(String username);

    List<Role> findRolesByUsername(String username);

    List<Resource> findResourcesByUsername(String username);

    TmResponse addAdmin(Admin admin);

}
