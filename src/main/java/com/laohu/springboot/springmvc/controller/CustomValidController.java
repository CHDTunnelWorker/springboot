package com.laohu.springboot.springmvc.controller;

import com.laohu.springboot.mybatistran.pojo.TranUser;
import com.laohu.springboot.springmvc.validator.UserValidator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description: 测试自定义验证器的控制类,进行验证器绑定与验证
 * @author: Holland
 * @create: 2019-10-31 10:54
 **/
@RequestMapping("/customValid")
@Controller
public class CustomValidController {

    /**
     * 绑定验证器,@InitBinder注解的方法,会在调用控制器方法前执行
     * 注:参数转换器会先与这个注解的方法执行,也就是说执行这个验证时,参数已经被转换封装
     * @param binder
     */
    @InitBinder
    public void initCustomValidator(WebDataBinder binder){
        //绑定验证器
        binder.setValidator(new UserValidator());
        /*
            WebDataBinder还支持对参数进行自定义,例如可以设置获取参数的格式,这样就不需要使用＠DateTimeForm注解来获取参数...
            false表示是否允许参数为空
         */
        binder.registerCustomEditor(Date.class,
            new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));
    }

    /**
     * 测试自定义验证器是否起作用
     * @param user 用户对象会被StringToTranUserConverter转换
     * @param errors
     * @param date
     * @return
     *
     * 测试路径:http://localhost:9000/customValid/customValidator?tranUser=1--note_1&date=2018-01-01
     */
    @RequestMapping("/customValidator")
    @ResponseBody
    public Map<String,Object> customValidator(@Valid TranUser user, Errors errors,Date date){
        Map<String,Object> map = new HashMap<>(16);
        map.put("user",user);
        map.put("date",date);
        //判断是否存在错误
        List<ObjectError> allErrors = errors.getAllErrors();
        for(ObjectError oe : allErrors){
            //判断是否是字段错误
            if(oe instanceof FieldError){
                FieldError fe = (FieldError) oe;
                map.put(fe.getField(),fe.getDefaultMessage());
            } else {
                map.put(oe.getObjectName(),oe.getDefaultMessage());
            }
        }
        return map;
    }

}
