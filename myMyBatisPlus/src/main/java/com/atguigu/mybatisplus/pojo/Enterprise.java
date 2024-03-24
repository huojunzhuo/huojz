package com.atguigu.mybatisplus.pojo;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: Enterprise
 * Package: com.atguigu.mybatisplus.pojo
 * Description:
 *
 * @Author huojz
 * @Create 2023/9/7 20:47
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "enterprise")
@Data
public class Enterprise {
    String name;
    Integer age;
    Address address;
}
