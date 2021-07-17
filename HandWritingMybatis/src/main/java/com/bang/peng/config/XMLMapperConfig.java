package com.bang.peng.config;


import com.bang.peng.constant.Constant;
import com.bang.peng.io.ResourceIo;
import com.bang.peng.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 15:28
 */
public class XMLMapperConfig {

    private Configuration configuration;

    public XMLMapperConfig(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parseMapper(Element element) {
        List<Element> list = element.selectNodes("//mapper");
        list.forEach((elementItem) -> {
            String mapperPath = elementItem.attributeValue("resource");
            InputStream in = ResourceIo.getResourceAsStream(mapperPath);
            try {
                parse(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void parse(InputStream in) throws Exception {
        Document document = new SAXReader().read(in);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        setMappedStatement(rootElement, Constant.SELECT, namespace);
        setMappedStatement(rootElement, Constant.UPDATE, namespace);
        setMappedStatement(rootElement, Constant.INSERT, namespace);
        setMappedStatement(rootElement, Constant.DEL, namespace);
    }

    public void setMappedStatement(Element element, String sqlType, String namespace) {
        List<Element> list = element.selectNodes("//" + sqlType);
        for (Element elementItem : list) {
            String id = elementItem.attributeValue("id");
            String resultType = elementItem.attributeValue("resultType");
            String parameterType = elementItem.attributeValue("parameterType");
            String sql = elementItem.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement(id, resultType, parameterType, sql, sqlType);
            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key, mappedStatement);
        }
    }
}
