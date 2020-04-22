/*
 * Copyright 2011-2016 10jqka.com.cn All right reserved. This software is the confidential and proprietary information
 * of 10jqka.com.cn (Confidential Information"). You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into with 10jqka.com.cn.
 */
package com.example.demo.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/***
 * 
 * @Company:浙江核新同花顺网络信息股份有限公司
 * @ClassName: RedisUtil
 * @Description: 修改原有代码，保留接口，不加锁，对pools采用ConcurrentHashMap控制并发，以修复原本对get加锁造成的缓慢问题
 * @Author: wangwenwei@myhexin.com
 * @CreateDate: 2016-8-6 下午3:34:55
 * @version:1.0
 */
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static Map<Integer, JedisPool> pools  = new ConcurrentHashMap<Integer, JedisPool>();

    static {
        JedisPoolConfig poolConfig = initPoolConfig();
//        System.out.println(CommonConstants.REDIS_IP+" "+CommonConstants.REDIS_PORT+" "+CommonConstants.REDIS_PASSWORD);
        pools.put(0, new JedisPool(poolConfig, CommonConstants.REDIS_IP, CommonConstants.REDIS_PORT, 0, CommonConstants.REDIS_PASSWORD, 0));
        
//        set(":600006:", "16", 15);
    }

    /**
     * 初始化 JedisPoolConfig
     * 
     * @return
     */
    private static JedisPoolConfig initPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5);
        poolConfig.setMaxIdle(5);
        poolConfig.setMaxWaitMillis(1000 * 100);
        poolConfig.setTestOnBorrow(true);

        return poolConfig;
    }

    /**
     * 初始化连接池
     * @param database
     * @return
     */
    private static JedisPool initPoolMap(int database) {
        JedisPool jedisPool = null;
        JedisPoolConfig poolConfig = initPoolConfig();
        pools.put(database, new JedisPool(poolConfig, CommonConstants.REDIS_IP, CommonConstants.REDIS_PORT, 0, CommonConstants.REDIS_PASSWORD, database));
        jedisPool = pools.get(database);

        return jedisPool;
    }

    /***
     * 切换连接池
     * @param database
     * @return
     */
    private  static JedisPool select(int database) {
        // 切换
        JedisPool jedisPool = pools.get(database);
        // 不存在，初始化
        if (null == jedisPool) {
            jedisPool =  initPoolMap(database);
        }
        return jedisPool;
    }

    /***
     * 返还连接池
     * @param jedis
     * @param database
     */
    @Deprecated
    private static void returnResource(Jedis jedis,int database){
        pools.get(database).returnResource(jedis);
    }
    
    @Deprecated
    private static void returnBrokenResource(Jedis jedis,int database){
        pools.get(database).returnBrokenResource(jedis);
    }
    
    /***
     * 关闭连接
     * @param jedis
     */
    private static void closeResource(Jedis jedis){
        if(jedis!=null)
            jedis.close();
    }
    
    /***
     * 从连接池获取一个实例
     * @param database
     * @return
     */
    private static Jedis getResource(int database){
        return select(database).getResource();
    }

    
    /**
     * 获取数据
     * 
     * @param key
     * @return
     */
    public  static String get(String key, int market) {
        String value = null;
        Jedis jedis =  null;
        try {
            jedis = getResource(market);
            value = jedis.get(key);
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }

        return value;
    }

    /**
     * 设置数据
     * 
     * @param key
     * @return
     */
    public static void set(String key, String value, int market) {
        Jedis jedis = null;
        try {
            jedis = getResource(market);
            jedis.set(key, value);
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }
    }
    
    /**
     * 缓存带有过期时间的数据
     *@param key
     *@param value
     *@param market
     */
    public static void setWithExpire(String key, String value, int market) {
        Jedis jedis = null;
        try {
            jedis = getResource(market);
            jedis.setex(key, CommonConstants.REDIS_EXPIRE, value);
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }
    }

    /**
     * 缓存带有  1天24小时 的  过期时间的数据
     *@param key
     *@param value
     *@param market
     */
    public static void setWith1DayExpire(String key, String value, int market) {
        Jedis jedis = null;
        try {
            jedis = getResource(market);
            jedis.setex(key,60*60*24, value);
            logger.info("setWith1DayExpire info--> key:{" + key + "},time:{ 24hours },market:{" + market + "}");
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }
    }

    public static void del(String key, int market){
    	
    	Jedis jedis =  null;
        try {
            jedis = getResource(market);
            jedis.del(key);
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }
    }

    /**
     * 模糊查询key
     * @param keyWord 关键字
     */
    public static HashSet selectLike(String keyWord, int market){
        HashSet value = null;
        Jedis jedis =  null;
        try {
            jedis = getResource(market);
            value = (HashSet) jedis.keys(keyWord + "*");
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }

        return value;
    }

    public static long setnx(String key, String value, int market){

        Long result=null;
        Jedis jedis =  null;
        try {
            jedis = getResource(market);
            result = jedis.setnx(key, value);
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }

        return result;
    }

    public static String getSet(String key, String value, int market){

        String result=null;
        Jedis jedis =  null;
        try {
            jedis = getResource(market);
            result = jedis.getSet(key, value);
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }

        return result;
    }
    /**
     * =============================== Hash begin ====================================
     */

    /**
     * 向key中设置多个键值对(带过期时间)
     *@param key
     *@param value
     *@param market
     */
    public static void setMapWithExpire(String key, Map<String, String> value, int market) {
        Jedis jedis = null;
        try {
            jedis = getResource(market);
            jedis.hmset(key, value);
            jedis.expire(key, CommonConstants.REDIS_EXPIRE);
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }
    }

    /**
     * 向key中设置多个键值对
     *@param key
     *@param value
     *@param market
     */
    public static void hmset(String key, Map<String, String> value, int market) {
        Jedis jedis = null;
        try {
            jedis = getResource(market);
            jedis.hmset(key, value);
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }
    }

    /**
     * 获取key中所有键值对
     *@param key
     *@param market
     *@return
     */
    public static Map<String,String> getMap(String key, int market){

        Map<String, String> value = null;
        Jedis jedis =  null;
        try {
            jedis = getResource(market);
            value = jedis.hgetAll(key);
        }  finally {
            // 返还到连接池
            closeResource(jedis);
        }

        return value;

    }
    /**
     * 设置一个键值对
     * @param key 键
     * @param field
     * @param value
     * @param market
     * @return
     */
    public static Long setHash(String key, String field, String value, int market) {

        Jedis jedis = null;
        Long l = 0l;
        try {
            jedis = getResource(market);
            l = jedis.hset(key, field, value);
            logger.info("set hash with key:{" + key + "},field:{" + field + "},market:{" + market + "}");
        } finally {
            // 返还到连接池
            closeResource(jedis);
        }
        return l;
    }

    /**
     * 获取key中指定域名的value
     * @param key 键
     * @param field
     * @param market
     * @return
     */
    public static String getHash(String key, String field, int market) {

        Jedis jedis = null;
        String value = null;
        try {
            jedis = getResource(market);
            value = jedis.hget(key, field);
        } finally {
            // 返还到连接池
            closeResource(jedis);
        }
        return value;
    }

    /**
     *  删除key中一个指定的field
     * @param key
     * @param field
     * @param market
     * @return
     */
    public static Long delHash(String key, String field, int market) {
        Jedis jedis = null;
        Long value = null;
        try {
            jedis = getResource(market);
            value = jedis.hdel(key, field);
            logger.info("delete hash with key:{" + key + "},field:{" + field + "},market:{" + market + "}");
        } finally {
            // 返还到连接池
            closeResource(jedis);
        }
        return value;
    }

    /**
     * 获取指定key所有的域
     * @param key
     * @param market
     * @return
     */
    public static Set<String> getFields(String key, int market) {
        Jedis jedis = null;
        Set<String> fields;
        try {
            jedis = getResource(market);
            fields = jedis.hkeys(key);
        } finally {
            // 返还到连接池
            closeResource(jedis);
        }
        return fields;
    }

    /**
     *  获取所有value
     * @param key
     * @param market
     * @return
     */
      public static List<String> getValues(String key, int market){
          Jedis jedis = null;
          List<String> values;
          try {
              jedis = getResource(market);
              values = jedis.hvals(key);
          } finally {
              // 返还到连接池
              closeResource(jedis);
          }
          return values;
      }

      public static Boolean hexists(String key, String field,int market){
          Jedis jedis = null;
          Boolean isExist;
          try {
              jedis = getResource(market);
              isExist = jedis.hexists(key,field);
          } finally {
              // 返还到连接池
              closeResource(jedis);
          }
          return isExist;

      }


}
