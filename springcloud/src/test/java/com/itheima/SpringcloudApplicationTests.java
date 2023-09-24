package com.itheima;

import com.itheima.springcloud.domain.User;
import com.itheima.springcloud.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringcloudApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testConnection() {
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
    }

}
