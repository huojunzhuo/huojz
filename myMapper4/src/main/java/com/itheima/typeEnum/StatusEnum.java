package com.itheima.typeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: StatusEnum
 *
 * @Description 状态枚举类
 * @Author huojz
 * @project huojz
 * @create 2024 04 05 17:00
 */

public enum StatusEnum {
    NOT_DELETE(0),
    IS_DELETE(1);
    public Integer code;

    StatusEnum(Integer code) {
        this.code = code;
    }

    StatusEnum() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
