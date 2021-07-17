package com.bang.peng.sqlsession;

import com.bang.peng.config.Configuration;
import com.bang.peng.config.XmlConfig;

import java.io.InputStream;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 17:15
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws Exception {
        XmlConfig xmlConfig = new XmlConfig();
        Configuration configuration = xmlConfig.parse(in);

        return new DefaultSqlSessionFactory(configuration);
    }

}
