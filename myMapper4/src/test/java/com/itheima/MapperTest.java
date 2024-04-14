package com.itheima;

import cn.hutool.core.date.DateTime;
import com.itheima.entities.Pay;
import com.itheima.mapper.PayMapper;
import com.itheima.typeEnum.StatusEnum;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Data
public class MapperTest {
    Logger logger = LogManager.getLogger(MapperTest.class);
    
    @Autowired
    PayMapper payMapper;

    @Test
    public void testM1() {
        List<Pay> pays = payMapper.selectAll();
        System.out.println("pays = " + pays);
        Pay pay = pays.get(0);
        pay.setPayNo("pay17203600");
        pay.setOrderNo("6544bafb424c");
        pay.setId(null);
        int insert = payMapper.insert(pay);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testSelect() {
        Pay pay1 = new Pay();
        pay1.setId(2);
        Pay pay = payMapper.selectByPrimaryKey(2);
        System.out.println("pay = " + pay);
        Pay pay2 = payMapper.selectByPrimaryKey(pay1);
        System.out.println("pay2 = " + pay2);
//        List<Pay> pay2s = payMapper.select(pay1);
//        System.out.println("pay2s = " + pay2s);
//        int i = payMapper.selectCount(pay1);
//        System.out.println("i = " + i);
//        Pay selectOne = payMapper.selectOne(pay1);
//        System.out.println("selectOne = " + selectOne);
    }

    @Test
    public void testInsert3() {
        Pay pay3 = new Pay();
        pay3.setDeleted(1);
        pay3.setPayNo("paynum1");
        pay3.setOrderNo("orderNum1");
        pay3.setAmount(BigDecimal.valueOf(9.902));
        pay3.setCreateTime(DateTime.now());
        pay3.setUpdateTime(DateTime.now());
        int insert = payMapper.insert(pay3);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testExample() {
        Example example = new Example(Pay.class);
        List<Integer> ids = List.of(1, 6);
        example.createCriteria().andIn("id", ids).andLike("payNo", "%num%");
        List<Pay> pays = payMapper.selectByExample(example);
        System.out.println("pays = " + pays);
        pays.forEach(System.out::println);
    }

    @Test
    public void testWeekend() {
        List<Integer> ids = List.of(1, 6);
        Example example = Example.builder(Pay.class)
//                .select("payNo")
                .where(WeekendSqls.<Pay>custom().andIn(Pay::getId, ids).andLike(Pay::getPayNo, "%num%"))
                .build();
        List<Pay> pays = payMapper.selectByExample(example);
        System.out.println("pays = " + pays);
        pays.forEach(System.out::println);
    }

    @Test
    public void testMapper4AndMybatisPlus(){
        List<Pay> pays = payMapper.selectBatchIds(List.of(1, 2));
        pays.forEach(System.out::println);
        Pay pay = payMapper.selectById(1);
        System.out.println("pay = " + pay);
    }

    @Test
    public void testSelectAll(){
        List<Pay> pays = payMapper.selecAll();
        logger.info("info信息");
        logger.error("error信息");
        logger.debug("debug信息");
        pays.forEach(System.out::println);
    }
    @Test
    public void testLog(){
        logger.info("info信息");
        logger.error("error信息");
        logger.debug("debug信息");
    }
}
