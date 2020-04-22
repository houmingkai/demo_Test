package com.Test.VariableParam;

import java.text.NumberFormat;

/**
 * 避免带有可变参数的方法重载
 */
public class Client {
    public  void calPrice(int price,int discount){
        float newPrice = price * discount / 100.F;
        System.out.println("打折后的价格是:"+formatCurrency(newPrice));

    }

    public void calPrice(int price,int... discounts){
        float newPrice = price;
        for (int discount : discounts) {
            newPrice =  newPrice * discount / 100;
        }
        System.out.println("折上折后的价格是:"+formatCurrency(newPrice));
    }

    //格式化成本的货币形式
    private static String formatCurrency(float price){
        return NumberFormat.getCurrencyInstance().format(price);
    }

    public static void main(String[] args){
      Client client = new Client();
      client.calPrice(100,80,80,10);
    }
}
