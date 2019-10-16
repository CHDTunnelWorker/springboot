package com.laohu.springboot.mybatistran.controller;

import com.laohu.springboot.mybatistran.pojo.TranUser;
import com.laohu.springboot.mybatistran.service.UserBatchService;
import com.laohu.springboot.mybatistran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.idn.Punycode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description: 测试数据库事务控制层
 * @author: Holland
 * @create: 2019-10-16 15:02
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserBatchService userBatchService;

    /**
     * 测试获取用户
     * @param id
     * @return
     */
    @RequestMapping("/getTranUser")
    @ResponseBody
    public TranUser getTranUser(Long id){
        return userService.getTranUser(id);
    }

    /**
     * 测试插入用户
     * @param userName
     * @param note
     * @return
     */
    @RequestMapping("/insertUser")
    @ResponseBody
    public Map<String,Object> insertUser(String userName,String note){
        TranUser tranUser = new TranUser();
        tranUser.setUserName(userName).setNote(note);
        int update = userService.insertUser(tranUser);
        Map<String,Object> result = new HashMap<>();
        result.put("success",update == 1);
        result.put("tranUser",tranUser);
        return result;
    }

    /**
     * 测试批量插入,调用子方法事务,事务管理测试
     * @param userName1
     * @param note1
     * @param userName2
     * @param note2
     * @return
     */
    @RequestMapping("/insertUsers")
    @ResponseBody
    public Map<String,Object> insertUsers(String userName1,String note1,String userName2,String note2){
        TranUser tranUser1 = new TranUser();
        tranUser1.setUserName(userName1).setNote(note1);
        TranUser tranUser2 = new TranUser();
        tranUser2.setUserName(userName2).setNote(note2);
        List<TranUser> userList = new ArrayList<>();
        userList.add(tranUser1);
        userList.add(tranUser2);
        //结果会回填主键,返回插入条数
        int inserts = userBatchService.insertUsers(userList);
        Map<String,Object> result = new HashMap<>();
        result.put("success",inserts>0);
        result.put("users",userList);
        return result;
    }
}
