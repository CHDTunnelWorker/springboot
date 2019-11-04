package com.laohu.springboot.springmvc.controller.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: springboot
 * @description: 测试控制器增加通知的控制器通知类
 * @author: Holland
 * @create: 2019-11-04 09:59
 **/
@ControllerAdvice(
        //指定需要该控制器通知器拦截的包路径下的类
        basePackages = "com.laohu.springboot.springmvc.controller.advice.*",
        //限定拦截类上的注解@Controller
        annotations = Controller.class
)
public class MyControllerAdvice {

    /**
     * 在参数转化之前,将参数格式化,转换和增加验证器等
     * @param binder
     */
    @InitBinder
    public void initDataBinder(WebDataBinder binder){
        //自定义日期编辑器,限定格式为yyyy-MM-dd,并且参数不能为空
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false);
        //注册自定义日期编辑器
        binder.registerCustomEditor(Date.class,dateEditor);
    }

    /**
     * 在执行控制器之前先前执行,可以初始化数据模型
     * @param model
     */
    @ModelAttribute
    public void projectModel(Model model){
        model.addAttribute("project_name","chapter10");
    }

    /**
     * 异常处理,使得被拦截的控制器中的方法出现指定异常,都能用相同的方式去响应
     * @param model
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String exception(Model model,Exception ex){
        //给数据模型增加异常信息
        model.addAttribute("exception_message",ex.getMessage());
        //返回异常视图
        return "exception";
    }
}
