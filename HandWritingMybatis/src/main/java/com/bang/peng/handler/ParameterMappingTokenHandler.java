package com.bang.peng.handler;

import com.bang.peng.util.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 16:05
 */
public class ParameterMappingTokenHandler implements TokenHandler{

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    @Override
    public String handleToken(String content) {
        parameterMappingList.add(buildParameterMapping(content));
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        ParameterMapping parameterMapping = new ParameterMapping(content);
        return parameterMapping;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }

    @Override
    public String toString() {
        return "ParameterMappingTokenHandler{" +
                "parameterMappingList=" + parameterMappingList +
                '}';
    }
}
