package com.laohu.springboot.security.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @program: springboot
 * @description: 角色表对应的实体类
 * @author: Holland
 * @create: 2019-11-27 10:17
 **/
@Data
public class DatabaseRole implements Serializable {
    /**
     * 角色主键id
     */
    @Id
    private Long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 备注
     */
    private String note;
}
