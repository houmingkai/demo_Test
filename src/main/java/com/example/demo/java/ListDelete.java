package com.example.demo.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListDelete {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("a1", "ab2", "a3", "ab4", "a5", "ab6", "a7", "ab8", "a9"));
        System.out.println("删除之前:"+list.toString());
        /**
         * 正常删除，推荐使用
         */
        for (Iterator<String> ite = list.iterator(); ite.hasNext();) {
                String str = ite.next();
            if (str.contains("b")) {
                ite.remove();
            }
        }
        System.out.println("删除all包含b字符的元素:"+list.toString());

        /**
         * 报错
         * java.util.ConcurrentModificationException
         *
         * 原因(查看ArrayList源码中:private class Itr implements Iterator<E>)
         * next()
         * 取下个元素的时候都会去判断要修改的数量和期待修改的数量是否一致，不一致则会报错，而通过迭代器本身调用remove方法则不会有这个问题，因为它删除的时候会把这两个数量同步
         */
//        for (Iterator<String> ite = list.iterator(); ite.hasNext(); ) {
//            String str = ite.next();
//            if (str.contains("b")) {
//                list.remove(str);
//            }
//        }
    }
}
