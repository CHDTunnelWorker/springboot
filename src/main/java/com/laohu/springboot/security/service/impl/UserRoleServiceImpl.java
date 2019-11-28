package com.laohu.springboot.security.service.impl;

import com.laohu.springboot.security.entity.DatabaseRole;
import com.laohu.springboot.security.entity.DatabaseUser;
import com.laohu.springboot.security.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springboot
 * @description: 获取用户信息的自定义接口实现类
 * @author: Holland
 * @create: 2019-11-27 10:23
 **/
@Service
public class UserRoleServiceImpl implements UserRoleService {

    /**
     * @Description: 对用户信息的查询获取不做实现
     * @param: [userName]
     * @return: com.laohu.springboot.security.entity.DatabaseUser
     * @auther: Holland
     * @date: 2019/11/27 10:23
     */
    @Override
    public DatabaseUser getUserByName(String userName) {
        return null;
    }

    /**
     * @Description: 根据用户名称获取角色信息对象集合(不做实现,可根据具体情况实现查询用户角色的逻辑)
     * @param: String userName
     * @return: List<DatabaseRole>
     * @auther: Holland
     * @date: 2019/11/27 10:20
     */
    @Override
    public List<DatabaseRole> findRolesByUsersName(String userName) {
        return null;
    }
}
