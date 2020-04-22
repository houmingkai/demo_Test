package com.example.demo.IText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFTEST {
    
    public static void main(String[] args){
//        System.out.println(getEffectiveContract());
        System.out.println(Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())));
    }


    private static String getEffectiveContract() {
        String recordText = "";
        BufferedReader bf = null;
        String contractPath = "http://127.0.0.1:9080/group1/M00/00/00/wKiu_VwaNIgEAAAAAAAAAELDsYM33.html";
        try {
            // 不带本号的协议（也就是最新的协议）
            URL url = new URL(contractPath);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(30000);
            bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            StringBuilder buffer = new StringBuilder();
            String s = null;
            while ((s = bf.readLine()) != null) {// 正文内容从第二行开始读
                buffer.append(s.trim());
            }
            recordText = new String(buffer);

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return recordText;
    }
}


