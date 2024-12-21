package com.itheima.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: Employee
 *
 * @Description TODO
 * @Author huojz
 * @project huojz
 * @create 2024 12 21 17:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @ExcelProperty("编号")
    private  Long id;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("日期")
    private Date date;

    @ExcelProperty("工资")
    private double salary;
}
