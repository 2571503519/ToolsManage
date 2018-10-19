package com.jack.web.controller;

import com.jack.util.TmResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public TmResponse<String[]> index() {
        TmResponse tmResponse = TmResponse.success("请求成功", new String[]{"book", "bycle"});
        return tmResponse;
    }


}
