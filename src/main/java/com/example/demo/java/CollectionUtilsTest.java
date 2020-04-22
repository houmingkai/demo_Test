package com.example.demo.java;

import com.example.demo.java8.Stream.Dish;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * package org.springframework.util.CollectionUtils
 *
 * spring提供的集合操作类演示
 *
 * APi地址:https://docs.spring.io/spring-framework/docs/2.5.6/api/org/springframework/util/CollectionUtils.html
 */
public class CollectionUtilsTest {

    public static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    public static List<String> strList = Arrays.asList("1","2","3","4","5");
    //验证集合是否为空
    @Test
    public void testIsEmpty(){
        boolean menuEmpty = CollectionUtils.isEmpty(menu);
        System.out.println("menu is not null:"+menuEmpty);

        Map map = new HashMap();
        System.out.println("map is null:"+CollectionUtils.isEmpty(map));

    }

    //数组转换为list
    @Test
    public void arrayToList(){
        String[] arr = {"1","2","3"};
        List list = CollectionUtils.arrayToList(arr);
        System.out.println(list);
    }

    //是否包含
    @Test
    public void contains(){
//        System.out.println("是否包含'1':"+CollectionUtils.contains(strList.iterator(),"1"));
//        System.out.println("是否包含'10':"+CollectionUtils.contains(strList.iterator(),"10"));

        //验证对象
        Dish pork = new Dish("pork", false, 800, Dish.Type.MEAT);
        Dish test = new Dish("test", false, 800, Dish.Type.MEAT);
        //contains
//        System.out.println("是否包含pork:"+CollectionUtils.contains(menu.iterator(),pork));
//        System.out.println("是否包含test:"+CollectionUtils.contains(menu.iterator(),test));
        //containsInstance
        System.out.println("是否包含pork:"+CollectionUtils.containsInstance(menu,pork));
        System.out.println("是否包含test:"+CollectionUtils.containsInstance(menu,test));
    }


    //判断两个集合之中是否有重复元素
    @Test
    public void containsAny(){
        List<String> strList2 = Arrays.asList("5","6","7","8");
        System.out.println("两个集合之中是否有重复元素:"+CollectionUtils.containsAny(strList,strList2));

    }

}
