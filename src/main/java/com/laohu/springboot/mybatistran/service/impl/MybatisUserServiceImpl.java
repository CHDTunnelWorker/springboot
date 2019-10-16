package com.laohu.springboot.mybatistran.service.impl;

import com.laohu.springboot.mybatistran.dao.MybatisUserDao;
import com.laohu.springboot.mybatistran.pojo.User;
import com.laohu.springboot.mybatistran.service.MybatisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: springboot
 * @description: 用户接口业务层实现类
 * @author: Holland
 * @create: 2019-10-16 10:26
 **/
@Service
public class MybatisUserServiceImpl implements MybatisUserService {

    @Autowired
    private MybatisUserDao mybatisUserDao;

    @Override
    public User getUser(Long id) {
        User user = mybatisUserDao.getUser(id);
        return user;
    }
}
