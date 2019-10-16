package com.laohu.springboot.mybatistran.service;

import com.laohu.springboot.mybatistran.pojo.TranUser;

import java.util.List;

/**
 * @program: springboot
 * @description: 测试数据库事务传播业务接口
 * @author: Holland
 * @create: 2019-10-16 16:07
 **/
public interface UserBatchService {
    /**
     *  批量插入用户信息
     * @param userList
     * @return
     */
    public int insertUsers(List<TranUser> userList);
}
