package com.itheima.mydemo;

import com.itheima.mydemo.entities.Pay;
import com.itheima.mydemo.mapper.PayMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * ClassName: CrudTest
 *
 * @Description crud
 * @Author huojz
 * @project huojz
 * @create 2024 03 31 18:39
 */
@SpringBootTest
public class CrudTest {
    @Autowired
    PayMapper payMapper;
    @Test
    public void testMapper(){
        List<Pay> pays = payMapper.selectAll();
        System.out.println("pays = " + pays);
    }
}
