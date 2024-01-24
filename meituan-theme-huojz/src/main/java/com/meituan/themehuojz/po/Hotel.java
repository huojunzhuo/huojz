package com.meituan.themehuojz.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: Hotel
 * Package: com.meituan.themehuojz.po
 * Description: TODO
 *
 * @Author huojz
 * @Create 2024/1/22 10:02
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

/**
 * 酒店
 */
public class Hotel {
    /**
     * 酒店id
     */
    private Long Id;
    /**
     * 酒店名称
     */
    private String name;
    /**
     * 酒店联系人列表
     */
    private List<Linkman> LinkmanList;

}
