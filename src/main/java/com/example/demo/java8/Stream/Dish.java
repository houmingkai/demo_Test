package com.example.demo.java8.Stream;

import lombok.Data;

@Data
public class Dish {
    private  String name;
    private  boolean vegetarian;
    private  int calories;
    private  Type type;

    public Dish() {
    }

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public enum Type { MEAT, FISH, OTHER }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }
}
