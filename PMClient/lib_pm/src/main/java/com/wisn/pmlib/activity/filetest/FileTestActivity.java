package com.wisn.pmlib.activity.filetest;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wisn.pmlib.R;
import com.wisn.pmlib.utils.FileUtils;
import com.wisn.pmlib.utils.ZipUtil;
import com.wisn.pmlib.utils.ZipUtils;

import java.io.File;
import java.io.IOException;

public class FileTestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_test);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == button1) {

/*
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                       FileUtils.writeStringAsFileContent(new File(Environment.getExternalStorageDirectory() +
                                                  File.separator +
                                                  "content1.txt" ),
                                                  "client.truststore fdsahfdsafdsa",true);*/
                        /*ZipUtil.upZipFile(new File(Environment.getExternalStorageDirectory() +
                                                   File.separator +
                                                   "dd"+File.separator+"them"+
                                                   File.separator +
                                                   "them.skin"),Environment.getExternalStorageDirectory() +
                                                                File.separator +
                                                                "dd" +
                                                                File.separator +
                                                                "the");*/
                       /* ZipUtil.zipFiles(new File(Environment.getExternalStorageDirectory() +
                                                      File.separator +"filetest").list(),new ZipUtil.ZipListener(){

                            @Override
                            public void zipStart() {

                            }

                            @Override
                            public void zipProgress(int zipProgress) {

                            }

                            @Override
                            public void zipFinish() {

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                    /* try {
                        FileUtils.writeByteToFile(new File(Environment.getExternalStorageDirectory() +
                                                  File.separator +
                                                  "content.txt" ),
                                                  "client.truststore fdsahfdsafdsa".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    FileUtils.copyAssetsFileToFile(FileTestActivity.this, "client.truststore",
                                              Environment.getExternalStorageDirectory() +
                                              File.separator +
                                              "assetsFile" ,
                                              "client.truststore");
                    FileUtils.copyAssetsDirToDir(FileTestActivity.this, "ddd",
                                                   Environment.getExternalStorageDirectory() +
                                                   File.separator +
                                                   "dd");
                }
            }).start();*/
        } else if (v == button2) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                       /*byte[] content= FileUtils.readFileContent(new File(Environment.getExternalStorageDirectory() +
                                                           File.separator +
                                                           "content.txt" ));
                        Log.e("content:",new String(content));
                                                           */
                       String  content= FileUtils.readFileContentAsString(new File(Environment.getExternalStorageDirectory() +
                                                           File.separator +
                                                           "content1.txt" ));
                        Log.e("content:",content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                 /*   FileUtils.copyDir(Environment.getExternalStorageDirectory() +
                                      File.separator +"filetest",
                                              Environment.getExternalStorageDirectory() +
                                              File.separator +
                                              "filetest2");*/
                }
            }).start();
        } else if (v == button3) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FileUtils.copyDirUnIncludeSubDirs(Environment.getExternalStorageDirectory() +
                                      File.separator +"filetest",
                                      Environment.getExternalStorageDirectory() +
                                      File.separator +
                                      "filetest3");
                }
            }).start();

        } else if (v == button4) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FileUtils.copyDirByFileType(Environment.getExternalStorageDirectory() +
                                                      File.separator +"filetest",
                                                      Environment.getExternalStorageDirectory() +
                                                      File.separator +
                                                      "filetest4",".md");
                }
            }).start();
        }
    }
}
