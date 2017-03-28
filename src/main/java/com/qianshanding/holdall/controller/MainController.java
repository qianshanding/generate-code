package com.qianshanding.holdall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fish on 2017/3/25.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/")
    public String mian() {
        return "main";
    }
}