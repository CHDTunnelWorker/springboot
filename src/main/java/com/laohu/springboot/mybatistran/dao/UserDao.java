package com.laohu.springboot.mybatistran.dao;

import com.laohu.springboot.mybatistran.pojo.TranUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: springboot
 * @description: 数据库事务测试用户Dao
 * @author: Holland
 * @create: 2019-10-16 12:26
 **/
@Repository
public interface UserDao {
    /**
     * 获取单个用户
     * @param id
     * @return
     */
    TranUser getTranUser(Long id);

    /**
     * 新增用户
     * @param tranUser
     * @return
     */
    int insertUser(TranUser tranUser);

    /**
     * 修改用户信息
     * @param tranUser
     * @return
     */
    int updateUser(TranUser tranUser);

    /**
     * 查询用户,指定mybatis的参数名称
     * @param userName
     * @param note
     * @return
     */
    List<TranUser> findUsers(@Param("userName") String userName,@Param("note") String note);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    int deleteUser(Long ids);
}
