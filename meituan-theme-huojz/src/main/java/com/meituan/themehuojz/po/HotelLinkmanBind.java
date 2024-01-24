package com.meituan.themehuojz.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: HotelLinkmanBind
 * Package: com.meituan.themehuojz.po
 * Description: HotelLinkmanBind
 *
 * @Author huojz
 * @Create 2024/1/22 11:04
 * @Version 1.0
 */

/**
 * 酒店-联系人关联数据表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelLinkmanBind {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 酒店id
     */
    private Long poiId;
    /**
     * 联系人id
     */
    private Long LinkmanId;
}
