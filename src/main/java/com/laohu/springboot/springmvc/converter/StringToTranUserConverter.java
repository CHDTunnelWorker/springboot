package com.laohu.springboot.springmvc.converter;

import com.laohu.springboot.mybatistran.pojo.TranUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: springboot
 * @description: 测试将字符串转换成tranuser对象, 自定义参数转换器
 * @author: Holland
 * @create: 2019-10-30 10:43
 *
 * 由于对于参数转换器的扫描在springboot中是自动注册的,只要继承了Converter接口接口即可.不用单独自行配置到配置类中
 * springboot自动注册机制,DefaultFormattingConversionService是管理参数转换器的默认实现类,其中实现了对BEAN容器中的指定
 * 三大参数转换器接口(Converter,Formatter,GenericConverter)的遍历扫描加入参数转化器注册机
 *
 * 注意:此时测试的访问路径中的参数名称,是泛型T需要转成的类型(即TranUser对象)的驼峰名称(并不是控制器中的方法参数名称,不是一一对应的关系),
 * 即tranUser=1-user_name_1-note_1,才能够被该转换器识别并进行参数转换处理!!!
 **/
@Component
public class StringToTranUserConverter implements Converter<String, TranUser> {

    /**
     * @Description: 自定义转换方法(将{id}-{userName}-{note}格式的字符串转化为tranuser对象)
     * @param: [s]
     * @return: com.laohu.springboot.mybatistran.pojo.TranUser
     * @auther: Holland
     * @date: 2019/10/30 10:46
     */
    @Override
    public TranUser convert(String userStr) {
        TranUser user = new TranUser();
        if(!StringUtils.isEmpty(userStr)){
            String[] strArgs = userStr.split("-");
            long id = Long.parseLong(strArgs[0]);
            String userName = strArgs[1];
            String note = strArgs[2];
            user.setId(id).setUserName(userName).setNote(note);
        }
        return user;
    }

}
