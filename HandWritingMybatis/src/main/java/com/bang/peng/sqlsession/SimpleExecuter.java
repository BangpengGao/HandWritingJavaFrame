package com.bang.peng.sqlsession;

import com.bang.peng.config.BoundSql;
import com.bang.peng.config.Configuration;
import com.bang.peng.handler.ParameterMappingTokenHandler;
import com.bang.peng.parser.GenericTokenParser;
import com.bang.peng.pojo.MappedStatement;
import com.bang.peng.util.ParameterMapping;
import com.mysql.cj.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 16:30
 */
public class SimpleExecuter implements Executor {
    @Override
    public <E> List<E> find(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        PreparedStatement preparedStatement = getResult(configuration, mappedStatement, params);

        ResultSet resultSet = preparedStatement.executeQuery();
        return (List<E>) getLastResult(resultSet, mappedStatement);
    }

    @Override
    public int update(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        PreparedStatement preparedStatement = getResult(configuration, mappedStatement, params);
        return preparedStatement.executeUpdate();
    }

    @Override
    public int insert(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        PreparedStatement preparedStatement = getResult(configuration, mappedStatement, params);
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        PreparedStatement preparedStatement = getResult(configuration, mappedStatement, params);
        return preparedStatement.executeUpdate();
    }

    private Object getLastResult(ResultSet resultSet, MappedStatement mappedStatement) throws Exception {
        String resultType = mappedStatement.getResultType();
        if (resultType.equals("int") || resultType.equals("long"))
            return resultSet.getMetaData();
        Class<?> resultTypeClass = getClassType(resultType);

        List<Object> objectList = new ArrayList<>();

        while (resultSet.next()) {
            assert resultTypeClass != null;
            Object o = resultTypeClass.getDeclaredConstructor().newInstance();

            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);

                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method method = propertyDescriptor.getWriteMethod();
                method.invoke(o, value);
            }
            objectList.add(o);
        }
        return objectList;
    }

    private Class<?> getClassType(String resultType) throws Exception {
        if (!StringUtils.isNullOrEmpty(resultType)) {
            return Class.forName(resultType);
        }
        return null;
    }

    private PreparedStatement getResult(Configuration configuration, MappedStatement mappedStatement, Object[] params) throws Exception {
        Connection connection = configuration.getDataSource().getConnection();

        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        String parameterType = mappedStatement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping mapping = parameterMappingList.get(i);
            String content = mapping.getContent();

            Field declaredField = parameterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i + 1, o);
        }
        return preparedStatement;
    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);

        String parseSql = tokenParser.parse(sql);

        List<ParameterMapping> parameterMappingList = tokenHandler.getParameterMappingList();
        return new BoundSql(parseSql, parameterMappingList);
    }
}
