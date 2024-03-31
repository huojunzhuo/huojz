package com.itheima.mydemo.service;

import com.itheima.mydemo.entities.Pay;

import java.util.List;

/**
 * ClassName: PayService
 *
 * @Description service接口
 * @Author huojz
 * @project huojz
 * @create 2024 03 24 19:58
 */
public interface PayService {
    public int add(Pay pay);

    public int delete(Integer id);

    public int update(Pay pay);

    public Pay getById(Integer id);

    public List<Pay> getAll();
}
