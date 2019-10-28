package com.laohu.springboot.redis.controller;

import com.laohu.springboot.mybatistran.pojo.TranUser;
import com.laohu.springboot.mybatistran.service.UserService;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description: 测试redis缓存注解的控制层类
 * @author: Holland
 * @create: 2019-10-28 14:32
 **/
@Controller
@RequestMapping("/redisCacheAnno")
public class TranUserController {

    @Autowired
    private UserService userService;

    /**
     * @Description: 获取用户
     * @param: [id]
     * @return: com.laohu.springboot.mybatistran.pojo.TranUser
     * @auther: Holland
     * @date: 2019/10/28 14:57
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public TranUser getUser(Long id){
        return userService.getTranUser(id);
    }

    /**
     * @Description: 保存用户
     * @param: [userName, note]
     * @return: com.laohu.springboot.mybatistran.pojo.TranUser
     * @auther: Holland
     * @date: 2019/10/28 14:57
     */
    @RequestMapping("/insertUser")
    @ResponseBody
    public TranUser insertUser(String userName,String note){

        TranUser tranUser = new TranUser();
        tranUser.setUserName(userName);
        tranUser.setNote(note);
        userService.insertUser(tranUser);
        return tranUser;
    }

    /**
     * @Description: 根据条件查询用户
     * @param: [userName, note]
     * @return: java.util.List<com.laohu.springboot.mybatistran.pojo.TranUser>
     * @auther: Holland
     * @date: 2019/10/28 14:58
     */
    @RequestMapping("/findUsers")
    @ResponseBody
    public List<TranUser> findUsers(String userName,String note){
        return userService.findUsers(userName,note);
    }

    /**
     * @Description: 更新用户
     * @param: [id, userName]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @auther: Holland
     * @date: 2019/10/28 14:58
     */
    @RequestMapping("/updateUserName")
    @ResponseBody
    public Map<String,Object> updateUserName(Long id,String userName){
        TranUser tranUser = userService.updateUserName(id, userName);
        boolean flag = tranUser != null;
        String msg = flag?"更新成功":"更新失败";
        Map<String,Object> result = new HashMap<>();
        result.put("msg",msg);
        return result;
    }

    /**
     * @Description: 删除用户
     * @param: [id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @auther: Holland
     * @date: 2019/10/28 14:58
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String,Object> deleteUser(Long id){
        int count = userService.deleteUser(id);
        boolean flag = count == 1;
        String msg = flag?"删除成功":"删除失败";
        Map<String,Object> result = new HashMap<>();
        result.put("msg",msg);
        return result;
    }
}
