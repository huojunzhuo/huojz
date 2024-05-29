package com.itheima.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * ClassName: GeneralDAO
 * @Description 通用DAO，继承tk-mapper
 * @Author huojz
 * @project huojz
 * @create 2024 05 26 15:38
 */
public interface GeneralDAO<T> extends Mapper<T>, MySqlMapper<T> {
}
