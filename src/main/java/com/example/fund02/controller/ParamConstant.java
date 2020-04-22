package com.example.fund02.controller;

import java.util.HashMap;
import java.util.Map;

public class ParamConstant {

        public static Map<String, String> params = new HashMap(){{
        // 一下参数参照[数据库应用接口应用开发说明文档.doc]
        put("dname", "ifind");
        put("dpassword", "ifind!@$456");
        put("dbtype", "pgsql");
        put("UserName", "APP_B2B");
        put("PWD", "APP_B2B_ZX");}};


        public static String REQUESTPATH = "http://ft.10jqka.com.cn/dataConnector/DBService.php";

}
