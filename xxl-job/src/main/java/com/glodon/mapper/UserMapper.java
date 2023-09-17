package com.glodon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glodon.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: UserMapper
 *
 * @Description TODO
 * @Author huojz
 * @project huojz
 * @create 2023 09 17 10:37
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where mod(id,#{total})=#{index}")
    public List<User> selectList(@Param("total") Integer total, @Param("index") Integer index);

}
