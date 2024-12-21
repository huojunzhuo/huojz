package com.itheima.read;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson2.JSON;
import com.itheima.pojo.Employee;
import com.itheima.util.TestFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;

/**
 * ClassName: SimpleRead
 *
 * @Description TODO
 * @Author huojz
 * @project huojz
 * @create 2024 12 21 19:05
 */
@Slf4j
public class SimpleReader {

    @Test
    public void read(){
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = TestFileUtil.getPath() + "simpleWrite2024-12-21.xlsx";
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(fileName, Employee.class, new PageReadListener<Employee>(dataList -> {
            for (Employee employee : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(employee));
            }
        })).sheet().doRead();
    }

}
