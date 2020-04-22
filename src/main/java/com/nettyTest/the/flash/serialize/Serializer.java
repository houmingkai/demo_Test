package com.nettyTest.the.flash.serialize;

import com.nettyTest.the.flash.serialize.impl.JSONSerializer;

public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法   -- 获取具体的序列化算法标识
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制  ---Java 对象转换成字节数组
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象  ---将字节数组转换成某种类型的 Java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
