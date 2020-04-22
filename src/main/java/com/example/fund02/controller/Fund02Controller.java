package com.example.fund02.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.fund02.dao.Fund02Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * (Fund02)表控制层
 *
 * @author makejava
 * @since 2019-01-17 17:35:16
 */
@RestController
@RequestMapping("fund02")
public class Fund02Controller {

    /**
     * 服务对象
     */
    @Autowired
    private Fund02Dao fund02Dao;


    /**
     * 自定义注解测试
     */
//    (pre = KEY_PRE + "company", key = "#code", timeout = RedisUtils.DAY)
//    (pre = FUND_KEY_PRE, key = "#fundcode_#term")
    @GetMapping("/redisCache")
    public String RedisCacheTest(String key){
        return "redis测试";
    }

    public static void main(String[] args){
//        String c_url = "SELECT seq,ctime,mtime,rtime,isvalid,fundcode,F105V_FUND02,F106V_FUND02,F107V_FUND02 from fund02";
//        String c_url = "SELECT count(*) FROM FUND02 WHERE RTIME>TO_TIMESTAMP('1970-01-01 01:01:01','YYYY-MM-DD HH24\\:MI\\:SS.MS')";
        String c_url = "SELECT mtime from fund02 limit 3";
        ParamConstant.params.put("AppSQL", c_url);
        String c_json = null;
        try {
            c_json = HttpUtil.getRequest(ParamConstant.REQUESTPATH,  ParamConstant.params);
        } catch (IOException e) {
            System.out.println(e);
        }
        String dataStr = JSONObject.parseObject(c_json).getString("Data");
        System.out.println(dataStr);
        //转换为List
//        List<Fund02> list = null;
//        try {
//            list = JsonUtil.toJavaBeanList(dataStr, new org.codehaus.jackson.type.TypeReference<List<Fund02>>() {
//            });
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        System.out.println(list.size());
    }


    /**
     * 通过主键查询单条数据
     *
     * @return 单条数据
     */
    @GetMapping("/insert")
    public void insert() {
        System.out.println("GlobalExceptionHandler测试");
        String[] arr = {"1"};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[10]);
        }



//        String c_url = "SELECT seq,ctime,mtime,rtime,isvalid,fundcode,F105V_FUND02,F106V_FUND02,F107V_FUND02 from fund02";
//        ParamConstant.params.put("AppSQL", c_url);
//        String c_json = null;
//        try {
//            c_json = HttpUtil.getRequest(ParamConstant.REQUESTPATH,  ParamConstant.params);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        String dataStr = JSONObject.parseObject(c_json).getString("Data");
////        System.out.println(dataStr);
//        //转换为List
//        List<Fund02> list = null;
//        try {
//            list = JsonUtil.toJavaBeanList(dataStr, new org.codehaus.jackson.type.TypeReference<List<Fund02>>() {
//            });
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        System.out.println(list.size());
//        for (Fund02 fund02 : list) {
//            fund02Dao.insert(fund02);
//        }



}
}
