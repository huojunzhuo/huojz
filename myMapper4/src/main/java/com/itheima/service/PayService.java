package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.entities.Pay;

import java.util.List;


/**
* @author HJZ
* @description 针对表【t_pay(支付交易表)】的数据库操作Service
* @createDate 2024-04-06 17:19:31
*/
public interface PayService extends IService<Pay> {

    public List<Pay> findPays();
}
