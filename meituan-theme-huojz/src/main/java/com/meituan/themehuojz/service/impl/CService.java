package com.meituan.themehuojz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meituan.themehuojz.mapper.HotelLinkmanBindMapper;
import com.meituan.themehuojz.mapper.LinkmanMapper;
import com.meituan.themehuojz.po.HotelLinkmanBind;
import com.meituan.themehuojz.po.Linkman;
import com.meituan.themehuojz.handler.NumberHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: CService
 * Package: com.meituan.themehuojz.service.impl
 * Description: CService
 *
 * @Author huojz
 * @Create 2024 01 20 15:16
 * @Version 1.0
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class CService extends ServiceImpl<HotelLinkmanBindMapper, HotelLinkmanBind> implements Runnable {
    @Autowired
    LinkmanMapper linkmanMapper;
    @Autowired
    AService aService;
    @Autowired
    BService bService;
    private NumberHandler numberHandler;

    public CService(NumberHandler numberHandler) {
        this.numberHandler = numberHandler;
    }


    /**
     * 题目2 方法3 根据酒店id集合，返回酒店下联系人信息集合
     * @param poiIds
     * @return
     */
    public List<Linkman> batchGetContactBypoiIds(List<Long> poiIds) {
        //返回数据
        List<Linkman> linkmen = new ArrayList<>();
        //联系人id
        List<Long> linkmanIds = new ArrayList<>();
        //根据id酒店id查询关联表
        LambdaQueryWrapper<HotelLinkmanBind> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(CollectionUtil.isNotEmpty(poiIds), HotelLinkmanBind::getPoiId, poiIds);
        List<HotelLinkmanBind> hotelLinkmanBinds = this.list(wrapper);
        //遍历关联表数据根据酒店id分组，每组取一个联系人id;
        Map<Long, List<HotelLinkmanBind>> poidLinkmanMap = hotelLinkmanBinds.stream().collect(Collectors.groupingBy(HotelLinkmanBind::getPoiId));
        for (Map.Entry<Long, List<HotelLinkmanBind>> entry : poidLinkmanMap.entrySet()) {
            HotelLinkmanBind hotelLinkmanBind = entry.getValue().get(0);
            if (BeanUtil.isNotEmpty(hotelLinkmanBind)) {
                linkmanIds.add(hotelLinkmanBind.getLinkmanId());
            }
        }
        //根据联系人id查表获取联系人信息
        if (CollUtil.isNotEmpty(linkmanIds)) {
            linkmen = linkmanMapper.selectBatchIds(linkmanIds);
        }

        return linkmen;
    }

    /**
     * 题目3：线程执行的方法生成数据的方法
     */
    @Override
    public void run() {
        numberHandler.addValue();
    }
}
