package com.jack.web.controller;

import com.github.pagehelper.PageInfo;
import com.jack.pojo.entity.Admin;
import com.jack.service.AdminService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * Created by Jackaroo Zhang on 2018/10/15.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    /**
     * 用户登录
     * @param admin
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse login(Admin admin) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                admin.getUsername(), admin.getPassword());
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

        subject.getSession().setAttribute(
                Constant.LOGINED_USER, adminService.findAdminByUsername(admin.getUsername())
        );
        return TmResponse.success("登录成功");
    }

    /**
     * 用户登出
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse logout(HttpSession session) {
        SecurityUtils.getSubject().logout();
        return TmResponse.success("用户登出成功");
    }

    /**
     * 测试使用，获取当前登录用户的信息
     * @return
     */
    @RequestMapping(value = "/session", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse session() {
        Session session = SecurityUtils.getSubject().getSession();
        return TmResponse.success("获取Session信息", session.getAttribute(Constant.LOGINED_USER));
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse adminList(PageQuery pageQuery) {

        Admin admin = new Admin();
        admin.setState(State.AdminState.NORMAL);
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
     * @param admin
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse updateAdminInfo(Admin admin) {
        // 参数校验
        if (admin.getAdminId() == null) return TmResponse.fail("请传入管理员ID");

        if (adminService.updateAdmin(admin)) {
            return TmResponse.success("修改管理员信息成功");
        } else {
            return TmResponse.fail("修改管理员信息失败");
        }
    }


    @RequestMapping(value = "/find/id/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse findAdmin(@PathVariable Long adminId) {
        if (adminId == null) return TmResponse.fail("请传入管理员ID");

        Admin admin = adminService.findAdminByAdminId(adminId);
        if (admin == null) return TmResponse.fail("查询不到此管理员");

        return TmResponse.success("查询用户信息", admin);
    }

    @RequestMapping(value = "/state/id/{adminId}/code/{stateCode}", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse changeAdminState(@PathVariable Long adminId, @PathVariable Integer stateCode) {

        return TmResponse.success("修改用户状态");
    }



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
