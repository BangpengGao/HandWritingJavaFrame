package com.bang.peng.config;

import com.bang.peng.constant.Constant;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 15:04
 */
public class XmlConfig {

    private Configuration configuration;

    public XmlConfig() {
    }

    public XmlConfig(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration parse(InputStream in) throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        Element rootElement = document.getRootElement();

        final ComboPooledDataSource dataSource = new ComboPooledDataSource();
        List<Element> list = rootElement.selectNodes("//property");
        list.forEach((element) -> {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            if (name.equals(Constant.DRIVER_CLASS)) {
                try {
                    dataSource.setDriverClass(value);
                } catch (PropertyVetoException e) {
                    throw new RuntimeException("数据库驱动配置错误！");
                }
            } else if (name.equals(Constant.JDBC_URL)) {
                dataSource.setJdbcUrl(value);
            } else if (name.equals(Constant.USER_NAME)) {
                dataSource.setUser(value);
            } else if (name.equals(Constant.PASSWORD)) {
                dataSource.setPassword(value);
            } else {
                throw new RuntimeException("数据库配置字段错误！");
            }
        });
        configuration.setDataSource(dataSource);
        XMLMapperConfig config = new XMLMapperConfig(configuration);
        config.parseMapper(rootElement);
        return configuration;
    }
}
