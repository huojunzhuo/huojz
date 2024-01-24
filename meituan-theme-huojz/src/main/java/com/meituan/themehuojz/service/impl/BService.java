package com.meituan.themehuojz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meituan.themehuojz.mapper.HotelMapper;
import com.meituan.themehuojz.po.Hotel;
import com.meituan.themehuojz.handler.NumberHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description BService
 * @Author huojz
 * @project tingshu-project
 * @create 2024 01 20 15:16
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class BService extends ServiceImpl<HotelMapper, Hotel> implements Runnable{

    private NumberHandler numberHandler;
    /**
     * 题目1方法：BService分批处理id列表
     * @param ids
     * @return
     */
    public static List<Integer> get(List<Integer> ids) {
        List<Integer> result = new ArrayList<>();
        int size = ids.size();
        int firstIndex = 0;
        if (ids.isEmpty() || ids.size() >= 100) {
            return result;
        }
        while (firstIndex < size) {
            int LastIndex = firstIndex + 10;
            if (firstIndex + 10 > size) {
                LastIndex = size;
            }
            List<Integer> subList = ids.subList(firstIndex, LastIndex);
            List<Integer> integers = AService.get(subList);
            result.addAll(integers);
            firstIndex = LastIndex;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("*********************************************");
        }
        return result;
    }

    /**
     * 题目2方法2
     * @param poiIds
     * @return
     */
    public  List<Hotel> batchGetHotelByPoiId(List<Long> poiIds) {

        //1.根据酒店id集合查询【酒店表】
        LambdaQueryWrapper<Hotel> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(CollectionUtil.isNotEmpty(poiIds),Hotel::getId,poiIds);
        return this.list(wrapper);
    }

    /**
     * 题目3：线程执行的方法生成数据的方法
     */
    @Override
    public void run() {
        numberHandler.addValue();
    }
}


