package com.itheima.write;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.itheima.pojo.Employee;
import com.itheima.util.TestFileUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: SimpleWrite
 *
 * @Description TODO
 * @Author huojz
 * @project huojz
 * @create 2024 12 21 17:25
 */
public class SimpleWriter {

    private static List<Employee> data(Integer num){
        ArrayList<Employee> data = ListUtils.newArrayList();
        for (Long i = 1L; i <= num; i++) {
            Employee employee = new Employee(i,"员工"+i,new Date(),66.6*i);
            data.add(employee);
        }
        return data;
    }
    @Test
    public void write() throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String dateString = dateformat.format(System.currentTimeMillis());
        String path = TestFileUtil.getPath();
        String fileName = path + "simpleWrite" + dateString + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, Employee.class).sheet("模板66").doWrite(data(5));
    }




}
