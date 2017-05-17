package com.wisn.pmlib.application;

import android.app.Activity;
import android.content.Context;

import com.wisn.pmlib.log.log;
import com.wisn.pmlib.utils.HttpClientSslHelper;
import com.wisn.pmlib.utils.TimeUtils;

import org.apache.http.client.HttpClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    /**打开的activity**/
    private List<Activity> activities = new ArrayList<>();

    /**
     * 新建了一个activity
     * @param activity
     */
    public void addActivity(Activity activity){
        activities.add(activity);
    }
    /**
     *  结束指定的Activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if (activity!=null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }
    /**
     * 应用退出，结束所有的activity
     */
    public void exit(){
        for (Activity activity : activities) {
            if (activity!=null) {
                activity.finish();
            }
        }
        System.exit(0);
    }
    /**
     * 关闭Activity列表中的所有Activity*/
    public void finishActivity(){
        for (Activity activity : activities) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }






    public static HttpClient getHttpsClient(Context mContext) {
        if (mHttpsClient == null) {
            mHttpsClient = HttpClientSslHelper.getSslHttpClient(mContext);
        }
        return mHttpsClient;
    }
}
