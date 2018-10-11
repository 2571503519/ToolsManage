package com.jack.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JackarooZhang
 * @date 2018/6/18 11:15
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/index")
    @ResponseBody
    public Map index() {
        Map<String, String> result = new HashMap<>();
        result.put("username", "张家乐");
        result.put("welcome", "您好！欢迎使用本系统。");
        return result;
    }


}
