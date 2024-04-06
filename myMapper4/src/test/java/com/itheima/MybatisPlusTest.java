package com.itheima;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itheima.entities.Pay;
import com.itheima.entities.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.PayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName: MybatisPlusTest
 *
 * @Description MybatisPlusTest
 * @Author huojz
 * @project huojz
 * @create 2024 04 06 16:53
 */
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    UserMapper userMapper;
    @Autowired
    PayService payService;
    @Test
    public void testMp(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
    @Test
    public void testPayService(){
        LambdaQueryWrapper<Pay> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Pay::getId,List.of(1,2));
        List<Pay> pays = payService.list(queryWrapper);
        pays.forEach(System.out::println);
    }

}
