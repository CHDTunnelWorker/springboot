package com.laohu.springboot.mybatistran.service.impl;

import com.laohu.springboot.mybatistran.pojo.TranUser;
import com.laohu.springboot.mybatistran.service.UserBatchService;
import com.laohu.springboot.mybatistran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: springboot
 * @description: 数据库事务传播业务层实现类
 * @author: Holland
 * @create: 2019-10-16 16:08
 **/
@Service
public class UserBatchServiceImpl implements UserBatchService {

    @Autowired
    private UserService userService;

    /**
     * @Description: 通过事务管理.调用子方法批量插入数据
     * @param: [userList]
     * @return: int
     * @auther: Holland
     * @date: 2019/10/16 16:12
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int insertUsers(List<TranUser> userList) {
        int count = 0;
        for(TranUser user : userList){
            //调用子方法,使用@Transactional定义事务传播行为
            count += userService.insertUser(user);
        }
        return count;
    }
}
