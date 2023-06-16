package com.shopping.common.exception;

import com.shopping.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @author: Wang Xiaoyi
 * @date: 2023-06-16 12:35
 * @description: shopping-parent
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null);
    }

    //自定义异常处理
    @ExceptionHandler(shoppingException.class)
    public Result error(shoppingException exception){
        return Result.fail(null);
    }

}
