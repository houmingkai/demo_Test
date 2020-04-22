package com.example.demo.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://juejin.im/post/5cfdf98d6fb9a07eea32671b#heading-1
 *  Arrays.asList()   使用注意事项
 *  asList() 返回的是 java.util.Arrays.ArrayList  ,  而不是 java.util.ArrayList
 *  三种常见错误:
 *  1.将基本类型数组作为asList的参数
 *  2.将数组作为asList参数后，修改数组或List
 *  3.数组转换为集合后，进行增删元素
 */
public class ArraysTest {

    /**
     * 1.将基本类型数组作为asList的参数
     * 由于Arrays.ArrayList参数为可变长泛型，而基本类型是无法泛型化的，所以它把int[] arr数组当成了一个泛型对象，所以集合中最终只有一个元素arr。
     */
    @Test
    public void test1(){
        int[] arr = {1,2,3};
        List<int[]> ints = Arrays.asList(arr);
        System.out.println("基本数据类型转换结果:"+ints);
        System.out.println("========================");
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("正常结果"+list);
    }

    /**
     * 2.将数组作为asList参数后，修改数组或List
     * 由于asList产生的集合元素是直接引用作为参数的数组，所以当外部数组或集合改变时，数组和集合会同步变化，这在平时我们编码时可能产生莫名的问题
     */
    @Test
    public void test2(){
        String[] arr = {"阿碧","阿强","阿楠"};
        List<String> list = Arrays.asList(arr);
        arr[0]="胖子";
        list.set(1,"骚人");
        System.out.println(Arrays.toString(arr));
        System.out.println(list.toString());
    }

    /**
     * 3.数组转换为集合后，进行增删元素
     *
     * 由于asList产生的集合并没有重写add,remove等方法，所以它会调用父类AbstractList的方法，而父类的方法中抛出的却是异常信息
     */
    @Test
    public void test3(){
        String[] arr = {"阿碧","阿强","阿楠"};
        List<String> list = Arrays.asList(arr);
        list.add("国服第一");
        list.remove("阿碧");
        list.forEach(System.out::println);

    }
}
