package com.itheima.feign;

import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName: mybatisFeign
 * Package: com.itheima.feign
 * Description:
 *
 * @Author huojz
 * @Create 2023/9/25 19:11
 * @Version 1.0
 */
@FeignClient("mp-service")
public interface mybatisFeign {

    @GetMapping("/test")
    public String testFeign(@Param("str") String str) ;

}
