package com.wisn.pmlib.utils;


import com.wisn.pmlib.api.Constants;
import com.wisn.pmlib.log.log;

/**
 * <b>Create Date:</b> 2016/9/21<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class LogUtils {
    private static String Tag="Agent";
    public  static void v(String logmsg){
        if(Constants.DEBUG_MODE_ENABLED){
            log.v(Tag, logmsg);
        }
    }
    public  static void d(String logmsg){
        if(Constants.DEBUG_MODE_ENABLED){
            log.d(Tag, logmsg);
        }
    }
    public  static void i(String logmsg){
        if(Constants.DEBUG_MODE_ENABLED){
            log.i(Tag, logmsg);
        }
    }
    public  static void w(String logmsg){
        if(Constants.DEBUG_MODE_ENABLED){
            log.w(Tag, logmsg);
        }
    }
    public  static void e(String logmsg){
        if(Constants.DEBUG_MODE_ENABLED){
            log.e(Tag, logmsg);
        }
    }
    public  static void v(String Tag, String logmsg){
        if(Constants.DEBUG_MODE_ENABLED){
            log.v(Tag, logmsg);
        }
    }
    public  static void d(String Tag, String logmsg){
        if(Constants.DEBUG_MODE_ENABLED){
            log.d(Tag, logmsg);
        }
    }
    public  static void i(String Tag, String logmsg){
        if(Constants.DEBUG_MODE_ENABLED){
            log.i(Tag, logmsg);
        }
    }
    public  static void w(String Tag, String logmsg){
        if(Constants.DEBUG_MODE_ENABLED){
            log.w(Tag, logmsg);
        }
    }
    public  static void e(String Tag, String logmsg){
        if(Constants.DEBUG_MODE_ENABLED){
            log.e(Tag, logmsg);
        }
    }
}
