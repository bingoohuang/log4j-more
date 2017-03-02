package com.github.bingoohuang.log4j.more.layout;

import com.github.bingoohuang.log4j.more.utils.Log4jConfig;
import lombok.extern.log4j.Log4j;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by huangjb on 2017/3/2.
 */
@Log4j
public class MaskPatternLayoutTest {
    @BeforeClass
    public static void beforeClass() {
        Log4jConfig.configFile("log4j-console-mask-layout.xml");
    }

    @Test
    public void test() {
        log.info("我的18位身份证号码：12345612345678123X, 我的15位身份证号码：123456123456123, apptx=12345612345678123X");
        log.info("我的工作证号码：60476");
    }
}
