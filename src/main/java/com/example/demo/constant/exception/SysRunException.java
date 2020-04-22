package com.example.demo.constant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 自定义异常
 * @Author: shenchengxiao
 * @CreateDate 2018/1/9 13:32
 */
public class SysRunException extends RuntimeException{

    private static final long serialVersionUID = -18169275144094491L;

    private static final Logger logger = LoggerFactory.getLogger(SysRunException.class);

    public SysRunException(String message){
        super(message);
        logger.warn(message);
    }

    public SysRunException(String message,Throwable cause){
        super(message,cause);
    }
}
