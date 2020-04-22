package com.example.demo.mq;

public class MQIoFoDto {

    private String name;
    private Integer type;  //1,爱基金基金数据采集
    private String content;

    public MQIoFoDto(){

    }


    public MQIoFoDto(String name, Integer type, String content) {
        this.name = name;
        this.type = type;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
