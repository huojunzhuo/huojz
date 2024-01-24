package com.meituan.themehuojz.handler;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: NumberHandler
 * Package: com.meituan.themehuojz.runable
 * Description: 多线程数据处理类
 *
 * @Author huojz
 * @Create 2024/1/21 12:11
 * @Version 1.0
 */

/**
 * 数据处理类
 */
@Data
public class NumberHandler {

    /**
     * 计数器
     */
    private int counter = 0;

    /**
     * 待求和数据的容器
     */
    private List<Integer> values = new ArrayList<>();

    /**
     * 求和值
     */
    private Integer sum = 0;

    /**
     * 制造数据方法
     */
    public synchronized void addValue() {
        String name = Thread.currentThread().getName();
        System.out.println("线程："+name+ "开始执行生成数据");
        double floor = Math.floor(Math.random() * 100 + 1);
        int intValue = Double.valueOf(floor).intValue();
        counter++;
        values.add(intValue);
        System.out.println("counter = " + counter);
        System.out.println("values = " + values);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 求和方法
     */
    public synchronized void sumValue() {
        String name = Thread.currentThread().getName();
        System.out.println("线程："+name+ "开始进行求和");
        sum = values.stream().reduce(Integer::sum).orElse(0);
        System.out.println("求和sum = " + sum);
    }

}



