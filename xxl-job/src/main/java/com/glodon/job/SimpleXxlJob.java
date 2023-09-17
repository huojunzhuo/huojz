package com.glodon.job;

import com.glodon.dao.User;
import com.glodon.mapper.UserMapper;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: SimpleXxlJob
 *
 * @Description TODO
 * @Author huojz
 * @project huojz
 * @create 2023 09 16 19:24
 */
@Component
public class SimpleXxlJob {

    @Autowired
    UserMapper userMapper;

    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception{

        System.out.println("bean模式执行定时任务，执行时间："+ LocalDateTime.now());
    }

    @XxlJob("sendMsHandler")
    public void sendMsHadnler(){
        System.out.println("任务开始时间"+ LocalDateTime.now());
        int index = XxlJobHelper.getShardIndex();
        int total = XxlJobHelper.getShardTotal();
        System.out.println("分片索引："+index+"分片总数："+total);
        List<User> users = null;
        if(total == 1){
            users = userMapper.selectList(null);
        }else {
            users = userMapper.selectList(total, index);
        }
        if(users == null){
            return;
        }
        int size = users.size();
        System.out.println("处理任务数量+"+size);
        long startTime = System.currentTimeMillis();
        users.forEach(item->{
            System.out.println("item = " + item);
            try {
                //模拟发短信的通知动作
                TimeUnit.MILLISECONDS.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        long finishTime = System.currentTimeMillis();
        System.out.println("任务结束时间："+LocalDateTime.now());
        System.out.println("任务耗时："+(finishTime-startTime)+"毫秒");
        System.out.println("******************************************************");


    }
}
