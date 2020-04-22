package com.example.fund02.entity;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * (Fund02)实体类
 *
 * @author makejava
 * @since 2019-01-17 17:35:08
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fund02  {
    //记录唯一标识
    @JsonProperty(value = "SEQ")
    private String seq;
    //记录创建日期
    @JsonProperty(value = "CTIME")
    private String ctime;
    //记录修改日期
    @JsonProperty(value = "MTIME")
    private String mtime;
    //记录通讯到用户端日期
    @JsonProperty(value = "RTIME")
    private String rtime;
    //是否有效
    @JsonProperty(value = "ISVALID")
    private String isvalid;
    //基金代码
    @JsonProperty(value = "FUNDCODE")
    private String fundcode;
    //申购确认日(T+N格式)
    @JsonProperty(value = "F105V_FUND02")
    private String f105vFund02;
    //赎回划款日(T+N格式)
    @JsonProperty(value = "F106V_FUND02")
    private String f106vFund02;
    //赎回确认日(T+N格式)
    @JsonProperty(value = "F107V_FUND02")
    private String f107vFund02;


}