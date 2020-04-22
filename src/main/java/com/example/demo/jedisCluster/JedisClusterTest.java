package com.example.demo.jedisCluster;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


/**
 *   本地集群描述:
 *   本地部署的redis集群共有6个节点(3主3从)
 *   对应关系:
 *   6380(主)--->6383(从),
 *   6381(主)--->6384(从),
 *   6382(主)--->6385(从)
 */
public class JedisClusterTest {

    public static HostAndPort hostAndPort = new HostAndPort("127.0.0.1",6386);
    public static JedisCluster jedisCluster= new JedisCluster(hostAndPort);

    @Test
    public void test1(){
//        redis-trib.rb create --replicas 1 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 127.0.0.1:6385
        System.out.println("存数据");
        jedisCluster.set("jedisCluster2","hmk");    //6382(主)和6385(从)中有数据
        System.out.println("取数据");
        String jedisCluster = this.jedisCluster.get("jedisCluster2");
        System.out.println(jedisCluster);
    }
    
}
