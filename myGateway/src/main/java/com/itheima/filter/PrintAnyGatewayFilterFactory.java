package com.itheima.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * ClassName: PrintAnyGatewayFilterFactory
 *
 * @Description 自定义无参GatewayFilter
 * @Author huojz
 * @project huojz
 * @create 2023 09 28 10:47
 */
@Component
public class PrintAnyGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println("GatewayFilter过滤器执行了");
//                ApplicationContext applicationContext = exchange.getApplicationContext();
//                System.out.println("applicationContext = " + applicationContext);
//                System.out.println("exchange.getSession() = " + exchange.getSession());
//                ServerHttpRequest request = exchange.getRequest();
//                System.out.println("request.getPath() = " + request.getPath());
//                System.out.println("request.getQueryParams() = " + request.getQueryParams());
                return chain.filter(exchange);
            }
        };
    }
}
