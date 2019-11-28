package com.laohu.springboot.security.service;

import com.laohu.springboot.security.entity.DatabaseRole;
import com.laohu.springboot.security.entity.DatabaseUser;

import java.util.List;

/**
 * @Description:用户自定义获取用户信息的接口(不做实现,仅服务于配置springsecurity自定义验证)
 * @auther: Holland
 * @date: 2019/11/27 10:12
 */
public interface UserRoleService {
    /**
     * @Description: 根据用户名称获取用户信息对象(不做实现,可根据具体情况实现查询用户信息的逻辑)
     * @param: String userName
     * @return: DatabaseUser
     * @auther: Holland
     * @date: 2019/11/27 10:20
     */
    DatabaseUser getUserByName(String userName);
    /**
     * @Description: 根据用户名称获取角色信息对象集合(不做实现,可根据具体情况实现查询用户角色的逻辑)
     * @param: String userName
     * @return: List<DatabaseRole>
     * @auther: Holland
     * @date: 2019/11/27 10:20
     */
    List<DatabaseRole> findRolesByUsersName(String userName);
}
