package com.example.demo.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (TChannel)实体类
 *
 * @author makejava
 * @since 2018-10-19 10:29:49
 */
@Data
public class TChannel implements Serializable {
    private static final long serialVersionUID = 535022933312225027L;
    //主键
    private Integer id;
    //渠道id
    private String channelcode;
    //渠道名称
    private String channelname;
    //渠道描述
    private String channeldesc;
    //是否显示(0,不显示 1，显示)
    private Integer isshow;
    //对应客户id
    private String customerid;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;



}