/*
 * Copyright 2011-2015 10jqka.com.cn All right reserved. This software is the confidential and proprietary information
 * of 10jqka.com.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into with 10jqka.com.cn.
 */
package com.example.demo.entity;

import java.io.Serializable;

/**
 * @Company:浙江核新同花顺网络信息股份有限公司
 * @ClassName: TemplateDataDto
 * @Description: 静态化模板数据对象
 * @Author: wulongcan@myhexin.com
 * @CreateDate: 2015-3-26 下午4:47:21
 * @version:1.0
 */
public class TemplateDataDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T                 variable;             // 模板变量

    private Object            data;                 // 模板数据

    public TemplateDataDto() {
    }

    public TemplateDataDto(T variable, Object data) {

        this.variable = variable;
        this.data = data;
    }

    /**
     * @return the variable
     */
    public T getVariable() {
        return variable;
    }

    /**
     * @param variable the variable to set
     */
    public void setVariable(T variable) {
        this.variable = variable;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

}
