package com.wisn.pmlib.application;

import com.wisn.pmlib.log.log;
import com.wisn.pmlib.utils.TimeUtils;

import java.io.File;

/**
 * <b>Create Date:</b> 2016/10/28<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class MApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        File root = this.getCacheDir();
        File file = new File(root, "log");
        if (!file.exists()) {
            file.mkdirs();
        }
        final String times = TimeUtils.format("yyyy-MM-dd");
        file = new File(file, times + ".txt");
        log.config(file.getAbsolutePath());

    }
}
