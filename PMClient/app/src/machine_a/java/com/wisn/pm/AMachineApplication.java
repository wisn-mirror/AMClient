package com.wisn.pm;

import android.util.Log;

import com.wisn.pmlib.application.MApplication;

/**
 * Created by wisn on 2017/2/22.
 */

public class AMachineApplication extends MApplication{
    public  String TAG="com.wisn.pm.AMachineApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "com.wisn.pm.AMachineApplication");
    }
}
