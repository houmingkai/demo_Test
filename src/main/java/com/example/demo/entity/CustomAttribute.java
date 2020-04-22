package com.example.demo.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomAttribute {

    @Value("${com.didispace.blog.name}")
    private String name ;

    @Value("${com.didispace.blog.title}")
    private String title;
}
