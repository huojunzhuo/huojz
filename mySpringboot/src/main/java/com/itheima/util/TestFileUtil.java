package com.itheima.util;

import java.net.URL;

/**
 * ClassName: TestFileUtil
 *
 * @Description TODO
 * @Author huojz
 * @project huojz
 * @create 2024 12 21 17:33
 */
public class TestFileUtil {

    public static String getPath(){
        ClassLoader classLoader = TestFileUtil.class.getClassLoader();
        URL resource = classLoader.getResource("");
        String path = resource.getPath().replace("test-classes/","");
        return path;
    }

    public static void main(String[] args) {
        String path = getPath();
        System.out.println("path = " + path);
    }
}
