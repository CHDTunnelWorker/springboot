package com.laohu.springboot.security.service.impl;

import com.laohu.springboot.security.entity.DatabaseRole;
import com.laohu.springboot.security.entity.DatabaseUser;
import com.laohu.springboot.security.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springboot
 * @description: springsecurity定义的用户信息接口实现类(该实现类中的方法仅完成用户名称,用户密码和角色名称的绑定)
 * 并且 该实现类 ,需要注册给springsecurity,供其发现和调用)
 * @author: Holland
 * @create: 2019-11-27 10:21
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 注入查询用户信息服务接口
     */
    @Autowired
    private UserRoleService userRoleService;

    /**
     * @Description:该方法是实现springsecuity的UserDetailsService接口时,必须实现的方法
     * @param: [username]
     * @return: org.springframework.security.core.userdetails.UserDetails 用户详细信息 包含用户名 密码和角色名称
     * @auther: Holland
     * @date: 2019/11/27 10:37
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户对象
        DatabaseUser dbUser = userRoleService.getUserByName(username);
        //获取数据库角色信息
        List<DatabaseRole> roleList = userRoleService.findRolesByUsersName(username);
        //返回UserDetails
        return changeToUser(dbUser,roleList);
    }

    private UserDetails changeToUser(DatabaseUser dbUser, List<DatabaseRole> roleList){
        //新建权限列表
        List<GrantedAuthority> authorityList = new ArrayList<>();
        //将该用户查询到的角色列表
        for(DatabaseRole role : roleList){
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorityList.add(authority);
        }
        //通过userDetails旗下的user类的构造方法 将用户名 密码和角色列表封装到UserDetails(这是个接口,user是其实现类)对象中
        UserDetails userDetails = new User(dbUser.getUserName(), dbUser.getPwd(), authorityList);
        return userDetails;
    }
}
