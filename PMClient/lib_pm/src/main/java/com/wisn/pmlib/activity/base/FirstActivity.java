package com.wisn.pmlib.activity.base;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.alarm.AlarmActivity;
import com.wisn.pmlib.activity.intent.TestIntentActivity;
import com.wisn.pmlib.activity.other.BlurActivity;
import com.wisn.pmlib.activity.pm.MainActivity;
import com.wisn.pmlib.activity.ssl.HttpUrlConnectionSSLActivity;
import com.wisn.pmlib.activity.ssl.SSLActivity;
import com.wisn.pmlib.activity.test.TestWso2RestActivity;
import com.wisn.pmlib.activity.test.TextViewActivity;
import com.wisn.pmlib.activity.tips.SnackbarActivity;
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
//        startActivity(new Intent(FirstActivity.this,TestJsonActivity.class));
       // startActivity(new Intent(FirstActivity.this,DownloadActivity.class));
//        startActivity(new Intent(FirstActivity.this,DownloadActivity.class));
//        startActivity(new Intent(FirstActivity.this,MainActivity.class));
//        startActivity(new Intent(FirstActivity.this,UnZipActivity.class));
        //startActivity(new Intent(FirstActivity.this,TestIntentActivity.class));
        //startActivity(new Intent(this, AlarmActivity.class));
//        startActivity(new Intent(this, BlurActivity.class));
//        startActivity(new Intent(this, SnackbarActivity.class));
//        startActivity(new Intent(this, TestWso2RestActivity.class));
//        startActivity(new Intent(this, HttpUrlConnectionSSLActivity.class));
        startActivity(new Intent(this, TextViewActivity.class));
        this.finish();
        final TextView tv= (TextView) findViewById(R.id.first);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ApplicationInfo appInfo = FirstActivity.this.getPackageManager()
                                                                .getApplicationInfo(getPackageName(),
                                                                                    PackageManager.GET_META_DATA);
                    String msg=appInfo.metaData.getString("CName");
                    Log.d(TAG, " CName == " + msg );
                    tv.setText(" CName == " + msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
