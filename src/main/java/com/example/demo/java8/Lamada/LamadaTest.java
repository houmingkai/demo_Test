package com.example.demo.java8.Lamada;


import com.example.demo.entity.Student;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class LamadaTest {

    /**
     * 实现Comparator接口重写compare方法
     * @param o1
     * @param o2
     * @return  -1：由高到底排序 ,1由低到高排序,0相等
     */
//    @Override
//    public int compare(Student o1, Student o2) {
//        if(o1.getScore()>o2.getScore())
//            return -1;
//        else if(o1.getScore()<o2.getScore())
//            return 1;
//        else{
//            if(o1.getAge()>o2.getAge())
//                return 1;
//            else if(o1.getAge()<o2.getAge())
//                return -1;
//            else
//                return 0;
//        }
//    }


    /**
     * 使用lambda表达式对列表进行迭代
     */
    @Test
    public void iterTest() {
        List<String> languages = Arrays.asList("java", "scala", "python");
        //before java8
//        for(String each:languages) {
//            System.out.println(each);
//        }
        //after java8
//        languages.forEach(x -> System.out.println(x));
        languages.forEach(n -> {
            if (n.equals("java")) {
                System.out.println(n);
            }
        });
        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
//        languages.forEach(System.out::println);
    }

    /**
     * lambda表达式替换匿名类   ----用lambda表达式实现Runnable
     */
    @Test
    public void oldRunable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("The old runable now is using!");
            }
        }).start();
    }

    @Test
    public void runable() {
        new Thread(() -> System.out.println("It's a lambda function!")).start();
    }

    /**
     * lambda表达式替换匿名类   ---- Collections.sort()
     */
    @Test
    public void oldSort() {
        List<Student> students = ArrToList();
        System.out.println("排序之前");
        students.forEach(System.out::println);
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {  //先按成绩排序,成绩相同按年龄排序
                if (o1.getScore() > o2.getScore())
                    return -1;
                else if (o1.getScore() < o2.getScore())
                    return 1;
                else {
                    if (o1.getAge() > o2.getAge())
                        return 1;
                    else if (o1.getAge() < o2.getAge())
                        return -1;
                    else
                        return 0;
                }
            }
        });
        System.out.println("排序之后:");
        students.forEach(System.out::println);
    }

    @Test
    public void java8Sort() {
        List<Student> students = ArrToList();
        System.out.println("排序之前");
        students.forEach(System.out::println);
        Collections.sort(students, (o1, o2) ->{
            if (o1.getScore() > o2.getScore())
                return -1;
            else if (o1.getScore() < o2.getScore())
                return 1;
            else {
                if (o1.getAge() > o2.getAge())
                    return 1;
                else if (o1.getAge() < o2.getAge())
                    return -1;
                else
                    return 0;
            }
        });
        System.out.println("排序之后:");
        students.forEach(System.out::println);
    }

    public List<Student> ArrToList() {

        Student stu[] = {new Student("zhangsan", 20, 90.0f),
                new Student("lisi", 22, 90.0f),
                new Student("wangwu", 20, 99.0f),
                new Student("sunliu", 22, 100.0f)};
        List<Student> list = Arrays.asList(stu);
        return list;
    }
    /**
     * lambda表达式+函数式接口Predicate 进行集合数据过滤
     */
    @Test
    public void predicate(){
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//        filter2(languages,(str)->str.length() > 4);
    }


    public static void filter(List names, Predicate condition){
        names.forEach(name -> {
            if(condition.test(name)){
                System.out.println(name+" ");
            }
        });
    }

    public static void filter2(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }


    @Test
    public void lamada(){

    }
}
