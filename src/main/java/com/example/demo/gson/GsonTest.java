package com.example.demo.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.HashMap;

public class GsonTest {

    public static GsonBuilder gsonBuilder = new GsonBuilder();
    public static UserInfo user = new UserInfo("shangsan","man",10);

    @Test
    public void test1(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "mafly");
        map.put("age", "18");
        map.put("sex", null);

        //当 Sex=null时，用 Gson 的tojson方法会把 null 值忽略，从而序列化之后输出不包含这个属性值的 Json 串
        //输出结果:{"name":"mafly","age":"18"}
        String jsonString = new Gson().toJson(map);
        System.err.println(jsonString);

        String toJson = gsonBuilder.serializeNulls().create().toJson(map);
        System.err.println(toJson);
    }

    @Test
    public void test2(){
        //@Expose + gsonBuilder.excludeFieldsWithoutExposeAnnotation() 只序列化带@Expose的字段
        //@SerializedName 可以更改序列化的字段名称
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        String toJson = gsonBuilder.serializeNulls().create().toJson(user);
        System.out.println(toJson);
    }

    @Test
    public void test3(){
        //@Since、@Until 不同版本不同数据
        ////这里要设置setVersion(3.2)，@Since标注的在 3.2 版本或之后才会输出，@Until标注的只在 3.2版本前才有
        gsonBuilder.setVersion(3.2);
        System.out.println(gsonBuilder.create().toJson(user));
    }
}
