package com.itheima.hmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: ItemApplication
 *
 * @Description TODO
 * @Author huojz
 * @project huojz
 * @create 2023 09 28 15:30
 */
@MapperScan("com.itheima.hmall.mapper")
@SpringBootApplication
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}
