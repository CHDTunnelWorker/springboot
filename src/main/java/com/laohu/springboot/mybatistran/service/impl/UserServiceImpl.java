package com.laohu.springboot.mybatistran.service.impl;

import com.laohu.springboot.mybatistran.dao.UserDao;
import com.laohu.springboot.mybatistran.pojo.TranUser;
import com.laohu.springboot.mybatistran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: springboot
 * @description: 数据库事务测试用户业务层实现类
 * @author: Holland
 * @create: 2019-10-16 14:59
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * @Description: 用户查询
     * @param: [id]
     * @return: com.laohu.springboot.mybatistran.pojo.TranUser
     * @auther: Holland
     * @date: 2019/10/16 15:00
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    public TranUser getTranUser(Long id) {
        return userDao.getTranUser(id);
    }

    /**
     * @Description: 新增用户
     * @param: [tranUser]
     * @return: int
     * @auther: Holland
     * @date: 2019/10/16 15:01
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1,propagation = Propagation.NESTED)
    public int insertUser(TranUser tranUser) {
        return userDao.insertUser(tranUser);
    }
}
