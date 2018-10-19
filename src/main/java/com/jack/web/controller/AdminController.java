package com.jack.web.controller;

import com.jack.pojo.entity.Admin;
import com.jack.util.TmResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jackaroo Zhang on 2018/10/15.
 */
@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse addAdmin(Admin admin) {
        logger.info("Recive: {}", admin);
        return TmResponse.success("创建管理员成功");
    }

    @RequestMapping(value = "/id/{adminId}", method = RequestMethod.POST)
    @ResponseBody
    public TmResponse updateAdminInfo(@PathVariable Long adminId
            , String adminName, Long deptId, String resIds, Integer adminState) {


        return TmResponse.success("修改管理员信息成功");
    }


}
