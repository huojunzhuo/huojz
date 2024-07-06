package com.itheima;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.itheima.entities.Pay;
import com.itheima.mapper.PayMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.typeEnum.StatusEnum;
import com.itheima.mapper.UserMapper;
import lombok.Data;
import org.apache.ibatis.session.RowBounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    //测试insert插入方法
    @Test
    public void testInsert3() {
        Pay pay3 = new Pay();
        pay3.setDeleted(0);
        pay3.setPayNo("paynum3");
        pay3.setOrderNo("orderNum3");
        pay3.setAmount(BigDecimal.valueOf(9.902));
        pay3.setUserId(123);
        pay3.setCreateTime(DateTime.now());
        pay3.setUpdateTime(DateTime.now());
        Pay pay4 = new Pay();
        pay4.setDeleted(0);
        pay4.setPayNo("paynum4");
        pay4.setOrderNo("orderNum4");
        pay4.setAmount(BigDecimal.valueOf(9.902));
        pay4.setUserId(456);
        pay4.setCreateTime(DateTime.now());
        pay4.setUpdateTime(DateTime.now());
//        int insert = payMapper.insert(pay3);
//        Assertions.assertEquals(1,insert);
//        int insert = payMapper.insertUseGeneratedKeys(pay3);
        payMapper.insertList(List.of(pay3,pay4));
        System.out.println("pay3.getId() = " + pay3.getId());
    }
    //根据主键全量更新数据
    @Test
    public void testUpdate(){
        Pay pay = new Pay();
        pay.setId(18);
        pay.setDeleted(0);
        pay.setPayNo("19");
        pay.setOrderNo("19");
        pay.setAmount(BigDecimal.valueOf(9.902));
        pay.setUpdateTime(DateTime.now());
        pay.setCreateTime(DateTime.now());
        payMapper.updateByPrimaryKey(pay);
    }
    //根据主键可选更新已经配置的属性
    @Test
    public void testUpdate2(){
        Pay pay = new Pay();
        pay.setId(18);
        pay.setOrderNo("haha");
        pay.setUpdateTime(DateTime.now());
        payMapper.updateByPrimaryKeySelective(pay);
    }
    //根据主键删除（逻辑删除）
    @Test
    public void testDelete(){
        int i = payMapper.deleteByPrimaryKey(18);
        Assertions.assertEquals(1,i);
    }

    //1.查询所有
    @Test
    public void testSelectAll(){
        List<Pay> pays = payMapper.selecAll();
        logger.info("info信息");
        logger.error("error信息");
        logger.debug("debug信息");
        pays.forEach(System.out::println);
    }
    //2.根据主键查询
    @Test
    public void testSelectByPrimaryKey(){
        Pay pay = payMapper.selectByPrimaryKey(17);
        System.out.println("pay = " + pay);
    }
    //3.根据Example条件查询
    @Test
    public void testExample() {
        Example example = new Example(Pay.class);
        List<Integer> ids = List.of(1, 6);
        example.createCriteria().andIn("id", ids).orIsNotNull("userId")
        ;
        List<Pay> pays = payMapper.selectByExample(example);
        System.out.println("pays = " + pays);
        pays.forEach(System.out::println);
    }


    //4.根据Example条件查询(lambd表达式写法)
    @Test
    public void testWeekend() {
        List<Integer> ids = List.of(1, 6);
        Example example = Example.builder(Pay.class)
//                .select("payNo") //如果查询某个参数则增加这个条件
                .where(WeekendSqls.<Pay>custom().andIn(Pay::getId, ids).andLike(Pay::getPayNo, "%num%"))
                .build();
        List<Pay> pays = payMapper.selectByExample(example);
        System.out.println("pays = " + pays);
        pays.forEach(System.out::println);
    }
    @Test
    public void testWeekend2(){
        Weekend<Pay> wk = Weekend.of(Pay.class);
        wk.weekendCriteria().andIn(Pay::getId, Arrays.asList(1,2));
        Weekend<Pay> wk2 = Weekend.of(Pay.class);
        WeekendCriteria<Pay, Object> wkca = wk2.weekendCriteria();
        wkca.orIsNull(Pay::getRelId).andGreaterThan(Pay::getAmount,9.9);
        wk.and(wkca);
        List<Pay> pays = payMapper.selectByExample(wk);
        pays.forEach(System.out::println);
    }
    //5.分页查询
    @Test
    public void testLimit(){
        int pageNo = 2;
        int pageSize = 5;
        int start = (pageNo-1) * pageSize;
        RowBounds rowBounds = new RowBounds(start,pageSize);
        Pay pay = new Pay();
        pay.setUserId(1);
        //分页查询列表
        List<Pay> pays = payMapper.selectByRowBounds(pay, rowBounds);
        pays.forEach(System.out::println);
        //查询总记录数
        System.out.println(payMapper.selectCount(pay));
    }
    @Test
    public void testLog(){
        logger.info("info信息");
        logger.error("error信息");
        logger.debug("debug信息");
    }

    @Autowired
    UserMapper userMapper;
    @Test
    public void teststream(){
        List<Pay> pays = payMapper.selecAll();
        pays.forEach(System.out::println);
        System.out.println("________________________________________________________");
        List<Pay> collect = pays.stream().filter(x -> BeanUtil.isEmpty(x.getRelId()) || 0 == x.getRelId()  ).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
