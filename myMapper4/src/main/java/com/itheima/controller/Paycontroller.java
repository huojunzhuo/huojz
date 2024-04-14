package com.itheima.controller;

import com.itheima.entities.Pay;
import com.itheima.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: Paycontroller
 *
 * @Description TODO
 * @Author huojz
 * @project huojz
 * @create 2024 04 14 15:51
 */
@RestController
public class Paycontroller {
    @Autowired
    PayService payService;
    @GetMapping("/pay")
    public List<Pay> searchPay(){
        return payService.findPays();
    }
}
