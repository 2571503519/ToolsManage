package com.jack.web.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Optional;
import com.jack.pojo.entity.RoleResource;
import com.jack.service.RoleService;
import com.jack.util.PageQuery;
import com.jack.util.TmResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jackaroo Zhang on 2018/10/24.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse roleList(PageQuery pageQuery, RoleResource roleResource) {
        PageInfo<RoleResource> roleResourceList = roleService.findRoleResourceList(
                pageQuery, Optional.of(roleResource)
        );
        return TmResponse.success("获取角色列表成功", roleResourceList);
    }

}
