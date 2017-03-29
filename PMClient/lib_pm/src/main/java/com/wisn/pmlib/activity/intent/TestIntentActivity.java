package com.wisn.pmlib.activity.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.wisn.pmlib.R;

/**
 * Created by wisn on 2017/3/10.
 * 通信方式
 * 1 intent
 * 2 aidl
 */

public class TestIntentActivity extends Activity {
    Button aidl,sendIntent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        aidl= (Button) findViewById(R.id.aidl);
        sendIntent= (Button) findViewById(R.id.sendIntent);
        aidl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sendIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送Intent
                Intent intent=new Intent("InstallApp_Success");
                intent.putExtra("package_name",TestIntentActivity.this.getPackageName());
                TestIntentActivity.this.sendBroadcast(intent);
            }
        });
    }
}
