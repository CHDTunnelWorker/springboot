package com.laohu.springboot.mybatistran.pojo;

import com.laohu.springboot.mybatistran.enumeration.SexEnum;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @program: springboot
 * @description: 用户类
 * @author: Holland
 * @create: 2019-10-15 17:22
 *
 * @Alias(value = "user") 这个注解用于定义别名,方便在mapper.xml中使用,但是个人不建议这样使用别名,因为协作开发,还是写全路径名称比较好
 **/
@Data
@Alias(value = "user")
public class User {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 备注
     */
    private String note;
    /**
     * 性别枚举,这里可以使用一下typeHandler 类型转换(数据库存性别编号,回显显示枚举)
     */
    private SexEnum sex;
}
