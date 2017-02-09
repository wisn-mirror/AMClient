package com.wisn.pmlib.application;

import android.content.Context;

import com.wisn.pmlib.log.log;
import com.wisn.pmlib.utils.HttpClientSslHelper;
import com.wisn.pmlib.utils.TimeUtils;

import org.apache.http.client.HttpClient;

import java.io.File;

/**
 * <b>Create Date:</b> 2016/10/28<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class MApplication extends BaseApplication {
    public  static MApplication app;
    private static HttpClient mHttpsClient;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        File root = this.getCacheDir();
        File file = new File(root, "log");
        if (!file.exists()) {
            file.mkdirs();
        }
        final String times = TimeUtils.format("yyyy-MM-dd");
        file = new File(file, times + ".txt");
        log.config(file.getAbsolutePath());

    }
    public  static  MApplication  getInstance(){
        return app;
    }
    public static HttpClient getHttpsClient(Context mContext) {
        if (mHttpsClient == null) {
            mHttpsClient = HttpClientSslHelper.getSslHttpClient(mContext);
        }
        return mHttpsClient;
    }
}
