package com.laohu.springboot.mybatistran.typehandler;

import com.laohu.springboot.mybatistran.enumeration.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @program: springboot
 * @description: 性别类型转换器
 * @author: Holland
 * @create: 2019-10-16 09:41
 *
 * @MappedJdbcTypes(JdbcType.INTEGER) 声明数据库中的想转换的列的数据类型
 * @MappedTypes(SexEnum.class) 声明在java中要转换成为的类型
 **/

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(SexEnum.class)
public class SexTypeHandler extends BaseTypeHandler<SexEnum> {

    /**
     * 设置非空性别参数
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,parameter.getId());
    }

    /**
     * 通过列名读取性别
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int sex = rs.getInt(columnName);
        if(sex != 1 && sex != 2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    /**
     * 通过下标读取性别
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int sex = rs.getInt(columnIndex);
        if(sex != 1 && sex != 2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }

    /**
     * 通过存储过程读取性别
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int sex = cs.getInt(columnIndex);
        if(sex != 1 && sex != 2){
            return null;
        }
        return SexEnum.getEnumById(sex);
    }
}
