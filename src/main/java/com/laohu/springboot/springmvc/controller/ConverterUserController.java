package com.laohu.springboot.springmvc.controller;

import com.laohu.springboot.mybatistran.pojo.TranUser;
import com.laohu.springboot.mybatistran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: springboot
 * @description: SpringMVC章节中的用户测试控制类
 * @author: Holland
 * @create: 2019-10-30 10:22
 **/
@RequestMapping("/mvc")
@Controller
public class ConverterUserController {

    @Autowired
    private UserService userService;

    /**
     * @Description: 测试自定义converter
     * @param: [user]
     * @return: com.laohu.springboot.mybatistran.pojo.TranUser
     * @auther: Holland
     * @date: 2019/10/30 11:11
     */
    @GetMapping("/converter")
    @ResponseBody
    public TranUser getUserByConverter(TranUser user){
        return user;
    }

    /**
     * @Description:测试GenericConverter数组转换器,mvc内置了一个实现GenericConverter接口的StringToCollectionConverter的数组转换器
     * 会将字符串用逗号进行分割,然后根据原类型string,目标类为TranUser,找到对应的普通converter进行处理
     *
     * 注意:参数名称与控制器方法的参数名称不是一一对应的,http的参数名称形式应该为目标类的驼峰名称+List,才能进行参数识别并转换
     * @param: [users]
     * @return: java.util.List<com.laohu.springboot.mybatistran.pojo.TranUser>
     * @auther: Holland
     * @date: 2019/10/30 12:10
     */
    @GetMapping("/converterList")
    @ResponseBody
    public List<TranUser> getListByConverter(List<TranUser> users){
        return users;
    }
}
