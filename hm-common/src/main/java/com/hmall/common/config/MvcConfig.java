package com.hmall.common.config;

import com.hmall.common.intercepter.UserInfoIntercepter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: MvcConfig
 *
 * @Description MvcConfig
 * @Author huojz
 * @project huojz
 * @create 2023 09 28 19:00
 */
@Configuration
@ConditionalOnClass(DispatcherServlet.class)//仅在DispatcherServlet情况下生效（网关引用也无效）
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有，不需要增加路径
        registry.addInterceptor(new UserInfoIntercepter());
    }
}
