package com.example.demo.java8.Stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


/**
 * 在本例中，我们先是对menu调用stream方法，由菜单得到一个流。数据源是菜肴列表（菜
 * 单），它给流提供一个元素序列。接下来，对流应用一系列数据处理操作：filter、map、limit
 * 和collect。除了collect之外，所有这些操作都会返回另一个流，这样它们就可以接成一条流
 * 水线，于是就可以看作对源的一个查询。最后，collect操作开始处理流水线，并返回结果（它
 * 和别的操作不一样，因为它返回的不是流，在这里是一个List）。在调用collect之前，没有任
 * 何结果产生，实际上根本就没有从menu里选择元素。你可以这么理解：链中的方法调用都在排
 * 队等待，直到调用collect
 */
public class StreamTest2 {
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



    /**
     * 打印
     * 流只能被消费一次
     */
    @Test
    public void test1(){
        Stream<Dish> stream = menu.stream();
        stream.forEach(System.out::println);
        //遍历第二次的时候会报错(遍历完成后,这个流就被已经被消费了)
        //java.lang.IllegalStateException: stream has already been operated upon or closed
        stream.forEach(System.out::println);
    }
    /**
     *  获取calories<400的元素名称并排序
     */
    @Test
    public void java7test2(){
        //java7
        List<Dish> LowCaloriesList = new ArrayList<>();
        //筛选
        for (Dish dish : menu) {
            if(dish.getCalories()<400){
                LowCaloriesList.add(dish);
            }
        }
        //排序
        Collections.sort(LowCaloriesList, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(),o2.getCalories());
            }
        });
        List<String> LowCaloriesListName = new ArrayList<>();
        for (Dish dish : LowCaloriesList) {
            LowCaloriesListName.add(dish.getName()+"-"+dish.getCalories());
        }
        System.out.println(LowCaloriesListName);

    }

    /**
     *  获取calories<400的元素名称并排序
     */
    @Test
    public void java8test2(){
        List<String> LowCaloriesListName = menu.stream()
                                        .filter(d -> d.getCalories() <400)
                                        .sorted(Comparator.comparing(Dish::getCalories))
                                        .map(Dish::getName)
                                        .collect(toList());
        System.out.println(LowCaloriesListName);
    }
}
