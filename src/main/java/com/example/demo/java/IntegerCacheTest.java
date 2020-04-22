package com.example.demo.java;

public class IntegerCacheTest {

    public static void main(String[] args){
        Integer i3 = 100;
        Integer i4 = 100;
        System.out.println(i3 == i4);

        Integer i5 = 1000;
        Integer i6 = 1000;
        System.out.println(i5 == i6);

        Integer i7 = new Integer(100);
        Integer i8 = new Integer(100);
        System.out.println(i8 == i7);
    }


}
