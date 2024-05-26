package com.itheima.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.common.GeneralDAO;
import com.itheima.entities.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PayMapper extends GeneralDAO<Pay>, BaseMapper<Pay>  {

    @Select("select * from t_pay ")
    public List<Pay> selecAll();
}