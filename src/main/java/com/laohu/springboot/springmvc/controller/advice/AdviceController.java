package com.laohu.springboot.springmvc.controller.advice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: springboot
 * @description: 为了配合测试控制器通知类的控制器
 * @author: Holland
 * @create: 2019-11-04 10:29
 **/
@Controller
@RequestMapping("/advice")
public class AdviceController {

    /**
     * 测试控制器通知
     * @param date
     * @param modelMap
     * @return
     */
    @GetMapping("/test")
    public String test(Date date, ModelMap modelMap){
        //从数据模型中获取数据
        System.out.println(modelMap.get("project_name"));
        //打印日期参数
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(date));
        //抛出异常,这样流转到控制器异常通知
        throw new RuntimeException("异常了,跳转到控制器的异常信息里");
    }
}
