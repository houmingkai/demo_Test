package com.example.demo.java8.Stream;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {
    
    public static void main(String[] args){
        List<Apple> appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple22 =  new Apple(2,"大西瓜",new BigDecimal("3.33"),35);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple22);
        appleList.add(apple3);

        //limit(n):返回前 n 个元素
        List<Apple> limitList = appleList.stream().limit(3).collect(Collectors.toList());
        System.out.println(limitList);

        /**
     * List -> Map
     * 需要注意的是：
     * toMap 如果集合对象有重复的key，会报错Duplicate key ....
     *  apple1,apple12的id都为1。
     *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
     */
        Map<Integer, Apple> map = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));


        /**
         * 分组
         */
//        Map<Integer, List<Apple>> map = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
//        System.out.println(map);

        //过滤出符合条件的数据
        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("香蕉")).collect(Collectors.toList());
//        System.out.println(filterList);

        //去重
        List<Apple> unique = appleList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparingLong(Apple::getId))), ArrayList::new)
        );
//        System.out.println(unique);




    }

    @Test
    public void convertTest() {
        List<String> collected = new ArrayList<>();
        collected.add("alpha");
        collected.add("beta");
//        collected = collected.stream().map(string -> string.toUpperCase()).collect(Collectors.toList());
        collected = collected.stream().map(String::toUpperCase).collect(Collectors.toCollection(ArrayList::new));//注意发生的变化
        System.out.println(collected);
    }

}
