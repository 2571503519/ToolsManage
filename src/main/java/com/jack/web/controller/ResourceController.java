package com.jack.web.controller;

import com.jack.pojo.entity.Resource;
import com.jack.service.ResourceService;
import com.jack.util.TmResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}
