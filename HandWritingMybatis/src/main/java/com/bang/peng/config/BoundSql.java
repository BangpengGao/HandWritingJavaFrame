package com.bang.peng.config;

import com.bang.peng.util.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 15:59
 */
public class BoundSql {
    private String sqlText;

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql() {
    }

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }

    @Override
    public String toString() {
        return "BoundSql{" +
                "sqlText='" + sqlText + '\'' +
                ", parameterMappingList=" + parameterMappingList +
                '}';
    }
}
