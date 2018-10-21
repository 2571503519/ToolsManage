package com.jack.web.controller;

import com.jack.pojo.entity.Admin;
import com.jack.service.AdminService;
import com.jack.util.Constant;
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
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse logout(HttpSession session) {
        SecurityUtils.getSubject().logout();
        return TmResponse.success("用户登出成功");
    }

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse session() {
        Session session = SecurityUtils.getSubject().getSession();
        return TmResponse.success("获取Session信息", session.getAttribute(Constant.LOGINED_USER));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse addAdmin(Admin admin) {
        return adminService.addAdmin(admin);
    }

    @RequestMapping(value = "/id/{adminId}", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse updateAdminInfo(@PathVariable Long adminId
            , String adminName, Long deptId, String resIds, Integer adminState) {


        return TmResponse.success("修改管理员信息成功");
    }



}
