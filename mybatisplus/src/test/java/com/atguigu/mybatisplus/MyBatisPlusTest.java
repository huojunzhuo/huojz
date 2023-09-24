package com.atguigu.mybatisplus;

import cn.hutool.core.util.StrUtil;
import com.atguigu.mybatisplus.enums.SexEnum;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.Address;
import com.atguigu.mybatisplus.pojo.Enterprise;
import com.atguigu.mybatisplus.pojo.User;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.*;

/**
 * Date:2022/2/12
 * Author:ybc
 * Description:
 */
@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void testSelectList(){
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }
    @Autowired
    private MapperFacade mapperFacade;
    @Test
    public void testInsert(){
        //实现新增用户信息
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        //user.setId(100L);
        user.setName("张三L");
        user.setAge(23);
        user.setEmail("zhangsan@atguigu.com");
        user.setSex(SexEnum.MALE);
//        user.setId(10L);
        int result = userMapper.insert(user);
        System.out.println("result:"+result);
        //1475754982694199298
        System.out.println("id:"+user.getId());

    }
    @Test
    public void testJoin(){
        Map<String, Object> map = Map.of("name", "Tom", "age", 20);
        List.of();
        Set<Integer> integers = Set.of(1, 2, 3);
        String join = StrUtil.join(",", map);
        System.out.println("join = " + join);
        System.out.println(StrUtil.join(";", integers));
    }

    @Test
    public void testDelete(){
        //通过id删除用户信息
        //DELETE FROM user WHERE id=?
        /*int result = userMapper.deleteById(1492767055210991617L);
        System.out.println("result:"+result);*/
        //根据map集合中所设置的条件删除用户信息
        //DELETE FROM user WHERE name = ? AND age = ?
        /*Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("age", 23);
        int result = userMapper.deleteByMap(map);
        System.out.println("result:"+result);*/
        //通过多个id实现批量删除
        //DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(1L, 2L, 3L);
//        int result = userMapper.deleteBatchIds(list);
//        System.out.println("result:"+result);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("name","张三L");
        hashMap.put("age",23);
        userMapper.deleteByMap(hashMap);
    }

    @Test
    public void testUpdate(){
        //修改用户信息
        //UPDATE user SET name=?, email=? WHERE id=?
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@atguigu.com");
        int result = userMapper.updateById(user);

        System.out.println("result:"+result);
    }

    @Test
    public void testSelect(){
        //通过id查询用户信息
        //SELECT id,name,age,email FROM user WHERE id=?
        /*User user = userMapper.selectById(1L);
        System.out.println(user);*/
        //根据多个id查询多个用户信息
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        /*List<Long> list = Arrays.asList(1L, 2L, 3L);
        List<User> users = userMapper.selectBatchIds(list);
        users.forEach(System.out::println);*/
        //根据map集合中的条件查询用户信息
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        /*Map<String, Object> map = new HashMap<>();
        map.put("name", "Jack");
        map.put("age", 20);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);*/
        //查询所有数据
        //SELECT id,name,age,email FROM user
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        /*Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);*/
    }
    @Autowired
    private Enterprise enterprise;
    @Autowired
    private Address address;
    @Test
    public void testYml(){
        System.out.println(enterprise);
        System.out.println(address);
    }

}
