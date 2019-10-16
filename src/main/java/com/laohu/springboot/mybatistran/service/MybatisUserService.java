package com.laohu.springboot.mybatistran.service;

import com.laohu.springboot.mybatistran.pojo.User;

public interface MybatisUserService {
    /**
     * 通过主键id获得用户对象
     * @param id
     * @return
     */
    public User getUser(Long id);
}
