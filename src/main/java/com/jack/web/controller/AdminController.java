package com.jack.web.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jack.exception.NotFoundCodeException;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Role;
import com.jack.service.AdminService;
import com.jack.service.RoleService;
import com.jack.util.Constant;
import com.jack.util.PageQuery;
import com.jack.util.State;
import com.jack.util.TmResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Jackaroo Zhang on 2018/10/15.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户登录
     * @param admin
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse login(Admin admin) {
        // 参数校验
        Preconditions.checkArgument(admin != null, "admin can not be null, admin=" + admin);
        String username = StringUtils.trim(admin.getUsername());
        String password = StringUtils.trim(admin.getPassword());
        Preconditions.checkArgument(StringUtils.isNotBlank(username), "username can not be empty, username=" + username);
        Preconditions.checkArgument(StringUtils.isNotBlank(password), "password can not be empty, password=" + password);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return TmResponse.fail("用户名不存在");
        } catch (IncorrectCredentialsException ice) {
            return TmResponse.fail("密码错误");
        } catch (LockedAccountException lae) {
            return TmResponse.fail("用户状态异常");
        } catch (AuthenticationException e) {
            return TmResponse.fail("未知错误");
        }


        Admin loginedAdmin = adminService.findAdminByUsername(admin.getUsername());
        // 将密码置为“”返回给前端
        loginedAdmin.setPassword("");
        subject.getSession().setAttribute(Constant.LOGINED_USER, loginedAdmin);
        return TmResponse.success("登录成功", loginedAdmin);
    }

    /**
     * 用户登出
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse logout() {
        SecurityUtils.getSubject().logout();
        return TmResponse.success("用户登出成功");
    }

    /**
     * 根据查询条件获取管理员列表
     * @param pageQuery 当前页、页的大小、查询的参数
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse adminList(PageQuery pageQuery, Admin admin) {
        if (admin.getState() == null) admin.setState(State.AdminState.NORMAL);

        PageInfo<Admin> adminPageInfo = adminService.queryAdminList(pageQuery, admin);
        return TmResponse.success("获取管理员列表", adminPageInfo);
    }

    /**
     * 添加管理员
     * @param admin
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse addAdmin(Admin admin) {
        // 参数校验
        String errorMsg = validate(admin);
        if (StringUtils.isNotBlank(errorMsg))
            return TmResponse.fail(errorMsg);

        Admin existedAdmin = adminService.findAdminByUsername(admin.getUsername());
        if (existedAdmin != null) return TmResponse.fail("用户名已存在");

        if (adminService.addAdmin(admin)) {
            return TmResponse.success("添加用户成功");
        } else {
            return TmResponse.fail("添加用户失败");
        }
    }

    /**
     * 更新管理员信息
     * @param admin 状态码以字符串的形式传入，例如：NORMAL、FORBID
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse updateAdminInfo(Admin admin) {
        // 参数校验
        Preconditions.checkArgument(admin.getAdminId() != null, "adminId can not be null, adminId=" + admin.getAdminId());

        if (adminService.updateAdmin(admin)) {
            return TmResponse.success("修改管理员信息成功");
        } else {
            return TmResponse.fail("修改管理员信息失败");
        }
    }

    /**
     * 获取指定ID的管理员信息
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/find/id/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse findAdmin(@PathVariable Long adminId) {
        Preconditions.checkArgument((adminId != null && adminId > 0), "adminId can not be null or negative，adminId=" + adminId);

        Admin admin = adminService.findAdminByAdminId(adminId);
        if (admin == null) return TmResponse.fail("查询不到此管理员");

        admin.setPassword("");
        return TmResponse.success("查询用户信息", admin);
    }

    /**
     * 更新用户状态
     * @param adminId 管理员ID
     * @param stateCode 状态码，整型
     * @return
     */
    @RequestMapping(value = "/state/id/{adminId}/code/{stateCode}", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse changeAdminState(@PathVariable Long adminId, @PathVariable Integer stateCode) {

        Admin admin = new Admin();
        try {
            admin.setState(State.AdminState.codeOf(stateCode));
        } catch (NotFoundCodeException nfe) {
            return TmResponse.fail("参数错误");
        }
        admin.setAdminId(adminId);
        if (adminService.updateAdmin(admin)) {
            return TmResponse.success("管理员状态更新成功");
        } else {
            return TmResponse.fail("管理员状态更新失败");
        }
    }


    /**
     * 为用户分配角色
     * @param params 参数，包含了用户和角色ID的集合
     * @return
     */
    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse assignRolesForAdmin(@RequestBody Map<String, Object> params) {
        // 转换从参数为合法格式
        Admin admin = new Admin();
        Integer adminId = (Integer) params.get("adminId");
        admin.setAdminId(Long.parseLong(adminId.toString()));

        List<Integer> roleIdsRaw = (List<Integer>) params.get("roleIds");
        List<Long> roleIds = Lists.newArrayList();
        for (Integer item : roleIdsRaw) {
            roleIds.add(Long.parseLong(item.toString()));
        }

        Preconditions.checkArgument(admin != null, "admin must be not null");
        Preconditions.checkArgument(admin.getAdminId() != null, "adminId must be not null");

        Admin existedAdmin = adminService.findAdminByAdminId(admin.getAdminId());
        if (existedAdmin == null) return TmResponse.fail("对应用户不存在, adminId=" + admin.getAdminId());
        for (Long roleId : roleIds) {
            Role role = roleService.findRoleByRoleId(Optional.of(roleId));
            if (role == null) return TmResponse.fail("对应角色不存在, roleId=" + roleId);
        }

        boolean result = adminService.assignRolesForAdmin(admin, roleIds);

        return result ? TmResponse.success("分配角色成功") : TmResponse.fail("分配角色失败");
    }



    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public TmResponse handleParamException(IllegalArgumentException e) {
        return TmResponse.fail("参数异常", e.getMessage());
    }

/*------------------------------------------------内部私有函数-----------------------------------------------------------*/

    /**
     * 添加管理员时，校验前端传来的参数
     * @param admin
     * @return
     */
    private String validate(Admin admin) {
        if (admin == null) return "必须传入指定参数";
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
