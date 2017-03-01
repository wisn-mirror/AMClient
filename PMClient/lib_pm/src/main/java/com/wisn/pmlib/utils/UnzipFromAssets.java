package com.wisn.pmlib.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by wisn on 2017/3/1.
 */

public class UnzipFromAssets {
    public  interface UnZipListener{
        void unZipProgress(int count ,int sum);
        void finish(long totalTime);
        void start();
    }
    /**
     * 解压Assets中的文件
     * @param context 上下文对象
     * @param assetName 压缩包文件名
     * @param outputDirectory 输出目录
     * @throws IOException
     */
    public  void unZip(final Context context, final String assetName, final String outputDirectory,
                       final UnZipListener unZipListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime=System.currentTimeMillis();
                ZipInputStream zipInputStream=null;
                try {
                    //创建解压目标目录
                    File file = new File(outputDirectory);
                    //如果目标目录不存在，则创建
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    InputStream inputStream = null;
                    if (unZipListener != null) {
                        unZipListener.start();
                    }
                    //打开压缩文件
                    inputStream = context.getAssets().open(assetName);
                    zipInputStream = new ZipInputStream(inputStream);
                    int sum=inputStream.available();
                    //读取一个进入点
                    ZipEntry zipEntry = zipInputStream.getNextEntry();
                    //使用1Mbuffer
//            byte[] buffer = new byte[1024 * 1024];
                    byte[] buffer = new byte[1024 * 512];
                    //解压时字节计数
                    int count = 0;
                    int counttemp=0;
                    //如果进入点为空说明已经遍历完所有压缩包中文件和目录
                    while (zipEntry != null) {
                        //如果是一个目录
                        if (zipEntry.isDirectory()) {
                            //String name = zipEntry.getName();
                            //name = name.substring(0, name.length() - 1);
                            file = new File(outputDirectory + File.separator + zipEntry.getName());
                            file.mkdir();
                        } else {
                            //如果是文件
                            file = new File(outputDirectory + File.separator
                                            + zipEntry.getName());
                            //创建该文件
                            file.createNewFile();
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            while ((count = zipInputStream.read(buffer)) > 0) {
                                fileOutputStream.write(buffer, 0, count);
                                if(count>0){
                                    counttemp=counttemp+count;
                                }
                            }
                            fileOutputStream.close();
                        }
                        if (unZipListener != null) {
                            unZipListener.unZipProgress(counttemp, sum);
                        }
                        //定位到下一个文件入口
                        zipEntry = zipInputStream.getNextEntry();
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(zipInputStream!=null) {
                            zipInputStream.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if (unZipListener != null) {
                            unZipListener.finish(System.currentTimeMillis()-startTime);
                        }
                    }
                }
            }
        }).start();

    }

}
