package com.itheima;

import com.itheima.mydemo.entities.Pay;
import com.itheima.mydemo.mapper.PayMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyMyBatisGeneratorApplicationTests {

    @Autowired
    PayMapper payMapper;
    @Test
    void contextLoads() {
        List<Pay> pays = payMapper.selectAll();
        System.out.println("pays = " + pays);
    }

}
