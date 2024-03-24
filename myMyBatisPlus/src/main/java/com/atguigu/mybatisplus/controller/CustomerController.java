package com.atguigu.mybatisplus.controller;

import com.atguigu.mybatisplus.pojo.Customer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/test/test")
    public Customer customer(@RequestBody Customer customer){
        Customer customer1 = new Customer();
        customer1.setName("Lisi");
        customer1.setCreateTime(LocalDateTime.now());
        return customer1;
    }
    @GetMapping("/customer")
    public String testFeign( String str ,@RequestHeader(value ="truth" ,required = false )String truth){
        System.out.println(str + str);
        System.out.println("truth = " + truth);
        return str+str;
    }
}
