package com.laohu.springboot.mybatistran.service;

import com.laohu.springboot.mybatistran.pojo.TranUser;

/**
 * @program: springboot
 * @description: 数据库事务测试用户业务层接口
 * @author: Holland
 * @create: 2019-10-16 14:56
 **/
public interface UserService {
    /**
     * 获取用户信息
     * @param id
     * @return
     */
    public TranUser getTranUser(Long id);

    /**
     * 新增用户
     * @param tranUser
     * @return
     */
    public int insertUser(TranUser tranUser);
}
