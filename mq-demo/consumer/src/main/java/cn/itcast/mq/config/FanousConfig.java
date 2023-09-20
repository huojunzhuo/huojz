package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: FanousConfig
 * Package: cn.itcast.mq.config
 * Description:
 *
 * @Author huojz
 * @Create 2023/9/19 21:09
 * @Version 1.0
 */
@Configuration
public class FanousConfig {
    //FanoutExchange交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("itcast.fanout");
    }
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanoutQueue1");
    }
    @Bean
    public Binding fanoutBinding1(Queue fanoutQueue1,FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(fanoutQueue1)
                .to(fanoutExchange);
    }
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanoutQueue2");
    }

    @Bean
    public Binding fanoutBinding2(Queue fanoutQueue2,FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(fanoutQueue2)
                .to(fanoutExchange);
    }
    //创建队列：object.queue
    @Bean
    public Queue objectQueue(){
        return new Queue("object.queue");
    }
    //对象消息转换器bean
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }


}
