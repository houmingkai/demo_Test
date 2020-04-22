package com.example.demo.java;

import com.example.demo.java8.Stream.Dish;
import org.junit.Test;

import java.util.Objects;

/**
 * https://www.cnblogs.com/DFX339/p/9945771.html
 * 判断Java对象是否为null可以有两层含义：
 *
 * 第一层：  直接使用 object == null 去判断，对象为null的时候返回true,不为null的时候返回false。
 *
 * 第二层：在object != null 为true的情况 下，进一步去判断对象的所有属性是否为null。
 */
public class ObjectIsNull {


    @Test
    public void testIsNull(){
        Dish dish =null;
        System.out.println(Objects.isNull(dish));
        System.out.println(Objects.isNull(new Dish()));
        System.out.println("======================================");
    }
}
