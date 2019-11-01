package com.laohu.springboot.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: springboot
 * @description: 测试拦截器的控制类
 * @author: Holland
 * @create: 2019-11-01 16:25
 **/
@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

    /**
     * 简单模拟单个自定义拦截器的拦截过程
     * @return
     */
    @RequestMapping("/start")
    @ResponseBody
    public String start(){
        System.out.println("执行处理器逻辑");
        return "请看控制台";
    }

}
