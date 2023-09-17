package com.glodon;

import com.glodon.service.HelloService;
import com.xxl.job.core.handler.IJobHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class JobHandler extends IJobHandler {
    @Autowired
    private HelloService helloService;

    @Override
    public void execute() throws Exception {
        helloService.testGlue();
    }
}
