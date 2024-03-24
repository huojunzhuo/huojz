package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

/**
 * ClassName: SpringRabbitListener
 * Package: cn.itcast.mq.listener
 * Description:
 *
 * @Author huojz
 * @Create 2023/9/19 20:35
 * @Version 1.0
 */
@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg) {
        System.out.println("消费者监听到消息msg为：【" + msg + "】");
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue(String msg) throws InterruptedException {
        System.out.println("消费者1监听到消息为：【" + msg + "】"+ LocalTime.now());
        Thread.sleep(20);
    }
    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2监听到消息为.......【" + msg + "】"+LocalTime.now());
        Thread.sleep(200);
    }

    //fanout模式************************************************************************
    //声明队列1
    @RabbitListener(queues = "fanoutQueue1")
    public void listenFanoutQueue1(String msg) throws InterruptedException {
        System.out.println("消费者监听到fanoutQueue1消息为【" + msg + "】"+LocalTime.now());

    }
    //声明队列1
    @RabbitListener(queues = "fanoutQueue2")
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.err.println("消费者监听到fanoutQueue2消息为【" + msg + "】"+LocalTime.now());
    }

    //direct模式*************************************************************************
    //声明队列1
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "itcast.direct",type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void listenDirectQueue1(String msg){
        System.out.println("消费者监听到direct.queue1消息为【" + msg + "】"+LocalTime.now());
    }
    //声明队列2
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void listenDirectQueue2(String msg){
        System.out.println("消费者监听到direct.queue2消息为【" + msg + "】"+LocalTime.now());
    }
    //topic模式*************************************************************************
    //声明队列1
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic",type = ExchangeTypes.TOPIC),
            key = {"China.#"}
    ))
    public void listenTopicQueue1(String msg){
        System.out.println("消费者监听到topic.queue1消息为【" + msg + "】"+LocalTime.now());
    }
    //声明队列2
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "itcast.topic",type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    ))
    public void listenTopicQueue2(String msg){
        System.out.println("消费者监听到topic.queue2消息为【" + msg + "】"+LocalTime.now());
    }

    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String,Object> msg){
        System.out.println("接收到Object消息："+msg);
        System.out.println(msg.get("name"));
        System.out.println(msg.get("age"));
    }
}
