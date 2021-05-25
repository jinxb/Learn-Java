package com.hbnu.exception;

import com.hbnu.vo.SysResult;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//全局异常通知，监控controller
@Slf4j
public class SystemException {

    //运行时生效
    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
    public SysResult exception(Throwable throwable){
        throwable.printStackTrace();
        log.info(throwable.getMessage());
        return SysResult.fail("服务器调用异常！");
    }
}
