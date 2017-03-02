package com.github.bingoohuang.log4j.more.appenders;

import com.github.bingoohuang.log4j.more.utils.Log4jConfig;
import lombok.extern.log4j.Log4j;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by huangjb on 2017/3/2.
 */
@Log4j
public class BlackcatAppenderTest {
    @BeforeClass
    public static void beforeClass() {
        Log4jConfig.configFile("log4j-blackcat-appender.xml");
    }

    @Test
    public void test() throws InterruptedException {
        log.info("Entering application.");

        // do something

        log.trace("这是我的第一个测试TRACE日志");
        log.debug("这是我的第一个测试DEBUG日志");
        log.info("这是我的第一个测试INFO日志");
        log.warn("这是我的第一个测试WARN日志");
        log.error("这是我的第一个测试ERROR日志");

        // do someting

        log.info("Exiting application.");
    }
}
