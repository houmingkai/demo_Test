package com.example.demo.mq;

import com.alibaba.fastjson.JSON;
import com.example.demo.util.JsonUtil;

public class JsonTest {
    
    public static void main(String[] args){
        MQIoFoDto mqIoFoDto = new MQIoFoDto("kettle采集",1,"基金经理");
        String jsonString = JsonUtil.toJsonString(mqIoFoDto);
//        String jsonString = JSON.toJSONString(mqIoFoDto);

        System.out.println(jsonString);

        MQIoFoDto mqIoFoDto1 = JSON.parseObject(jsonString, MQIoFoDto.class);
//        MQIoFoDto mqIoFoDto1 = JsonUtil.toJavaBean(jsonString, MQIoFoDto.class);
        System.out.println(mqIoFoDto1.getName());
    }
}
