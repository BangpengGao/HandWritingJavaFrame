package com.bang.peng.util;
/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 11:35
 */

public class ParameterMapping {
    private String content;

    public ParameterMapping() {
    }

    public ParameterMapping(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ParameterMapping{" +
                "content='" + content + '\'' +
                '}';
    }
}
