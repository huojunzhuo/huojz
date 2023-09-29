package com.itheima.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String testFeign(@RequestParam("str") String str) ;

}
