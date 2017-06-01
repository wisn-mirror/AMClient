package com.wisn.pmlib.activity.base;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.wisn.pmlib.R;
import com.wisn.pmlib.application.MApplication;
import com.wisn.pmlib.log.log;


public abstract class BaseActivity extends AppCompatActivity {
    public  String  TAG= getClass().getName();
    private long firstTouch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        log.d(TAG, "onCreate");
        MApplication.getInstance().addActivity(this);
        getMetaData();
    }

    private void getMetaData() {
        ApplicationInfo appInfo = null;
        try {
            appInfo = this.getPackageManager()
                              .getApplicationInfo(getPackageName(),
                                                  PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String msg=appInfo.metaData.getString("CName");
        Log.d(TAG, " CName == " + msg );
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (System.currentTimeMillis() - firstTouch < 2000) {
                return true;
            } else {
                firstTouch = System.currentTimeMillis();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        return super.dispatchGenericMotionEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.d(TAG,"onDestroy");
        MApplication.getInstance().finishActivity(this);

    }
}
