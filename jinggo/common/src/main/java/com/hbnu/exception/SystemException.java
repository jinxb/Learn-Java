package com.hbnu.exception;

import com.hbnu.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   // 全局异常通知，监控Controller
@Slf4j
public class SystemException {

    @ExceptionHandler(RuntimeException.class)
    public SysResult exception(Throwable throwable) {
        throwable.printStackTrace();
        log.info(throwable.getMessage());
        return SysResult.fail("服务器调用异常！！！");
    }
}
