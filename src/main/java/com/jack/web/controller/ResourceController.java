package com.jack.web.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import com.jack.service.ResourceService;
import com.jack.util.Constant;
import com.jack.util.PageQuery;
import com.jack.util.TmResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
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
    public TmResponse list(PageQuery pageQuery, Resource resource) {
        Preconditions.checkArgument((pageQuery.getPage() >= 0 && pageQuery.getPageSize() > 0)
                , "page and pageSize both can not be negative, page=" + pageQuery.getPage() + ", pageSize=" + pageQuery.getPageSize());

        PageInfo<Resource> resourceList = rscService.findResourceList(pageQuery, resource);
        return TmResponse.success("获取资源列表成功", resourceList);
    }

    /**
     * 获取结构化后的资源列表
     * @return
     */
    @RequestMapping(value = "/available", method = RequestMethod.GET)
    @ResponseBody
    public TmResponse availableResource() {

        return TmResponse.success("获取可用资源成功", rscService.findResources());
    }


    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseBody
    public TmResponse handleParamException(IllegalArgumentException e) {
        return TmResponse.fail(e.getMessage());
    }
}
