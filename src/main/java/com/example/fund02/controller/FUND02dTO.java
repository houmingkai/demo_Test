package com.example.fund02.controller;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FUND02dTO {

    @JsonProperty(value = "F105V_FUND02")
    private String F105V_FUND02;
    @JsonProperty(value = "FUNDCODE")
    private String FUNDCODE;
    @JsonProperty(value = "F106V_FUND02")
    private String F106V_FUND02;
    @JsonProperty(value = "F107V_FUND02")
    private String F107V_FUND02;

}
