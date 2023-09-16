package com.atguigu.mybatisplus.controller;

import com.atguigu.mybatisplus.pojo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * ClassName: CustomerController
 * Package: com.atguigu.mybatisplus.controller
 * Description:
 *
 * @Author huojz
 * @Create 2023/9/13 17:58
 * @Version 1.0
 */
@RestController
public class CustomerController {
    @RequestMapping("/test")
    public Customer customer(@RequestBody Customer customer){
        Customer customer1 = new Customer();
        customer1.setName("Lisi");
        customer1.setCreateTime(LocalDateTime.now());
        return customer1;
    }
}
