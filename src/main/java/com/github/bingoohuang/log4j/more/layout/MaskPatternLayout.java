package com.github.bingoohuang.log4j.more.layout;


import lombok.val;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 掩码布局类。
 */
public class MaskPatternLayout extends PatternLayout {
    @Override
    public String format(LoggingEvent event) {
        if (!(event.getMessage() instanceof String))
            return super.format(event);

        String message = maskFormat(event.getRenderedMessage());

        val throwableInfo = event.getThrowableInformation();
        val throwable = throwableInfo != null ? throwableInfo.getThrowable() : null;

        val logger = Logger.getLogger(event.getLoggerName());
        LoggingEvent maskedEvent = new LoggingEvent(
                event.fqnOfCategoryClass,
                logger,
                event.timeStamp,
                event.getLevel(),
                message,
                throwable);

        return super.format(maskedEvent);
    }

    private Pattern[] patterns;
    private String[] masks;
    private String separate = " ";
    private MatchType matchType = MatchType.first;

    private String maskFormat(String original) {
        String message = original;

        for (int i = 0; i < patterns.length; ++i) {
            Matcher matcher = patterns[i].matcher(message);

            if (matcher.find()) {
                message = matcher.replaceAll(i < masks.length ? masks[i] : "*");
                if (matchType == MatchType.first) break;
            }
        }

        return message;
    }

    /**
     * 设置匹配类型。
     *
     * @param matchType "first"或者"all"
     */
    public void setMatchType(String matchType) {
        this.matchType = MatchType.valueOf(matchType);
    }

    /**
     * 多个模式之间的正则分隔符。
     *
     * @param separate 正则分隔符
     */
    public void setSeparate(String separate) {
        this.separate = separate;
    }

    /**
     * 以分隔符进行分割的模式列表。
     *
     * @param patterns 模式列表
     */
    public void setPatterns(String patterns) {
        String[] subPatterns = patterns.split(separate);
        this.patterns = new Pattern[subPatterns.length];
        for (int i = 0; i < subPatterns.length; ++i)
            this.patterns[i] = Pattern.compile(subPatterns[i]);
    }

    /**
     * 对应模式的掩码列表。
     *
     * @param masks 掩码列表
     */
    public void setMasks(String masks) {
        String[] subMasks = masks.split(separate);
        this.masks = new String[subMasks.length];
        for (int i = 0; i < subMasks.length; ++i)
            this.masks[i] = subMasks[i];
    }


    /**
     * 掩码类型。
     */
    public enum MatchType {
        /**
         * 仅对第一个匹配进行全部替换。
         */
        first,
        /**
         * 对所有匹配都进行替换。
         */
        all
    }

}