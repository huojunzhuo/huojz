package com.glodon.dao;

import lombok.Data;

/**
 * ClassName: User
 *
 * @Description User
 * @Author huojz
 * @project huojz
 * @create 2023 09 17 10:39
 */
@Data
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String tel;

}
