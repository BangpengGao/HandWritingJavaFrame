package com.bang.peng.config;

import com.bang.peng.pojo.MappedStatement;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 15:32
 */
public class Configuration implements Serializable {

    private DataSource dataSource;

    Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

    public Configuration() {
    }

    public Configuration(DataSource dataSource, Map<String, MappedStatement> mappedStatementMap) {
        this.dataSource = dataSource;
        this.mappedStatementMap = mappedStatementMap;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "dataSource=" + dataSource +
                ", mappedStatementMap=" + mappedStatementMap +
                '}';
    }
}
