package com.atguigu.mybatisplus.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * ClassName: Address
 * Package: com.atguigu.mybatisplus.pojo
 * Description:
 *
 * @Author huojz
 * @Create 2023/9/7 20:48
 * @Version 1.0
 */

//@Component
@Data
@Component
@ConfigurationProperties(prefix = "enterprise.address")
public class Address {
    String name;
    Integer addr;

}
