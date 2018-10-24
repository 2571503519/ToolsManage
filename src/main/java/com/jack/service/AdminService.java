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

    /**
     * 查询管理员，通过用户名
     * @param username
     * @return
     */
    Admin findAdminByUsername(String username);

    /**
     * 查询管理员，通过ID
     * @param adminId
     * @return
     */
    Admin findAdminByAdminId(Long adminId);

    /**
     * 查询角色，通过用户名
     * @param username
     * @return
     */
    List<Role> findRolesByUsername(String username);

    /**
     * 查询资源，通过用户名
     * @param username
     * @return
     */
    List<Resource> findResourcesByUsername(String username);

    /**
     * 查询管理员，通过查询条件和分页参数
     * @param pageQuery 分页参数
     * @param admin 查询条件
     * @return
     */
    PageInfo<Admin> queryAdminList(PageQuery pageQuery, Admin admin);

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    boolean addAdmin(Admin admin);

    /**
     * 更新管理员信息
     * @param admin
     * @return
     */
    boolean updateAdmin(Admin admin);


}
