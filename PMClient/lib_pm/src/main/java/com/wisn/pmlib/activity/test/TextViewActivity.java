package com.wisn.pmlib.activity.test;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;
import com.wisn.pmlib.utils.LogUtils;
import com.wisn.pmlib.utils.ZipUtils;

/**
 * Created by wisn on 2017/5/5.
 */

public class TextViewActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textview);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        /*agreementLink(textView2,"hellfdsafds",false,false);
        agreementLink(textView2,"43215312431",false,true);
        agreementLink(textView2,"hellrewqrewqfdsafds",false,false);
        agreementLink(textView2,"215193432141546789",false,true);*/
        StringBuilder sb = new StringBuilder();
        getFormat(sb, "hello", "world", false);
        getFormat(sb, "hello", 432, false);
        getFormat(sb, "hello", 432143214321l, false);
        getFormat(sb, "hello", 993.22, false);
        getFormat(sb, "hello", true, false);
        textView2.setText(sb.toString());
        try {
          /*  ZipUtils.zipFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/text.zip", new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download"),
                             new ZipUtils.ZipListener() {
                                        @Override
                                        public void finish(long totalTime) {

                                        }

                                        @Override
                                        public void start() {

                                        }

                                        @Override
                                        public void zipCurrent(int current) {

                                        }
                                    });*/
            ZipUtils.unZipFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/text.zip",
                               Environment.getExternalStorageDirectory().getAbsolutePath() + "/textaaaa",
                               new ZipUtils.UnZipListener() {
                                   @Override
                                   public void unZipProgress(int count, int sum) {
                                       LogUtils.e(TAG,count+" count"+sum+" sum");

                                   }

                                   @Override
                                   public void finish(long totalTime) {
                                       LogUtils.e(TAG,totalTime+" time");
                                   }

                                   @Override
                                   public void start(int sum) {
                                       LogUtils.e(TAG,sum+" sum");

                                   }

                                   @Override
                                   public void unZipCurrent(int current) {
                                       LogUtils.e(TAG,current+" current");

                                   }
                               });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StringBuilder getFormat(StringBuilder sb, String key, Object value, boolean isEnd) {
        if (value instanceof String) {
            sb.append("\"" + key + "\":\"" + ((String) value).toString() + "\"");
        } else {
            sb.append("\"" + key + "\":" + value);
        }
        if (!isEnd) {
            sb.append(",");
        }
        return sb;
    }

    public void agreementLink(TextView textView, String message, boolean isClear, boolean isWarn) {
        if (isWarn) {
            int historyLength = textView.getText().toString().length();
            SpannableStringBuilder spannable = new SpannableStringBuilder(
                    message);// 用于可变字符串
            ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
            spannable.setSpan(span, 0, message.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (!isClear) {
                textView.append(spannable);
            } else {
                textView.setText(spannable);
            }
        } else {
            if (!isClear) {
                textView.append(message);
            } else {
                textView.setText(message);
            }
        }

    }
}
