package com.laohu.springboot.mybatistran.service.impl;

import com.laohu.springboot.mybatistran.dao.UserDao;
import com.laohu.springboot.mybatistran.pojo.TranUser;
import com.laohu.springboot.mybatistran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     *
     * @Cacheable 注解
     * 先从缓存中定义的键查询数据,如果查询到数据则返回,否则执行该方法返回数据,并且将数据保存到缓存中
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,timeout = 1)
    @Cacheable(value = "redisCache",key = "'redis_user_'+#id")
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

    /**
     * @Description: 更新数据后,更新缓存,如果condition配置项使结果返回为null,则不缓存
     * @param: [id, userName]
     * @return: com.laohu.springboot.mybatistran.pojo.TranUser
     * @auther: Holland
     * @date: 2019/10/28 14:21
     *
     * condition 条件返回true,才会使用缓存操作,如果为false 则不使用缓存操作
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache",key = "'redis_user_'+#id",condition = "#result != 'null'")
    public TranUser updateUserName(Long id, String userName) {
        //此处调用的getUser方法,其上面的缓存注解会失效,所以此处还会查询数据库中最新的数据
        TranUser tranUser = this.getTranUser(id);
        if(tranUser == null){
            return null;
        }
        tranUser.setUserName(userName);
        userDao.updateUser(tranUser);
        return tranUser;
    }

    /**
     * @Description: 查询用户,因为根据条件的变化,查询出来的用户不相同的概率很大,因此缓存命中率会很低,对系统性能的提升没有太大的帮助
     * @param: [userName, note]
     * @return: java.util.List<com.laohu.springboot.mybatistran.pojo.TranUser>
     * @auther: Holland
     * @date: 2019/10/28 14:27
     */
    @Override
    @Transactional
    public List<TranUser> findUsers(String userName, String note) {
        return userDao.findUsers(userName,note);
    }

    /**
     * @Description: 删除用户
     * @param: [id]
     * @return: int
     * @auther: Holland
     * @date: 2019/10/28 12:25
     *
     * @CacheEvict 注解
     * value和key不谈了(注意el表达式,取出的参数名称要一致!),beforeInvocation 表示在该方法调用之前删除缓存还是调用之后删除缓存,
     * false为调用之后
     */
    @Override
    @Transactional
    @CacheEvict(value = "redisCache",key = "'redis_user_'+#id",beforeInvocation = false)
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    /**
     * @Description: 插入用户并返回用户
     * @param: [tranUser]
     * @return: com.laohu.springboot.mybatistran.pojo.TranUser
     * @auther: Holland
     * @date: 2019/10/28 12:06
     *
     * @CachePut 注解
     * value 指明redis缓存管理器,key 是将用户对象数据缓存到指定的键中,此处采用了spring的EL表达式,#result.id是取出返回结果中的id属性值
     */
    @Override
    @Transactional
    @CachePut(value = "redisCache",key = "'redis_user_'+#result.id")
    public TranUser saveUser(TranUser tranUser) {
        //在mapper文件中,开启了mybatis插入数据后回填数据到对象中
        userDao.insertUser(tranUser);
        //因此返回的这个用户对象,是拥有回填后的主键id属性值的
        return tranUser;
    }
}
