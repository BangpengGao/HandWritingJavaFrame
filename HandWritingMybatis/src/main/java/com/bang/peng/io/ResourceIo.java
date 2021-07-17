package com.bang.peng.io;

import java.io.InputStream;

/*
 * @ Created with IntelliJ IDEA
 * @ Author Bangpeng Gao
 * @ Version 1.0.0
 * @ Since 1.0.0
 * @ Date 2021/7/16
 * @ Time 15:55
 */
public class ResourceIo {

    public static InputStream getResourceAsStream(String path) {
        InputStream in = ResourceIo.class.getClassLoader().getResourceAsStream(path);
        return in;
    }

}
