package com.itheima.mydemo.service.impl;

import com.itheima.mydemo.entities.Pay;
import com.itheima.mydemo.mapper.PayMapper;
import com.itheima.mydemo.service.PayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: PayServiceImpl
 *
 * @Description 实现类
 * @Author huojz
 * @project huojz
 * @create 2024 03 24 19:59
 */
@Service
@Log4j2
public class PayServiceImpl implements PayService {

    @Resource
    PayMapper payMapper;
    @Override
    public int add(Pay pay){
        log.info("哈哈");
        return payMapper.insertSelective(pay);
    }
    @Override
    public int delete(Integer id){
        return payMapper.deleteByPrimaryKey(id);
    }
    @Override
    public int update(Pay pay){
        return payMapper.updateByPrimaryKeySelective(pay);
    }
    @Override
    public Pay getById(Integer id){
        return payMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<Pay> getAll(){
        return payMapper.selectAll();
    }
}
