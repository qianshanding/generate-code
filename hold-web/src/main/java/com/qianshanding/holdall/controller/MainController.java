package com.qianshanding.holdall.controller;

import com.qianshanding.holdall.config.HoldConfig;
import com.qianshanding.holdall.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fish on 2017/3/25.
 */
@Controller
public class MainController {
    @Autowired
    private HoldConfig holdConfig;

    @RequestMapping(value = "/")
    public String mian() {
        return "main";
    }

    @RequestMapping(value = "/project")
    public String project() {
        System.out.println(holdConfig.getBasePath());
        return "main";
    }
}