package com.laohu.springboot.mybatistran.controller;

import com.laohu.springboot.mybatistran.pojo.User;
import com.laohu.springboot.mybatistran.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: springboot
 * @description: 控制层
 * @author: Holland
 * @create: 2019-10-16 10:28
 **/
@Controller
@RequestMapping("/mybatis")
public class MabatisController {

    @Autowired
    private MybatisUserService mybatisUserService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Long id){
        return mybatisUserService.getUser(id);
    }
}
