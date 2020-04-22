package com.example.demo.java8.optional;

import com.example.demo.java8.dto.Car;
import com.example.demo.java8.dto.Insurance;
import com.example.demo.java8.dto.Person;

public class OptionalTest {
    private static final String FINANCIAL_EARNING = "resource:%s:earning";


    public static void main(String[] args){
//        String abc = String.format(FINANCIAL_EARNING, "2100000510_000861");
//        System.out.println(abc);

    }


    public String getCarInsuranceName(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }
}
