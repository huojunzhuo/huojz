package com.itheima.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.entities.Pay;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PayMapper extends Mapper<Pay> , BaseMapper<Pay> {

    @Select("select * from t_pay ")
    public List<Pay> selecAll();
}