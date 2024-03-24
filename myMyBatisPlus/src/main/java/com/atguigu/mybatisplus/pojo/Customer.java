package com.atguigu.mybatisplus.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * ClassName: Customer
 * Package: com.atguigu.mybatisplus.pojo
 * Description:
 *
 * @Author huojz
 * @Create 2023/9/13 17:56
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
