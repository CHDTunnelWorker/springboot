package com.laohu.springboot.other.asyncthreadpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: springboot
 * @description: 异步控制器
 * @author: Holland
 * @create: 2019-11-28 14:40
 **/
@Controller
@RequestMapping("/async")
public class AsyncController {

    /**
     * 注入异步服务接口
     */
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/page")
    @ResponseBody
    public String asyncPage(){
        System.out.println("请求线程名称:"+"["+Thread.currentThread().getName()+"]");
        //调用异步服务方法
        asyncService.generateReport();
        return "调用成功!!";
    }
}
