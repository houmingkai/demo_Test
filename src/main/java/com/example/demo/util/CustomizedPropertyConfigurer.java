package com.example.demo.util;

import com.alibaba.druid.filter.config.ConfigTools;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ObjectUtils;

import java.util.Enumeration;
import java.util.Properties;

public class CustomizedPropertyConfigurer extends PropertyPlaceholderConfigurer {

    private static Properties copyProps;

    private static final String PROPERTY_ENCRYPT_KEY_SUFFIX = ".encrypt";

    private static final int    PROPERTY_ENCRYPT_KEY_LENGTH = PROPERTY_ENCRYPT_KEY_SUFFIX.length();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
                                                                                                   throws BeansException {
        super.processProperties(beanFactory, props);
        copyProps = props;
    }


    /**
     * 解密指定propertyName的属性值
     *
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        // 过滤出需要解密的属性
        if (propertyName.trim().endsWith(PROPERTY_ENCRYPT_KEY_SUFFIX)) {
            try {
                logger.debug("convert >>> " + propertyName);
                return ConfigTools.decrypt(propertyValue);
            } catch (Exception e) {
                logger.error(propertyName + " convert error!");
                logger.error(e.getMessage(), e);
            }
        }
        return super.convertProperty(propertyName, propertyValue);
    }

    @Override
    protected void convertProperties(Properties props) {
        Enumeration<?> propertyNames = props.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String propertyName = (String) propertyNames.nextElement();
            String propertyValue = props.getProperty(propertyName);
            String convertedValue = this.convertProperty(propertyName, propertyValue);
            // 过滤出需要解密的属性
            if (propertyName.trim().endsWith(PROPERTY_ENCRYPT_KEY_SUFFIX)) {
                propertyName = propertyName.substring(0, propertyName.length() - PROPERTY_ENCRYPT_KEY_LENGTH);
            }
            if (!ObjectUtils.nullSafeEquals(propertyValue, convertedValue)) {
                props.setProperty(propertyName, convertedValue);
            }
        }
    }

    public static Properties getProps() {
        return copyProps;
    }

    public static Object get(String name) {
        return copyProps.get(name);
    }

    public static String getProperty(String name) {
        return copyProps.getProperty(name);
    }

    public static String getProperty(String name, String defaultValue) {
        return copyProps.getProperty(name, defaultValue);
    }

}
