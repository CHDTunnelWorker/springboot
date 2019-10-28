package com.laohu.springboot.mybatistran.service;

import com.laohu.springboot.mybatistran.pojo.TranUser;

import java.util.List;

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

    /**
     * 修改用户
     * @param id
     * @param userName
     * @return
     */
    TranUser updateUserName(Long id,String userName);

    /**
     * 根据条件查询用户
     * @param userName
     * @param note
     * @return
     */
    List<TranUser> findUsers(String userName,String note);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(Long id);

    /**
     * 保存用户(测试redis缓存机制)
     * @return
     */
    TranUser saveUser(TranUser tranUser);
}
