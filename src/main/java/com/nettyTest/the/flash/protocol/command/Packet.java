package com.nettyTest.the.flash.protocol.command;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    /**
     * 获取指令的抽象方法
     * @return
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
    
    
    public static void main(String[] args){
        String s = null;
        System.out.print(s);

        Integer i = null;
        System.out.print(i);
    }
}
