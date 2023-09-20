package cn.itcast.mq.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: PublisherConfg
 * Package: cn.itcast.mq.config
 * Description:
 *
 * @Author huojz
 * @Create 2023/9/20 21:12
 * @Version 1.0
 */
@Configuration
public class PublisherConfg {
    //对象消息转换器序列化json的bean
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
