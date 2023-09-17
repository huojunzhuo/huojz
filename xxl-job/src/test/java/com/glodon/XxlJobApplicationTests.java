package com.glodon;

import com.glodon.dao.User;
import com.glodon.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class XxlJobApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    public void userMapperTest() {
        List<User> users = userMapper.selectList(null);
        users.forEach(item->{
            System.out.println("item = " + item);
        });
    }

}
