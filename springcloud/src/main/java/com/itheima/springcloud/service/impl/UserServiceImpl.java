package com.itheima.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.springcloud.domain.User;
import com.itheima.springcloud.mapper.UserMapper;
import com.itheima.springcloud.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author HJZ
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-09-24 17:15:30
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




