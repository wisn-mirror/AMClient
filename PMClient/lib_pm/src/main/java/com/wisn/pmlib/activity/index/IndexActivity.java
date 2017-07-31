package com.wisn.pmlib.activity.index;

import android.os.Bundle;
import android.widget.TextView;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;

/**
 * @author Wisn
 * @time 2017/7/31 21:44
 */


public class IndexActivity extends BaseActivity {

    private TextView indexTextView;
    private QuickIndex quickIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickindex);
        indexTextView = (TextView) findViewById(R.id.index);
        quickIndex = (QuickIndex) findViewById(R.id.quickindex);
        quickIndex.setOnIndexChangeListener(new QuickIndex.OnIndexChangeListener() {
            @Override
            public void startTouch() {

            }

            @Override
            public void change(String str, int index) {
                indexTextView.setText(str+" index:"+index);
            }

            @Override
            public void release() {

            }
        });
    }
}
