package com.wisn.pmlib.activity.unzip;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;
import com.wisn.pmlib.utils.UnzipFromAssets;

import java.io.File;

/**
 * Created by wisn on 2017/3/1.
 */

public class UnZipActivity extends BaseActivity {

    private TextView mTestResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unzip);
        mTestResult = (TextView) findViewById(R.id.testResult);
        final UnzipFromAssets unzipFromAssets = new UnzipFromAssets();
        String outputPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File folder = new File(outputPath + File.separator + "apk_my");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        unzipFromAssets.unZip(UnZipActivity.this,
                              "package.zip",
                              folder.getAbsolutePath(),
                              new UnzipFromAssets.UnZipListener() {
                                  @Override
                                  public void unZipProgress(int count, int sum) {
                                      updateView("解压进度：" + count + "  sum：" + sum + "\n");
                                  }

                                  @Override
                                  public void finish(long totalTime) {
                                      updateView("解压完成！共耗时：" + totalTime + "\n");
                                  }

                                  @Override
                                  public void start() {
                                      updateView("开始解压！\n");
                                  }
                              });
    }

    public void updateView(final String test) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTestResult.append(test);

            }
        });
    }
}
