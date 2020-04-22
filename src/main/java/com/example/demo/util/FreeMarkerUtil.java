/*
 * Copyright 2011-2015 10jqka.com.cn All right reserved. This software is the confidential and proprietary information
 * of 10jqka.com.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into with 10jqka.com.cn.
 */
package com.example.demo.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName: FreeMarkerUtil
 * @Description: freemarker工具类
 */
public class FreeMarkerUtil {

    protected static Log logger        = LogFactory.getLog(FreeMarkerUtil.class);
    private static Configuration configuration = null;

    /**
     * 获取模板配置
     * 
     * @param templateFilePath 模板路径
     * @return
     */
    public static Configuration getFreemarkerCFG(String templateFilePath) {
        if (null == configuration) {
            configuration = new Configuration();
            try {
                configuration.setLocale(Locale.CHINA);
                configuration.setDefaultEncoding("UTF-8");
                configuration.setEncoding(Locale.CHINA, "UTF-8");
                configuration.setDirectoryForTemplateLoading(new File(templateFilePath));
            } catch (Exception e) {
                logger.error("getFreemarkerCFG error:" + e.getMessage());
            }
        }

        return configuration;
    }

    /**
     * 产生静态文件
     * 
     * @param templateFilePath 模板路径 eg:WEB-INF/templates
     * @param templateName 模板名称 xxx.ftl
     * @param mapData 模板替换数据
     * @param staticFileOutPath 静态文件输出路径 eg:d:/xxx/xxxx
     * @param staticFileName 静态文件name eg:test.html
     */
    public static void geneHtmlFile(String templateFilePath, String templateName, Map<String, Object> mapData,
                                    String staticFileOutPath, String staticFileName) {
        try {
            Template template = getFreemarkerCFG(templateFilePath).getTemplate(templateName, "UTF-8");
            File file = new File(staticFileOutPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            File htmlFile = new File(staticFileOutPath + File.separator + staticFileName);
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
            template.process(mapData, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("geneHtmlFile error:" + e.getMessage());
        }
    }

    /**
     * 产生静态文件
     * 
     * @param templateFilePath 模板路径 eg:WEB-INF/templates
     * @param templateName 模板名称 xxx.ftl
     * @param mapData 模板替换数据
     * @param staticFileOutPath 静态文件输出路径 eg:d:/xxx/xxxx
     * @param staticFileName 静态文件name eg:test.html
     */
    public static void geneHtmlFileException(String templateFilePath, String templateName, Map<String, Object> mapData,
                                             String staticFileOutPath, String staticFileName) throws Exception {

        Template template = getFreemarkerCFG(templateFilePath).getTemplate(templateName, "UTF-8");
        File file = new File(staticFileOutPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        File htmlFile = new File(staticFileOutPath + File.separator + staticFileName);
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
        template.process(mapData, out);
        out.flush();
        out.close();
    }

    public static void main(String[] args) {

    }
}
