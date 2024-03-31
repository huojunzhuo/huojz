package com.itheima.mydemo.controller;

import com.itheima.mydemo.entities.Pay;
import com.itheima.mydemo.entities.PayDTO;
import com.itheima.mydemo.service.PayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: PayController
 *
 * @Description TODO
 * @Author huojz
 * @project huojz
 * @create 2024 03 24 20:00
 */
@Log4j2
@RestController
public class PayController {
    @Autowired
    PayService payService;
    @PostMapping(value = "/pay/add")
    public String addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return "成功插入记录，返回值："+i;
    }
    @DeleteMapping(value = "/pay/del/{id}")
    public Integer deletePay(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }
    @PutMapping(value = "/pay/update")
    public String updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return "成功修改记录，返回值："+i;
    }
    @GetMapping(value = "/pay/get/{id}")
    public Pay getById(@PathVariable("id") Integer id){
        log.info("哈哈");
        return payService.getById(id);
    }//全部查询getall作为家庭作业

    @GetMapping(value = "/pay/getAll")
    public List<Pay> getAll(){
        log.info("哈哈");
        return payService.getAll();
    }
}
