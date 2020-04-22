package com.example.demo.mq;

/**
 * 类MQInfo.java的实现描述：
 * MQ 参数配置实体bean。 
 * @author liuhualuo@myhexin.com 2013-7-17 下午4:18:50
 */
public class MQInfo {
    
    private String host;
    private String uri;
    private String exchange;
    private String routingKey;
    private String queue;
    private String charSet;
    
    public MQInfo(){
        
    }
    
    public MQInfo(String uri, String queue, String routingKey, String exchange, String charSet) {
        this.uri = uri;
        this.queue = queue;
        this.routingKey = routingKey;
        this.exchange = exchange;
        this.charSet = charSet;
    }
    
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getUri() {
        return uri;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public String getExchange() {
        return exchange;
    }
    
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    
    public String getRoutingKey() {
        return routingKey;
    }
    
    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
    
    public String getQueue() {
        return queue;
    }
    
    public void setQueue(String queue) {
        this.queue = queue;
    }
    
    public String getCharSet() {
        return charSet;
    }
    
    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }
}
