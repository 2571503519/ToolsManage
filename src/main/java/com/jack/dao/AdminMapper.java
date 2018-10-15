package com.jack.dao;

import com.jack.pojo.entity.Admin;
import org.springframework.stereotype.Repository;

/**
 * Created by Jackaroo Zhang on 2018/10/13.
 */
@Repository
public interface AdminMapper {

     boolean saveAdmin(Admin admin);

     Admin findAdminByPrimaryKey(Long adminId);

     boolean updateAdmin(Admin admin);

     boolean deleteAdmin(Long adminId);
}
