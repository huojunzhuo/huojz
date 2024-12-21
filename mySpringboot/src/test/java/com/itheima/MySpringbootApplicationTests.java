package com.itheima;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

@SpringBootTest
class MySpringbootApplicationTests {

    @Test
    void contextLoads() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("");
        String path = resource.getPath();
        System.out.println("path = " + path);
    }

}
