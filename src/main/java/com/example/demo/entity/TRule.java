package com.example.demo.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TRule)实体类
 *
 * @author makejava
 * @since 2018-10-22 09:26:01
 */
public class TRule implements Serializable {
    private static final long serialVersionUID = 801561103927196904L;
    //主键
    private Integer id;
    //规则类型(1,source:资讯来源 2,title:资讯文章)
    private Object type;
    //过滤关键词
    private String keywords;
    //文章唯一标示
    private String seq;
    
    private String jumperurl;
    //渠道id串.以|分隔
    private String channelids;
    //对应客户id
    private String customerid;
    //创建时间
    private Date createtime;
    //更新时间
    private Date updatetime;
    //备注
    private String remark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getJumperurl() {
        return jumperurl;
    }

    public void setJumperurl(String jumperurl) {
        this.jumperurl = jumperurl;
    }

    public String getChannelids() {
        return channelids;
    }

    public void setChannelids(String channelids) {
        this.channelids = channelids;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}