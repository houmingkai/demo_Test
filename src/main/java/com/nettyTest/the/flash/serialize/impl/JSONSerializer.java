package com.nettyTest.the.flash.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.nettyTest.the.flash.serialize.Serializer;
import com.nettyTest.the.flash.serialize.SerializerAlogrithm;

public class JSONSerializer implements Serializer {

    /**
     * 序列化算法   -- 获取具体的序列化算法标识
     * @return
     */
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }
    /**
     * java 对象转换成二进制  ---Java 对象转换成字节数组
     */
    @Override
    public byte[] serialize(Object object) {

        return JSON.toJSONBytes(object);
    }
    /**
     * 二进制转换成 java 对象  ---将字节数组转换成某种类型的 Java 对象
     */
    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSON.parseObject(bytes, clazz);
    }
}
