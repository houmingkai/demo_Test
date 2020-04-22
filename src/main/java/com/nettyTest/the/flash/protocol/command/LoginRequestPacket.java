package com.nettyTest.the.flash.protocol.command;

import lombok.Data;

import static com.nettyTest.the.flash.protocol.command.Command.LOGIN_REQUEST;

/**
 *  定义登录请求数据包
 */
@Data
public class LoginRequestPacket extends Packet {
    private String  userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
