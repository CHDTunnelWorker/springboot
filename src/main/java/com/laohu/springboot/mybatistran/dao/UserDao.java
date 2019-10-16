package com.laohu.springboot.mybatistran.dao;

import com.laohu.springboot.mybatistran.pojo.TranUser;
import org.springframework.stereotype.Repository;
/**
 * @program: springboot
 * @description: 数据库事务测试用户Dao
 * @author: Holland
 * @create: 2019-10-16 12:26
 **/
@Repository
public interface UserDao {
    TranUser getTranUser(Long id);
    int insertUser(TranUser tranUser);
}
