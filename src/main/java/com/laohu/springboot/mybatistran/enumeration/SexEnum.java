package com.laohu.springboot.mybatistran.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: springboot
 * @description: 性别枚举类
 * @author: Holland
 * @create: 2019-10-16 09:12
 **/
@AllArgsConstructor
public enum SexEnum {
    /**
     * 男 枚举
     */
    MALE(1,"男"),
    /**
     * 女 枚举
     */
    FEMALE(2,"女");

    /**
     * 性别编号
     */
    @Getter
    private int id;
    /**
     * 性别名称
     */
    @Getter
    private String name;

    /**
     * @Description: 
     * @param: [id]
     * @return: com.laohu.springboot.mybatistran.enumeration.SexEnum
     * @auther: Holland
     * @date: 2019/10/16 9:18
     */
    public static SexEnum getEnumById(int id){
        for (SexEnum sex : SexEnum.values()){
            if(sex.getId() == id){
                return sex;
            }
        }
        return null;
    }
}
