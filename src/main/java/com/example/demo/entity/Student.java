package com.example.demo.entity;

import lombok.Data;

@Data
public class Student {

    private String name;
    private int age;
    private float score;

    public Student(String name, int age, float score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }



}
