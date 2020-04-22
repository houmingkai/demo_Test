package com.example.demo.jedis;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JedisTest {
    public static final int REDIS_HASH_MARKET = 5;

    @Test
    public void test1(){
//        RedisUtil.set("test","test",3);
        System.out.println(RedisUtil.get("test",3));

        Map map = new HashMap();
        map.put("field1","value1");
        map.put("field2","value2");
        map.put("field3","value3");
        RedisUtil.hmset("hashTest",map,REDIS_HASH_MARKET);
    }

    @Test
    public void testHash(){
//        Map<String, String> hashTest = RedisUtil.getMap("hashTest", REDIS_HASH_MARKET);
//        System.out.println(hashTest);
        if(RedisUtil.hexists("hashTest","field3",REDIS_HASH_MARKET)){
            System.out.println("field3存在");
        }
    }

}
