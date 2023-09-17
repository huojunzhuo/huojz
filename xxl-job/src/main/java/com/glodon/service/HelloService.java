package com.glodon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * ClassName: HelloService
 *
 * @Description HelloService
 * @Author huojz
 * @project huojz
 * @create 2023 09 16 19:52
 */
@Service
public class HelloService {
    public void testGlue(){
        System.out.println("hello,Glue"+ LocalDateTime.now());
    }
}
