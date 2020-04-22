package com.Test;

import org.junit.Test;

public class TestClass {

    private static final String HUMUMID = "returnForHs300:%s:id";
    @Test
    public void formatTest(){
        String format = String.format(HUMUMID, "hmk");
        System.out.println(format);  //returnForHs300:hmk:id    参数2替换'%s'
    }

    /**
     * 三元表达式的类型要保持一致
     */
    @Test
    public void threeYuanTest(){
        int i = 80;
        String s1 = String.valueOf(i < 100 ? 90 : 100);
        System.out.println("s1:"+s1);
        //类型不一致的时候会发生类型转换
        String s2 = String.valueOf(i < 100 ? 90 : 100.0);
        System.out.println("s2:"+s2);
        System.out.println("s1与s2是否相等："+s1.equals(s2));
    }

    @Test
    public void addAddTest(){
        int count = 0;
        for(int i=0;i<=10;i++){
            count = count++;
        }
        System.out.println(count);
    }
}
