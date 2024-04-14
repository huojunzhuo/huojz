package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.entities.Pay;
import com.itheima.mapper.PayMapper;
import com.itheima.service.PayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author HJZ
* @description 针对表【t_pay(支付交易表)】的数据库操作Service实现
* @createDate 2024-04-06 17:19:31
*/
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay>
    implements PayService {

    @Resource
    PayMapper payMapper;
    @Override
    public List<Pay> findPays() {
        return payMapper.selecAll();
    }
}




