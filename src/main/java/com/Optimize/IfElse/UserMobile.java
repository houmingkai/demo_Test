package com.Optimize.IfElse;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//多if-else优化(map代替)
public class UserMobile {

    /**
     * 思路是：将name作为key，将getMobile1/getMobile2/getMobile3这三个方法名作为value，放在map中，
     * 然后根据参数name，获取对应的方法名，然后使用反射来调用相关的方法。
     */
    static Map<String, String> map = new HashMap<String, String>();
    static {
        map.put("xiaoming", "getMobile1");
        map.put("xiaoming2", "getMobile2");
        map.put("xiaoming3", "getMobile3");
    }

    public String getMobile1(){
        return "1234561";
    }

    public String getMobile2(){
        return "1234562";
    }

    public String getMobile3(){
        return "1234563";
    }

    public String getMobile(String name){
        if(StringUtils.equalsIgnoreCase(name,"xiaoming1")){
            return this.getMobile1();
        }else if(StringUtils.equalsIgnoreCase(name,"xiaoming2")){
            return this.getMobile2();
        }else if(StringUtils.equalsIgnoreCase(name,"xiaoming3")){
            return this.getMobile3();
        }else{
            return "姓名不对";
        }
    }

    public String getMobielOptimize(String name) throws Exception {
        String methodName = map.get(name);
        if (StringUtils.isEmpty(methodName)) {
            return "姓名不对";
        }
        Method method = this.getClass().getMethod(methodName,new Class[]{});
        String mobile = (String) method.invoke(this);
        return mobile;
    }

    public static void main(String[] args) throws Exception {
        UserMobile userMobile = new  UserMobile();
        String mobile = userMobile.getMobielOptimize("xiaoming2");
        System.out.println("mobile:"+mobile);
    }



}
