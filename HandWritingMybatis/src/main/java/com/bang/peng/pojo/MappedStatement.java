package com.bang.peng.pojo;

import java.io.Serializable;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 15:35
 */
public class MappedStatement implements Serializable {
    /** id标识=namespace+“.”+id */
    private String id;
    /** 返回值类型 */
    private String resultType;
    /** 参数值类型 */
    private String parameterType;
    /** SQL语句 */
    private String sql;
    /** SQL语句类型 */
    private String sqlType;

    public MappedStatement() {
    }

    public MappedStatement(String id, String resultType, String parameterType, String sql, String sqlType) {
        this.id = id;
        this.resultType = resultType;
        this.parameterType = parameterType;
        this.sql = sql;
        this.sqlType = sqlType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    @Override
    public String toString() {
        return "MappedStatement{" +
                "id='" + id + '\'' +
                ", resultType='" + resultType + '\'' +
                ", parameterType='" + parameterType + '\'' +
                ", sql='" + sql + '\'' +
                ", sqlType='" + sqlType + '\'' +
                '}';
    }
}
