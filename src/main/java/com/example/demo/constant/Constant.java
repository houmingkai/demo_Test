package com.example.demo.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Constant {

    @Autowired
    private Environment env;                  // 全局环境变量

    // 模板目录
   // public static final String TEMPLATEFILEPATH                   = CustomizedPropertyConfigurer.getProperty("templatefile.path");
    public static  String TEMPLATEFILEPATH ;
    //模板名称

    // 静态文件生成目录
    //public static final String ZS_STATICFILEGENERATEDIR           = CustomizedPropertyConfigurer.getProperty("static.generate.rootdir");
    public static  String ZS_STATICFILEGENERATEDIR;
    //静态化模板
    public static final String GETCHANNELFTL              = "channel.ftl";
    public static final String GETCHANNELDATAJS               = "channel.js";

    @PostConstruct
    public void init() {
        TEMPLATEFILEPATH = env.getProperty("templatefile.path");
        ZS_STATICFILEGENERATEDIR = env.getProperty("static.generate.rootdir");
    }
    public static void main(String[] args){
        String templateFilePath = Constant.TEMPLATEFILEPATH;
        System.out.println(templateFilePath);
    }
}
