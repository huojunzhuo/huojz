package com.itheima.hmall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmall.common.exception.BizIllegalException;
import com.hmall.common.utils.BeanUtils;
import com.itheima.hmall.domain.dto.ItemDTO;
import com.itheima.hmall.domain.po.Item;
import com.itheima.hmall.mapper.ItemMapper;
import com.itheima.hmall.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 虎哥
 */
@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    public ItemMapper itemMapper;


    @Override
    public List<Item> getAll() {
        return itemMapper.selectList(null);
    }
}
