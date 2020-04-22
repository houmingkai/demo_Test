/**
 * 
 */
package com.example.demo.util;

import com.example.demo.constant.exception.InfoException;
import com.example.demo.constant.exception.SysRunException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 类JsonUtil.java的实现描述：json转换工具类
 * 
 * @author Huangxiquan 2018年1月10日 上午11:03:57
 */
public class JsonUtil {

    /**
     * 日志工具
     */
    private static final Logger       logger       = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 对象转换器
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtil(){
    }

    /**
     * json转换器初始化
     * 
     * @return
     */
    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * 使用Jackson 数据绑定 将对象转换为 json字符串 还可以 直接使用 JsonUtils.getInstance().writeValueAsString(Object obj)方式
     * 
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("转换为json字符串失败" + e.toString());
        }
        return null;
    }

    /**
     * json字符串转化为 JavaBean 还可以直接JsonUtils.getInstance().readValue(String content,Class valueType)用这种方式
     * 
     * @param <T>
     * @param content
     * @param valueType
     * @return
     */
    public static <T> T toJavaBean(String content, Class<T> valueType) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
            return mapper.readValue(content, valueType);
        } catch (Exception e) {
            logger.error("json字符串转化为 javabean失败" + e.toString());
        }
        return null;
    }

    /**
     * json字符串转化为list 还可以 直接使用 JsonUtils.getInstance().readValue(String content, new TypeReference<List<T>>(){})方式
     * 
     * @param <T>
     * @param content
     * @return
     * @throws IOException
     */
    public static <T> List<T> toJavaBeanList(String content, TypeReference<List<T>> typeReference) throws InfoException {
        try {
            return objectMapper.readValue(content, typeReference);
        } catch (Exception e) {
            logger.error("json字符串转化为 list失败,原因:" + e.toString());
            throw new InfoException("json字符串转化为 list失败");
        }
    }

    /**
     * json字符串转化为list 还可以 直接使用 JsonUtils.getInstance().readValue(String content, new TypeReference<List<T>>(){})方式
     *
     * @param <T>
     * @param content
     * @return
     * @throws IOException
     */
    public static <T> Map<String, T> toJavaBeanMap(String content,
                                                   TypeReference<Map<String, T>> typeReference) throws IOException {
        try {
            objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper.readValue(content, typeReference);
        } catch (JsonParseException e) {
            logger.error("json字符串转化为 map失败", e);
            throw new SysRunException("json字符串转化为 map失败");
        } catch (JsonMappingException e) {
            logger.error("json字符串转化为 map失败", e);
            throw new JsonMappingException("json字符串转化为 map失败");
        } catch (IOException e) {
            logger.error("json字符串转化为 map失败", e);
            throw new IOException("json字符串转化为 map失败");
        }
    }

    public static <T> T toJavaBean(InputStream inputStream, Class<T> clzz) {
        try {
            return objectMapper.readValue(inputStream, clzz);
        } catch (Exception e) {
            logger.error("toJavaBean出错, 原因:" + e.toString());
        }
        return null;
    }

    public static <T> T toJavaBean(InputStream inputStream, TypeReference<T> typeRef) {
        try {
            return objectMapper.readValue(inputStream, typeRef);
        } catch (Exception e) {
            logger.error("toJavaBean出错, 原因:" + e.toString());
        }
        return null;
    }

    public static String stringJsonReplace(String str) {
        str = str.replace("&gt;", ">");
        str = str.replace("&lt;", "<");
        str = str.replace("&quot;", "\"");
        str = str.replace("&#39;", "\'");
        str = str.replace("&ldquo;", "“");
        str = str.replace("&rdquo;", "”");
        str = str.replace("&lsquo;", "‘");
        str = str.replace("&rsquo;", "’");
        str = str.replace("&mdash;&mdash;", "——");
        return str;
    }
}
