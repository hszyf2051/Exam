package com.yif.handler.exception;

import com.yif.exception.SystemException;
import com.yif.vo.params.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yif
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public Result systemExceptionHandler(SystemException e) {
        // 打印异常信息
        log.error("出现了异常！ {}", e);
        // 从异常对象中获取提示信息封装返回
        return Result.fail(e.getCode(), e.getMsg());
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public Result badCredentialsException(BadCredentialsException e) {
//        // 打印异常信息
//        log.error("出现了异常！ {}", e);
//        // 从异常对象中获取提示信息封装返回
//        return Result.fail(ResultEnum.LOGIN_ERROR.getCode(), ResultEnum.LOGIN_ERROR.getMsg());
//    }

//    @ExceptionHandler(Exception.class)
//    public Result exceptionHandler(Exception e) {
//        //打印异常信息
//        log.error("出现了异常！ {}", e);
//        //从异常对象中获取提示信息封装返回
//        return Result.fail(ResultEnum.SYSTEM_ERROR.getCode(), e.getMessage());
//    }
}
