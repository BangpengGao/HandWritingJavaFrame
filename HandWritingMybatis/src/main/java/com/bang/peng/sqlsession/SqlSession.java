package com.bang.peng.sqlsession;

import java.util.List;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 16:24
 */
public interface SqlSession {

    <E> List<E> findAll(String statementId, Object... params) throws Exception;

    <E> E findByCondition(String statementId, Object... params) throws Exception;

    int update(String statementId, Object... params) throws Exception;

    int insert(String statementId, Object... params) throws Exception;

    int delete(String statementId, Object... params) throws Exception;
}
