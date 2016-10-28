package com.wisn.pmlib.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wisn.pmlib.R;
import com.wisn.pmlib.log.log;


public abstract class BaseActivity extends AppCompatActivity {
    public  String  TAG= getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        log.d(TAG, "onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.d(TAG,"onDestroy");
    }
}
