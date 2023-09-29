package com.itheima.hmall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.itheima.hmall.domain.po.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

}
