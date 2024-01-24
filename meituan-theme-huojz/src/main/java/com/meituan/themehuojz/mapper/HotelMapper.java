package com.meituan.themehuojz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meituan.themehuojz.po.Hotel;
import com.meituan.themehuojz.po.HotelSupplierBind;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: HotelMapper
 * Package: com.meituan.themehuojz.mapper
 * Description: HotelMapper
 *
 * @Author huojz
 * @Create 2024/1/22 10:36
 * @Version 1.0
 */
@Mapper
public interface HotelMapper extends BaseMapper<Hotel> {
}
