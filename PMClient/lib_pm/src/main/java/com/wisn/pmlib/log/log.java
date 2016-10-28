package com.wisn.pmlib.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Locale;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * <b>Create Date:</b> 8/20/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class log {
    private static Logger _LOGGER;
    private log() {
        //no instance
    }

    public static void config(String fileName) {
        final LogConfigurator configurator = new LogConfigurator();
        // config log level
        configurator.setRootLevel(Level.ALL);
        // config log file
        configurator.setFilePattern("%d - [%p::%c] - %m%n");
        configurator.setFileName(fileName);
        configurator.setUseFileAppender(true);
        // 文件大小10M
        configurator.setMaxFileSize(10 * 1024 * 1024);
//        configurator.setMaxFileSize(5 * 1024);

        // config logcat
        configurator.setUseLogCatAppender(true);
        // 不立即写日志
        configurator.setImmediateFlush(false);
        configurator.configure();

        _LOGGER = Logger.getLogger("vmc");
    }

    public static void v(String tag, String msg) {
//        lg.v(tag, msg);
        _LOGGER.trace(format(tag, msg));
    }

    public static void d(String tag, String msg) {
//        lg.d(tag, msg);
        _LOGGER.debug(format(tag, msg));
    }

    public static void i(String tag, String msg) {
//        lg.i(tag, msg);
        _LOGGER.info(format(tag, msg));
    }

    public static void w(String tag, String msg) {
//        lg.w(tag, msg);
        _LOGGER.warn(format(tag, msg));
    }

    public static void e(String tag, String msg) {
//        lg.e(tag, msg);
        _LOGGER.error(format(tag, msg));
    }

    private static String format(String tag, String message) {
        return format("[%s] %s", tag, message);
    }

    private static String format(String tag, String message, Throwable t) {
        return format("[%s] %s. %s", tag, message, t.getMessage());
    }

    private static String format(String format, Object... message) {
        return String.format(Locale.getDefault(), format, message);
    }


}
