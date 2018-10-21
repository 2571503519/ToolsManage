package com.jack.service.impl;

import com.jack.dao.AdminMapper;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import com.jack.pojo.entity.Role;
import com.jack.service.AdminService;
import com.jack.util.State;
import com.jack.util.TmResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/21.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminByUsername(String username) {
        if (username == null)
            throw new IllegalArgumentException("username is null");
        Admin admin = adminMapper.findAdminByUsername(username);
        return admin;
    }

    @Override
    public List<Role> findRolesByUsername(String username) {
        return adminMapper.findRolesByUsername(username);
    }

    @Override
    public List<Resource> findResourcesByUsername(String username) {
        List<Resource> resources = adminMapper.findResourcesByUsername(username);

        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i) == null)
                resources.remove(i);
        }

        return resources;
    }

    @Override
    public TmResponse addAdmin(Admin admin) {
        // 参数校验
        String msg = validate(admin);
        if (msg != null) return TmResponse.fail(msg);

        Admin existedAdmin = findAdminByUsername(admin.getUsername());
        if (existedAdmin != null) {
            return TmResponse.fail("用户名已存在");
        }
        // TODO: 将密码加密

        // 预设默认值
        admin.setState(State.AdminState.NORMAL);

        if (!adminMapper.saveAdmin(admin)) {
            return TmResponse.fail("添加管理员失败");
        }
        return TmResponse.success("添加管理员成功");
    }

    private String validate(Admin admin) {
        if (StringUtils.isBlank(admin.getUsername()))
            return "用户名不能为空";
        if (StringUtils.isBlank(admin.getPassword()))
            return "密码不能为空";
        if (StringUtils.isBlank(admin.getIdCard())) {
            return "身份证号码不能为空";
        }

        return null;
    }
}
