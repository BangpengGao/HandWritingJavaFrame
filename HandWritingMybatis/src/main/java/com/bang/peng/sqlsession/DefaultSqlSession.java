package com.bang.peng.sqlsession;

import com.bang.peng.config.Configuration;

import java.util.List;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 16:24
 */
public class DefaultSqlSession implements SqlSession{

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.executor = new SimpleExecuter();
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> findAll(String statementId, Object... params) throws Exception {
        return null;
    }

    @Override
    public <E> E findByCondition(String statementId, Object... params) throws Exception {
        return null;
    }

    @Override
    public int update(String statementId, Object... params) throws Exception {
        return 0;
    }

    @Override
    public int insert(String statementId, Object... params) throws Exception {
        return 0;
    }

    @Override
    public int delete(String statementId, Object... params) throws Exception {
        return 0;
    }
}
