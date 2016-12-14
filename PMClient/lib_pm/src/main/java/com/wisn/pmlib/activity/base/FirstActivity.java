package com.wisn.pmlib.activity.base;

import android.content.Intent;
import android.os.Bundle;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.downloader.DownloadActivity;
import com.wisn.pmlib.activity.testjson.TestJson;

/**
 * Created by wisn on 2016/12/8.
 */

public class FirstActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
//        startActivity(new Intent(FirstActivity.this,MainActivity.class));
        startActivity(new Intent(FirstActivity.this,TestJson.class));
//        startActivity(new Intent(FirstActivity.this,DownloadActivity.class));
    }
}
