package com.meituan.themehuojz.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Linkman
 * Package: com.meituan.themehuojz.po
 * Description: 联系人表
 *
 * @Author huojz
 * @Create 2024/1/22 10:49
 * @Version 1.0
 */

/**
 * 联系人表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Linkman {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;
}
