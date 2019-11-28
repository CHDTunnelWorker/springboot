package com.laohu.springboot.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @program: springboot
 * @description: 数据库用户表
 * @author: Holland
 * @create: 2019-11-27 10:14
 **/
@Data
public class DatabaseUser implements Serializable {
    /**
     * 用户主键id
     */
    @Id
    private Long id;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 加密密码
     */
    private String pwd;
    /**
     * 用户是否生效
     */
    private Integer available;
    /**
     * 备注
     */
    private String note;
}
