package com.example.demo.entity;

import java.io.Serializable;

public class ReturnDto implements Serializable {

    private static final long serialVersionUID = 8319429123918346364L;

    private boolean success;
    private String msg;
    private Object data;

    public ReturnDto(boolean success) {
        new ReturnDto(success, "", null);
    }

    public ReturnDto(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ReturnDto(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
