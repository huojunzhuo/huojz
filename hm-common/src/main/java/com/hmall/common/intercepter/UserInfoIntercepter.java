package com.hmall.common.intercepter;

import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: UserInfoIntercepter
 *
 * @Description 用户登录Spring拦截器，被微服务引用
 * @Author huojz
 * @project huojz
 * @create 2023 09 28 18:50
 */
@Component
public class UserInfoIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1.获取请求头中的用户
        String userInfo = request.getHeader("user-info");
        long userInfoLong = Long.parseLong(userInfo);
        // 2.判断是否为空
        if(StrUtil.isNotBlank(userInfo)){
            //存入线程信息共享
            UserContext.setUser(Long.valueOf(userInfo));
        }
        // 3.放行
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除用户
        UserContext.removeUser();
    }
}
