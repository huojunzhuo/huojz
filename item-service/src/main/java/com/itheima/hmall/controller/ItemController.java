package com.itheima.hmall.controller;




import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmall.common.domain.PageDTO;
import com.hmall.common.domain.PageQuery;
import com.hmall.common.utils.BeanUtils;
import com.itheima.hmall.domain.dto.ItemDTO;
import com.itheima.hmall.domain.po.Item;
import com.itheima.hmall.service.IItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品管理相关接口")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class ItemController {

    @Autowired
    private  IItemService itemService;

    @GetMapping("/items")
    public List<Item> getAll(){
        return itemService.getAll();
    }

    @GetMapping("cars")
    public String test(){
        return "hello";
    }


}
