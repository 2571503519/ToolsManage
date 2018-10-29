package com.jack.web.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jack.pojo.entity.Role;
import com.jack.pojo.entity.RoleResource;
import com.jack.service.RoleService;
import com.jack.util.PageQuery;
import com.jack.util.TmResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse addRole(Role role) {
        Preconditions.checkArgument(role != null, "role can not be null, role=" + role);
        Preconditions.checkArgument(StringUtils.isNotBlank(role.getRoleCode())
                , "roleCode can not be blank, roleCode=" + role.getRoleCode());

        if (roleService.saveRole(role))
            return TmResponse.success("新增角色成功");
        else
            return TmResponse.fail("新增角色失败");
    }

    @RequestMapping(value = "/find/id/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse findRole(@PathVariable Long roleId) {
        // boolean为false，才抛出异常
        Preconditions.checkArgument((roleId != null || roleId > 0), "Param error: roleId=" + roleId);

        Role role = roleService.findRoleByRoleId(Optional.of(roleId));
        return TmResponse.success("获取角色信息成功", role);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse updateRole(Role role) {
        Preconditions.checkArgument(role != null, "role must be not null, role=" + role);
        Preconditions.checkArgument((role.getRoleId() != null && role.getRoleId() > 0)
                , "roleId must be not null and positive, roleId=" + role.getRoleId());

        boolean res = roleService.updateRole(role);
        return res ? TmResponse.success("角色信息更新成功") : TmResponse.fail("角色信息更新失败");
    }


    @RequestMapping(value = "/admin/save", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse assignAdminsForRole(@RequestBody Map<String, Object> params) {
        // 参数转换
        Role role = new Role();
        Integer roleId = (Integer) params.get("roleId");
        // 校验roleId
        Preconditions.checkArgument((roleId != null && roleId > 0)
                , "roleId can not be null and negative, roleId=" + roleId);
        role.setRoleId(Long.parseLong(roleId.toString()));

        List<Integer> adminIdsRaw = (List<Integer>) params.get("adminIds");
        Preconditions.checkArgument(adminIdsRaw != null, "adminIds can not be null, adminIds=" + adminIdsRaw);

        List<Long> adminIds = Lists.newArrayList();
        for (Integer item : adminIdsRaw) {
            adminIds.add(Long.parseLong(item.toString()));
        }


        boolean result = roleService.assignAdminsForRole(role, adminIds);

        return result ? TmResponse.success("分配用户成功") : TmResponse.fail("分配用户失败");
    }


}
