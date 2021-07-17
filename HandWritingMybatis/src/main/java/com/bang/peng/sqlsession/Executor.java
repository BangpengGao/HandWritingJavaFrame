package com.bang.peng.sqlsession;

import com.bang.peng.config.Configuration;
import com.bang.peng.pojo.MappedStatement;

import java.util.List;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 16:28
 */
public interface Executor {

    <E> List<E> find(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    int update(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    int insert(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    int delete(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
