package com.itheima.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName: User
 *
 * @Description User
 * @Author huojz
 * @project huojz
 * @create 2024 04 06 16:47
 */
@Data
@TableName("user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
