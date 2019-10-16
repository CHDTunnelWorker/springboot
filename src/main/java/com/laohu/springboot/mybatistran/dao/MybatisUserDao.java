package com.laohu.springboot.mybatistran.dao;

import com.laohu.springboot.mybatistran.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * User 持久化层
 */
@Repository
public interface MybatisUserDao {
    public User getUser(Long id);
}
