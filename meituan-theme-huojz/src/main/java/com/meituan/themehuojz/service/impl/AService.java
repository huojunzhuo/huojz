package com.meituan.themehuojz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meituan.themehuojz.mapper.HotelSupplierBindMapper;
import com.meituan.themehuojz.po.HotelSupplierBind;
import com.meituan.themehuojz.handler.NumberHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: AService
 * Package: com.meituan.themehuojz.service.impl
 * Description: AService
 *
 * @Author huojz
 * @Create 2024 01 20 15:16
 * @Version 1.0
 */
@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AService extends ServiceImpl<HotelSupplierBindMapper, HotelSupplierBind> implements Runnable{

    private NumberHandler numberHandler;
    /**
     * 题目1方法1：批量处理id
     * @param ids
     * @return
     */
    public static List<Integer> get(List<Integer> ids) {
        for (Integer id : ids) {
            System.out.println("AService处理数据：" + id);
        }
        return ids;
    }

    /**
     * 题目2方法1：根据供应商ID，返回关联的poiId集合
     * @param partnerIds
     * @return
     */
    public  List<Long> getHotelByParnterId(List<Long> partnerIds) {
        //1.封装mybatis-plus查询条件：partnerId in partnerIds；
        LambdaQueryWrapper<HotelSupplierBind> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(CollectionUtil.isNotEmpty(partnerIds),HotelSupplierBind::getSupplierId,partnerIds);
        //2.根据查询条件查询【供应商-酒店关联】表列表；
        AService aService = SpringUtil.getBean(AService.class);
        List<HotelSupplierBind> list = aService.list(wrapper);
        //3.判断查询列表是否为空，如果是则处理；
        List<Long> poidList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(list)){
            poidList = list.stream().map(HotelSupplierBind::getPoiId).collect(Collectors.toList());
        }
        //4.stream流遍历上一步骤所得列表,通过map()映射到poiIds列表，返回结果集
        return poidList;
    }

    /**
     * 题目3：线程执行的方法生成数据的方法
     */
    @Override
    public void run() {
        numberHandler.addValue();
    }
}
