package com.shopping.common.exception;

import com.shopping.common.result.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 12:35
 * @description: shopping-parent
 *  自定义异常类
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class shoppingException extends RuntimeException{
    //异常状态码
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public shoppingException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public shoppingException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
