package com.itheima.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: PrintAnyGlobalFilter
 *
 * @Description 自定义全局过滤器
 * @Author huojz
 * @project huojz
 * @create 2023 09 28 11:34
 */
@Component
public class PrintAnyGlobalFilter implements GlobalFilter, Ordered {
    //全局过滤器不需要在yml配置文件配置就可直接执行
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 编写过滤器逻辑
        System.out.println("全局过滤器执行了");
        //放行
        return chain.filter(exchange);
    }
    @Override
    public int getOrder() {
        return 0;
    }
}
