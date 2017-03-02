package com.github.bingoohuang.log4j.more.appenders;

import com.github.bingoohuang.blackcat.instrument.callback.Blackcat;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by huangjb on 2017/3/2.
 */
public class BlackcatAppender extends AppenderSkeleton {
    @Override protected void append(LoggingEvent event) {
        StringBuilder msg = createLogMessage(event);

        Blackcat.log(msg.toString());
    }

    private StringBuilder createLogMessage(LoggingEvent event) {
        StringBuilder msg = new StringBuilder();
        String log = this.layout.format(event);
        msg.append(log);

        if (!layout.ignoresThrowable()) {
            return msg;
        }

        String[] s = event.getThrowableStrRep();
        if (s == null) {
            return msg;
        }

        for (int i = 0, len = s.length; i < len; i++) {
            msg.append(s[i]).append(Layout.LINE_SEP);
        }

        return msg;
    }

    @Override public void close() {

    }

    @Override public boolean requiresLayout() {
        return true;
    }
}
