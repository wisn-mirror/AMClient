package com.wisn.pmlib.activity.unzip;

import android.os.Bundle;
import android.os.Environment;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;
import com.wisn.pmlib.utils.UnzipFromAssets;

import java.io.File;

/**
 * Created by wisn on 2017/3/1.
 */

public class UnZipActivity extends BaseActivity {
    int countall =0;
    private TextView mTestResult;
    ScrollView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unzip);
        mTestResult = (TextView) findViewById(R.id.testResult);
        sv= (ScrollView)findViewById(R.id.scroll_info);//获取scrollView组件
        String path= unZip();
        if(path!=null)
        installAPP(path);
    }

    /**
     * 安装app
     */
    public void installAPP(String path){

    }

    /**
     * 解压
     * @return
     */
    public String unZip(){
       try{
           UnzipFromAssets unzipFromAssets = new UnzipFromAssets();
           String outputPath = Environment.getExternalStorageDirectory().getAbsolutePath();
           File folder = new File(outputPath + File.separator + "apk_my");
           if (!folder.exists()) {
               folder.mkdirs();
           }
           unzipFromAssets.unZipFile(UnZipActivity.this,
                                     "package.zip",
                                     folder.getAbsolutePath(),
                                     new UnzipFromAssets.UnZipListener() {

                                     @Override
                                     public void unZipProgress(int count, int sum) {
                                         updateView("解压进度：" + count + "  sum：" + sum + "\n");
                                         countall++;
                                         sv.fullScroll(ScrollView.FOCUS_DOWN);//滚动到底部
                                     }

                                     @Override
                                     public void finish(long totalTime) {
                                         updateView("解压完成！共耗时：" + totalTime + "\n");
                                         updateView(" xxxx"+countall);
                                     }

                                     @Override
                                     public void start(int sum) {
                                         updateView("开始解压！\n");
                                     }

                                     @Override
                                     public void unZipCurrent(int current) {
                                         updateView("解压第"+current+"个");
                                     }

                                 });
           return folder.getAbsolutePath();
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
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
