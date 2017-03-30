package com.wisn.pmlib.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.wisn.pmlib.R;
import com.wisn.pmlib.log.log;


public abstract class BaseActivity extends AppCompatActivity {
    public  String  TAG= getClass().getName();
    private long firstTouch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        log.d(TAG, "onCreate");
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
    }
}
