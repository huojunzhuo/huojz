package com.itheima.config;


import com.itheima.interceptor.UserInfoInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: FeignLogLevelConfig
 *
 * @Description FeignLogLevelConfig
 * @Author huojz
 * @project huojz
 * @create 2023 09 28 19:35
 */
@Configuration
public class FeignLogLevelConfig {
    @Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
    @Bean
    public RequestInterceptor userInfoInterceptor(){
        return new UserInfoInterceptor();
    }
}
