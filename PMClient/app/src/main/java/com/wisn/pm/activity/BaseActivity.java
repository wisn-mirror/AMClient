package com.wisn.pm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wisn.pm.R;
import com.wisn.pm.log.log;

public abstract class BaseActivity extends AppCompatActivity {
    public  String  TAG= getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        log.d(TAG,"onCreate");
        /*for(int i=0;i<10000;i++){


        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.d(TAG,"onDestroy");
    }
}
