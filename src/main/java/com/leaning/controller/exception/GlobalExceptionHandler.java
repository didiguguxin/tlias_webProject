package com.leaning.controller.exception;

import com.leaning.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        log.error("异常：{}", e.getMessage());
        //业务失败返回code=0，msg是提示文本
        return Result.error(e.getMessage());
    }

    //捕获全部其他异常兜底
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常", e);
        return Result.error("系统出错，请联系管理员");
    }
}