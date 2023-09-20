package cn.itcast.mq.spring;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SpringAmqpTest
 * Package: cn.itcast.mq.spring
 * Description:
 *
 * @Author huojz
 * @Create 2023/9/18 21:07
 * @Version 1.0
 */
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testRabbitTemplete() {
        String queueName = "simple.queue";
        String message = "hello spring amqp2!";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testSendMessageWorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello spring amqp__!";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }
    //发送fanout消息
    @Test
    public void testSendFanousExchange() throws InterruptedException {
        String exchangeName = "itcast.fanout";
        String message = "hello everyOne!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);

    }
    //发送direct消息
    @Test
    public void testSendDirectExchange() throws InterruptedException {
        String exchangeName = "itcast.direct";
        String message = "hello red!";
        rabbitTemplate.convertAndSend(exchangeName, "red", message);

    }
    //发送topic消息
    @Test
    public void testSendTopicExchange() throws InterruptedException {
        String exchangeName = "itcast.topic";
        String message = "English.news!11";
        rabbitTemplate.convertAndSend(exchangeName, "English.news1", message);

    }
    //发送Object消息
    @Test
    public void testSendObjectQueue() {
        Map<String,Object> msg = new HashMap<>();
        msg.put("name","柳岩");
        msg.put("age","24");
        String queueName = "object.queue";
        rabbitTemplate.convertAndSend(queueName, msg);

    }


}
