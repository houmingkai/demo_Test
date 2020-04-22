package com.example.fund02.controller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

public class HttpUtil {

    protected static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String post(String requestPath, Map<String, String> params) throws HttpException, IOException {

        Set<String> keys = params.keySet();
        NameValuePair[] pairs = new NameValuePair[keys.size()];
        if (keys != null) {
            Iterator<String> iterator = keys.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                String name = iterator.next();
                String value = params.get(name);
                NameValuePair pair = new NameValuePair(name, value);
                pairs[i++] = pair;
            }
        }

        HttpClient httpclient = null;
        PostMethod postMethod = null;
        try {
            httpclient = new HttpClient();// 创建一个客户端，类似打开一个浏览器

            postMethod = new PostMethod(requestPath);
            postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 6000);
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            postMethod.addRequestHeader("accept-encoding", "gzip,deflate");

            postMethod.setRequestBody(pairs);

            int statusCode = httpclient.executeMethod(postMethod);

            String responseBody = getResponseBody(postMethod);
            if (logger.isInfoEnabled()) {
                logger.info(" push notice return message:" + responseBody);
            }
            return responseBody;
        } finally {
            postMethod.releaseConnection();
        }
    }


    /**
     * 客户端用Get方法向服务器提交请求,并获取服务器响应信息
     *
     */
    public static String reqForGet(String getURL) {
        try {
            URL url = new URL(getURL);
            URLConnection urlConn = url.openConnection();
            HttpURLConnection httpUrlConn = (HttpURLConnection) urlConn;
            // 默认情况下是false;
            httpUrlConn.setDoOutput(false);
            // 设置是否从httpUrlConnection读入，默认情况下是true
            httpUrlConn.setDoInput(true);
            // Get 请求不能使用缓存
            httpUrlConn.setUseCaches(false);
            // 设定请求的方法为"GET"，默认是GET
            httpUrlConn.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), "UTF-8"));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();

            return org.apache.commons.lang.StringUtils.trimToEmpty(sb.toString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return "";
    }

    public static String getRequest(String requestPath, Map<String, String> params) throws HttpException, IOException {

        Set<String> keys = params.keySet();
        NameValuePair[] pairs = new NameValuePair[keys.size()];

        if (keys != null) {
            Iterator<String> iterator = keys.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                String name = iterator.next();
                String value = params.get(name);
                NameValuePair pair = new NameValuePair(name, value);
                pairs[i++] = pair;
            }
        }

        HttpClient httpclient = null;
        GetMethod getMethod = null;
        try {
            httpclient = new HttpClient();// 创建一个客户端，类似打开一个浏览器
            // 3分钟连接
            httpclient.setConnectionTimeout(3 * 60 * 1000);
            // 3分钟无数据响应
            httpclient.setTimeout(3 * 60 * 1000);

            getMethod = new GetMethod(requestPath);
            getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
            getMethod.addRequestHeader("accept-encoding", "gzip,deflate");

            getMethod.setQueryString(pairs);
            int statusCode = httpclient.executeMethod(getMethod);

            String responseBody = getResponseBody(getMethod);
            if (logger.isInfoEnabled()) {
                logger.info(" push notice return message.length:"
                            + (responseBody != null ? responseBody.length() : null));
            }
            return responseBody;
        } finally {
            getMethod.releaseConnection();
        }
    }

    private static String getResponseBody(HttpMethod httpMethod) throws UnsupportedEncodingException, IOException {
        String responseBody = null;

        if (httpMethod.getResponseBody() != null || httpMethod.getResponseBodyAsStream() != null) {

            InputStream in = httpMethod.getResponseBodyAsStream();
            if (httpMethod.getResponseHeader("Content-Encoding") != null
                && httpMethod.getResponseHeader("Content-Encoding").getValue().toLowerCase().indexOf("gzip") > -1) {
                // 无压缩设置时启用下面的代码，关闭以 GZIPInputStream读取的代码
                // BufferedReader bin = new BufferedReader(new
                // InputStreamReader(in,"UTF-8"));

                // 有GZIP压缩设置时启用下面的代码，关闭上面的无压缩设置代码
                GZIPInputStream gzin = new GZIPInputStream(in);
                BufferedReader bin = new BufferedReader(new InputStreamReader(gzin, "UTF-8"));

                String s = null;
                StringBuffer buffer = new StringBuffer();
                while ((s = bin.readLine()) != null) {
                    buffer.append(s);
                }
                responseBody = buffer.toString();
            } else {
                BufferedReader bin = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = bin.readLine()) != null) {
                    sb.append(line);
                }
                responseBody = sb.toString();
            }
        }
        return responseBody;
    }

}
