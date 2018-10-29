package com.jack.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jack.exception.PageQueryException;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.AdminRole;
import com.jack.pojo.entity.Role;
import com.jack.pojo.entity.RoleResource;
import com.jack.service.AdminService;
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

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse roleList(PageQuery pageQuery, RoleResource roleResource) {
        // 分页参数校验
        Preconditions.checkArgument(
                (pageQuery.getPage() >= 0 && pageQuery.getPageSize() > 0)
                , "Page Query Param error, page and pageSize both can't be negative, page="
                        + pageQuery.getPage() + ", pageSize=" + pageQuery.getPageSize()
        );

        PageInfo<RoleResource> roleResourceList = roleService.findRoleResourceList(
                pageQuery, Optional.of(roleResource)
        );
        return TmResponse.success("获取角色列表成功", roleResourceList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse addRole(Role role) {
        Preconditions.checkArgument(StringUtils.isNotBlank(role.getRoleName())
                , "roleName can not be empty, roleName=" + role.getRoleName());
        Preconditions.checkArgument(StringUtils.isNotBlank(role.getRoleCode())
                , "roleCode can not be blank, roleCode=" + role.getRoleCode());

        // 逻辑校验，roleCode在数据库中是唯一的，所以这里要校验roleCode是否重复
        Role existedRole = roleService.findRoleByRoleCode(Optional.of(role.getRoleCode()));
        if (existedRole != null) {
            return TmResponse.fail("角色代号已存在, roleCode=" + role.getRoleCode());
        }
        // 添加角色
        return roleService.saveRole(role) ? TmResponse.success("新增角色成功") : TmResponse.fail("新增角色失败");
    }

    @RequestMapping(value = "/find/id/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse findRole(@PathVariable Long roleId) {
        Preconditions.checkArgument(roleId > 0, "roleId can't be negative: roleId=" + roleId);

        Role role = roleService.findRoleByRoleId(Optional.of(roleId));
        return TmResponse.success("获取角色信息成功", role);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse updateRole(Role role) {
        Preconditions.checkArgument((role.getRoleId() != null && role.getRoleId() > 0)
                , "roleId must be not null and positive, roleId=" + role.getRoleId());

        boolean res = roleService.updateRole(role);
        return res ? TmResponse.success("角色信息更新成功") : TmResponse.fail("角色信息更新失败");
    }


    @RequestMapping(value = "/admin/save", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse assignAdminsForRole(@RequestBody Map<String, Object> params) {
        // 参数转换，并校验参数
        Role role = new Role();
        Integer roleId = (Integer) params.get("roleId");
        // 校验roleId
        Preconditions.checkArgument((roleId != null && roleId > 0)
                , "roleId can not be null and negative, roleId=" + roleId);
        role.setRoleId(Long.parseLong(roleId.toString()));

        List<Integer> adminIdsRaw = (List<Integer>) params.get("adminIds");
        Preconditions.checkArgument((adminIdsRaw != null && adminIdsRaw.size() > 0), "adminIds can not be empty");
        List<Long> adminIds = Lists.newArrayList();
        for (Integer item : adminIdsRaw) {
            adminIds.add(Long.parseLong(item.toString()));
        }

        // 逻辑校验，用户和角色必须都存在
        Role existedRole = roleService.findRoleByRoleId(Optional.of(role.getRoleId()));
        if (existedRole == null) return TmResponse.fail("对应的角色不存在，roleId=" + role.getRoleId());

        for (Long adminId : adminIds) {
            Admin existedAdmin = adminService.findAdminByAdminId(adminId);
            if (existedAdmin == null) return TmResponse.fail("对应的用户不存在，adminId=" + adminId);
        }
        // 用户与角色关联的记录不能重复
        for (Long adminId : adminIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setRoleId(role.getRoleId());
            adminRole.setAdminId(adminId);
            if (roleService.adminRoleExisted(adminRole)) {
                return TmResponse.fail("角色已经被分配与该用户，roleId=" + role.getRoleId() + ", adminId=" + adminId);
            }
        }

        // 为角色分配多个用户
        boolean result = roleService.assignAdminsForRole(role, adminIds);
        return result ? TmResponse.success("分配用户成功") : TmResponse.fail("分配用户失败");
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public TmResponse handleParamException(IllegalArgumentException e) {
        return TmResponse.fail(e.getMessage());
    }


}
