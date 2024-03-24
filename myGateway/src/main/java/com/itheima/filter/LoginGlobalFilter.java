package com.itheima.filter;

import cn.hutool.core.util.StrUtil;
import com.hmall.common.exception.UnauthorizedException;
import com.itheima.config.AuthProperties;
import com.itheima.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * ClassName: LoginGlobalFilter
 *
 * @Description 全局登录拦截器(网关拦截)
 * @Author huojz
 * @project huojz
 * @create 2023 09 28 17:09
 */
@RequiredArgsConstructor
@Component
//@EnableConfigurationProperties(AuthProperties.class)
public class LoginGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private AuthProperties authProperties;
    //spring提供的匹配路径和请求方法的工具
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private JwtTool jwtTool;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取request
        ServerHttpRequest request = exchange.getRequest();
        //2.判断当前请求是否需要拦截
        if (isAllowPath(request)) {
            //无需拦截 放行
            return chain.filter(exchange);
        }
        ServerHttpResponse response = exchange.getResponse();
        //3.获取token
        String authorization = null;
        List<String> authorizations = request.getHeaders().get("authorization");
        if (authorizations == null || authorizations.isEmpty()) {
            response.setRawStatusCode(401);
            return response.setComplete();
        }
        authorization = authorizations.get(0);
        //4.需要拦截，解析token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(authorization);
            System.out.println("userId = " + userId);
        } catch (Exception e) {
            e.printStackTrace();
            response.setRawStatusCode(401);
            response.setComplete();
        }
        String userinfo = userId.toString();
        //5.传递信息到下游服务(调用ServerWebExchange的方法修改请求)
        ServerWebExchange exc = exchange.mutate().request(builder -> builder.header("user-info", userinfo)).build();
        //6.放行
        return chain.filter(exchange);
    }

    private boolean isAllowPath(ServerHttpRequest request) {
        //获取当前路径
        String path = request.getPath().toString();
        for (String excludePath : authProperties.getExcludePaths()) {
            if (pathMatcher.match(excludePath, path)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public int getOrder() {
        return 0;
    }
}
