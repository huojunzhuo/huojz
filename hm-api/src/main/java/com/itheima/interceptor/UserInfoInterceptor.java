package com.itheima.interceptor;

import com.hmall.common.utils.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * ClassName: UserInfoInterceptor
 *
 * @Description feign用户信息拦截器
 * @Author huojz
 * @project huojz
 * @create 2023 09 28 19:30
 */
public class UserInfoInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Long userId = UserContext.getUser();
        if(userId != null){
            String userInfo = userId.toString();
            requestTemplate.header("user-info", userInfo);
        }

    }
}
