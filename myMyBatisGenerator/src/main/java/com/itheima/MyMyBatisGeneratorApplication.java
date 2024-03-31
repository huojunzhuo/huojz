package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.itheima.mydemo.mapper")
public class MyMyBatisGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMyBatisGeneratorApplication.class, args);
    }

}
