package com.wisn.pmlib.activity.jni;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wisn.pmlib.R;


/**
 * Created by wisn on 2017/3/22.
 */

public class JniActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
    }
}
