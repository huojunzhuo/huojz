package com.meituan.themehuojz.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: HotelLinkmanBind
 * Package: com.meituan.themehuojz.po
 * Description: 酒店-供应商关联表
 *
 * @Author huojz
 * @Create 2024/1/22 10:11
 * @Version 1.0
 */

/**
 * 酒店-供应商关联表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelSupplierBind {
    /**
     * 关联id
     */
   private Long id;
    /**
     * 酒店id
     */
   private Long poiId;
    /**
     * 供应商id
     */
   private Long supplierId;
}
