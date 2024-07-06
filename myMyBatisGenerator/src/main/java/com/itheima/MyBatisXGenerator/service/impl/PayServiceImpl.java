package com.itheima.MyBatisXGenerator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.MyBatisXGenerator.service.PayService;
import com.itheima.MyBatisXGenerator.mapper.PayMapper;
import com.itheima.MyBatisXGenerator.domain.Pay;
import org.springframework.stereotype.Service;

/**
* @author HJZ
* @description 针对表【t_pay(支付交易表)】的数据库操作Service实现
* @createDate 2024-04-06 17:19:31
*/
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay>
    implements PayService{

}




