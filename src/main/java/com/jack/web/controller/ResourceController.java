package com.jack.web.controller;

import com.google.common.base.Preconditions;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import com.jack.service.ResourceService;
import com.jack.util.Constant;
import com.jack.util.TmResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Jackaroo Zhang on 2018/10/27.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService rscService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse list(Resource resource) {

        List<Resource> resourceList = rscService.findResourceList(resource);
        return TmResponse.success("获取资源列表成功", resourceList);
    }

    @RequestMapping(value = "/available", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse availableResource() {
        Subject subject = SecurityUtils.getSubject();
        Preconditions.checkArgument(subject.isAuthenticated(), "current is not authenticated!");

        Admin admin = (Admin) subject.getSession().getAttribute(Constant.LOGINED_USER);
        Preconditions.checkArgument(admin != null, "current user is null, admin=" + admin);

        return TmResponse.success("获取可用资源成功", rscService.findResourcesForAdmin(admin));
    }

}
