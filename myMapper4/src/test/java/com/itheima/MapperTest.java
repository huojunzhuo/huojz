package com.itheima;

import com.itheima.entities.Pay;
import com.itheima.mapper.PayMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    PayMapper payMapper;
    @Test
    public void testM1(){
        List<Pay> pays = payMapper.selectAll();
        System.out.println("pays = " + pays);
    }
}
