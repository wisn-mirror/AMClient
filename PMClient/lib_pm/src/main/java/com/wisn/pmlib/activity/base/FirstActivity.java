package com.wisn.pmlib.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.alarm.AlarmActivity;
import com.wisn.pmlib.activity.pm.MainActivity;
import com.wisn.pmlib.activity.ssl.SSLActivity;
import com.wisn.pmlib.activity.unzip.UnZipActivity;
import com.wisn.pmlib.receiver.AlarmRecever;

/**
 * Created by wisn on 2016/12/8.
 */

public class FirstActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
//        startActivity(new Intent(FirstActivity.this,com.wisn.pmlib.activity.downloads.DownloadActivity.class));
//        startActivity(new Intent(FirstActivity.this,TestJson.class));
       // startActivity(new Intent(FirstActivity.this,DownloadActivity.class));
//        startActivity(new Intent(FirstActivity.this,DownloadActivity.class));
      //  startActivity(new Intent(FirstActivity.this,MainActivity.class));
        startActivity(new Intent(FirstActivity.this,UnZipActivity.class));
        this.finish();
       /* findViewById(R.id.first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("FirstActivity",System.currentTimeMillis()+"");
            }
        });*/
//        startActivity(new Intent(this, AlarmActivity.class));
    }
}
