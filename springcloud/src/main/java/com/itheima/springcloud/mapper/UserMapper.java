package com.itheima.springcloud.mapper;

import com.itheima.springcloud.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author HJZ
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-09-24 17:15:30
* @Entity springcloud.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




