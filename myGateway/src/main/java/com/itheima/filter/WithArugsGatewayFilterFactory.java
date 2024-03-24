package com.itheima.filter;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * ClassName: WithArugsGatewayFilterFactory
 *
 * @Description 自定义带参数过滤器
 * @Author huojz
 * @project huojz
 * @create 2023 09 28 11:15
 */
@Component
public class WithArugsGatewayFilterFactory extends AbstractGatewayFilterFactory<WithArugsGatewayFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        // OrderedGatewayFilter是GatewayFilter的子类，其构造器包含两个参数：
        // - GatewayFilter：过滤器
        // - int order值：值越小，过滤器执行优先级越高
        return new OrderedGatewayFilter(new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //编写过滤器逻辑
                System.out.println("config.getA() = " + config.getA());
                System.out.println("config.getB() = " + config.getB());
                System.out.println("config.getC() = " + config.getC());
                System.out.println("WithArugsGatewayFilter过滤器执行了");
                //放行
                return chain.filter(exchange);
            }
        },1);
    }

    // 自定义配置属性，成员变量名称很重要，下面会用到
    @Data
    static class Config {
        private String a;
        private String b;
        private String c;

    }

    // 将变量名称依次返回，顺序很重要，将来读取参数时需要按顺序获取
    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("a", "b", "c");
    }

    // 返回当前配置类的类型，也就是内部的Config
    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

}
