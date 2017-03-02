package com.github.bingoohuang.log4j.more.utils;

import org.apache.log4j.xml.DOMConfigurator;

import java.net.URL;

/**
 * Created by huangjb on 2017/3/2.
 */
public class Log4jConfig {
    public static void configFile(String classpathConfigFile) {
        DOMConfigurator.configure(classpathResource(classpathConfigFile));
    }

    public static URL classpathResource(String resourceName) {
        return Log4jConfig.class.getClassLoader().getResource(resourceName);
    }
}
