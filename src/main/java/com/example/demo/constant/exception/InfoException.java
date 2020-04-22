/*
 * Copyright 2011-2015 10jqka.com.cn All right reserved. This software is the confidential and proprietary information
 * of 10jqka.com.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into with 10jqka.com.cn.
 */
package com.example.demo.constant.exception;

/**
 * 类TrcException.java的实现描述：trc专用异常类
 * 
 * @author Huangxiquan 2018年1月6日 下午4:32:55
 */
public class InfoException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -5689133993286338253L;

    public InfoException(){
    }

    public InfoException(String s){
        super(s);
    }

    public InfoException(String s, Throwable throwable){
        super(s, throwable);
    }

    public InfoException(Throwable throwable){
        super(throwable);
    }

    protected InfoException(String s, Throwable throwable, boolean flag, boolean flag1){
        super(s, throwable, flag, flag1);
    }
}
