package com.wisn.pmlib.activity.ssl;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;
import com.wisn.pmlib.utils.LogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wisn on 2017/4/18.
 */

public class HttpUrlConnectionSSLActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.httpurlconnection_ssl_activity);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(i<100){
                    displayBriefMemory();
                    i++;
                }
               /* getAppSatus(HttpUrlConnectionSSLActivity.this,"com.wisn.pm");
                 String mhttpUrl = "https://10.0.34.252:9443/analytics/table_exists?table=TEST";
                // String mhttpUrl = "http://10.0.34.252:9763/analytics/table_exists?table=TEST";
                HashMap<String,String> map=new HashMap<>();
                map.put("Content-Type", "application/json");
                map.put("Authorization", "Basic YWRtaW46YWRtaW4=");
                String result = HttpsRequestUtil.httpGet(mhttpUrl, map);
                LogUtils.e("HttpUrlConnectionSSLActivity",result);*/
            }
        }).start();

    }
    /**
     * 返回app运行状态
     * 1:程序在前台运行
     * 2:程序在后台运行
     * 3:程序未启动
     * 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
     */
    public int getAppSatus(Context context, String pageName) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(20);

        for (ActivityManager.RunningTaskInfo info : list) {
//                还可以通过下面代码判断程序的某个页面是否在运行
            //  info.topActivity.getClassName().equals(activityName);
            LogUtils.e("getAppSatus ",info.baseActivity.getShortClassName());
            LogUtils.e("getAppSatus ",info.baseActivity.getPackageName());
            LogUtils.e("getAppSatus ",info.topActivity.getShortClassName());
//            该任务正在运行Activity数量，
            LogUtils.e("getAppSatus ","numRunning:"+info.numRunning);
            LogUtils.e("getAppSatus ","numActivities:"+info.numActivities);
        }

        //判断程序是否在栈顶
        if (list.get(0).topActivity.getPackageName().equals(pageName)) {
            return 1;
        } else {
            //判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : list) {
//                还可以通过下面代码判断程序的某个页面是否在运行
                //  info.topActivity.getClassName().equals(activityName);
                if (info.topActivity.getPackageName().equals(pageName)) {
                    return 2;
                }
            }
            return 3;//栈里找不到，返回3
        }
    }

    private void displayBriefMemory() {

        final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();

        activityManager.getMemoryInfo(info);

        LogUtils.i(TAG,"系统剩余内存:"+(info.availMem >> 10)+"k");

        LogUtils.i(TAG,"系统是否处于低内存运行："+info.lowMemory);

        LogUtils.i(TAG,"总的："+(info.totalMem>>10));

        LogUtils.i(TAG,"当系统剩余内存低于"+info.threshold+"时就看成低内存运行");

    }
    public void lock(){
        KeyguardManager km = (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
        if (km.inKeyguardRestrictedInputMode()) {//如果锁屏就解锁
            KeyguardManager.KeyguardLock keyguardLock = km.newKeyguardLock("");
            keyguardLock.disableKeyguard();
        }
    }

}
