package com.wisn.pmlib.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by wisn on 2017/3/9.
 */

public class ZipUtils {
    private static final String TAG = "ZipUtils";
    private static boolean isZip = false;

    public interface UnZipListener {
        void unZipProgress(int count, int sum);

        void finish(long totalTime);

        void start(int sum);

        void unZipCurrent(int current);
    }

    public interface ZipListener {

        void finish(long totalTime);

        void start();

        void zipCurrent(int current);
    }

    public static void resetUnZip() {
        isZip = false;
    }

    /**
     * 解压Assets中的文件
     *
     * @param context         上下文对象
     * @param assetName       压缩包文件名
     * @param outputDirectory 输出目录
     *
     * @throws IOException
     */
    public static void unZipFileFromAssets(final Context context,
                                           final String assetName,
                                           final String outputDirectory,
                                           final UnZipListener unZipListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isZip) {
                    LogUtils.d(TAG, "已经解压过了");
                    return;
                }
                isZip = true;
                long startTime = System.currentTimeMillis();
                ZipInputStream zipInputStream = null;
                try {
                    //创建解压目标目录
                    File file = new File(outputDirectory);
                    //如果目标目录不存在，则创建
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    InputStream inputStream = null;
                    //打开压缩文件
                    inputStream = context.getAssets().open(assetName);
                    zipInputStream = new ZipInputStream(inputStream);
                    int sum = inputStream.available();
                    if (unZipListener != null) {
                        unZipListener.start(sum);
                    }
                    //读取一个进入点
                    ZipEntry zipEntry = zipInputStream.getNextEntry();
                    //使用1Mbuffer
                    byte[] buffer = new byte[1024 * 512];
                    //解压时字节计数
                    int count = 0, counttemp = 0, current = 0;
                    //如果进入点为空说明已经遍历完所有压缩包中文件和目录
                    while (zipEntry != null) {
                        //如果是一个目录
                        if (zipEntry.isDirectory()) {
                            file = new File(outputDirectory + File.separator + zipEntry.getName());
                            file.mkdir();
                        } else {
                            current++;
                            //如果是文件
                            file = new File(outputDirectory + File.separator
                                            + zipEntry.getName());
                            //创建该文件
                            file.createNewFile();
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            while ((count = zipInputStream.read(buffer)) != -1) {
                                fileOutputStream.write(buffer, 0, count);
                                if (count > 0) {
                                    counttemp = counttemp + count;
                                    if (counttemp % 64 == 0) {
                                        if (unZipListener != null) {
                                            unZipListener.unZipProgress(counttemp, sum);
                                        }
                                    }
                                }
                            }
                            fileOutputStream.close();
                        }
                        if (unZipListener != null) {
                            unZipListener.unZipProgress(counttemp, sum);
                            unZipListener.unZipCurrent(current);
                        }
                        zipEntry = zipInputStream.getNextEntry();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (zipInputStream != null) {
                            zipInputStream.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (unZipListener != null) {
                            unZipListener.finish(System.currentTimeMillis() - startTime);
                        }
                    }
                }
            }
        }).start();
    }

    /**
     * 解压文件
     *
     * @param zipFilePath     压缩包文件名
     * @param outputDirectory 输出目录
     *
     * @throws IOException
     */
    public static void unZipFile(final String zipFilePath, final String outputDirectory,
                                 final UnZipListener unZipListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                if (isZip) {
//                    LogUtils.d(TAG, "已经解压过了");
//                    return;
//                }
//                isZip = true;
                long startTime = System.currentTimeMillis();
                ZipInputStream zipInputStream = null;
                try {
                    //创建解压目标目录
                    File file = new File(outputDirectory);
                    //如果目标目录不存在，则创建
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    InputStream inputStream = null;
                    //打开压缩文件
                    inputStream = new FileInputStream(zipFilePath);
                    zipInputStream = new ZipInputStream(inputStream);
                    int sum = inputStream.available();
                    if (unZipListener != null) {
                        unZipListener.start(sum);
                    }
                    //读取一个进入点
                    ZipEntry zipEntry = zipInputStream.getNextEntry();
                    //使用1Mbuffer
                    byte[] buffer = new byte[1024 * 512];
                    //解压时字节计数
                    int count = 0, counttemp = 0, current = 0;
                    //如果进入点为空说明已经遍历完所有压缩包中文件和目录
                    while (zipEntry != null) {
                        //如果是一个目录
                        if (zipEntry.isDirectory()) {
                            file = new File(outputDirectory + File.separator + zipEntry.getName());
                            file.mkdirs();
                        } else {
                            current++;
                            //如果是文件
                            file = new File(outputDirectory + File.separator
                                            + zipEntry.getName());
                            Log.e(TAG,"file:"+outputDirectory + File.separator
                                      + zipEntry.getName());
                            FileOutputStream fileOutputStream=null;
                            try {
                                //创建该文件
                                file.createNewFile();
                                file.setWritable(Boolean.TRUE);
                                fileOutputStream= new FileOutputStream(file);
                                while ((count = zipInputStream.read(buffer)) != -1) {
                                    fileOutputStream.write(buffer, 0, count);
                                    if (count > 0) {
                                        counttemp = counttemp + count;
                                        if (counttemp % 64 == 0) {
                                            if (unZipListener != null) {
                                                unZipListener.unZipProgress(counttemp, sum);
                                            }
                                        }
                                    }
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }finally {
                                if(fileOutputStream!=null)
                                fileOutputStream.close();
                            }

                        }
                        if (unZipListener != null) {
                            unZipListener.unZipProgress(counttemp, sum);
                            unZipListener.unZipCurrent(current);
                        }
                        zipEntry = zipInputStream.getNextEntry();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (zipInputStream != null) {
                            zipInputStream.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (unZipListener != null) {
                            unZipListener.finish(System.currentTimeMillis() - startTime);
                        }
                    }
                }
            }
        }).start();
    }

    //////////**********压缩************/////////////
    public static int current;

    /**
     * @param zipFileName 目标压缩文件
     * @param zipFilePath 输入的文件
     *
     * @throws Exception
     */
    public static void zipFile(final String zipFileName,
                               final String zipFilePath,
                               final ZipListener zipListener) throws Exception {
        current = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                if (zipListener != null) {
                    zipListener.start();
                }
                try {
                    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                            zipFileName));
                    if (zipListener != null) {
                        zipListener.zipCurrent(0);
                    }
                    if (zipFilePath == null) {
                        return;
                    }
                    BufferedOutputStream bo = new BufferedOutputStream(out);
                    File zipFile = new File(zipFilePath);
                    if (zipFile != null) {
                        zip(out, zipFile, zipFile.getName(), bo, zipListener);
                    }
                    bo.close();
                    out.close(); // 输出流关闭
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (zipListener != null) {
                        zipListener.finish(System.currentTimeMillis() - startTime);
                    }
                }
            }
        }).start();
    }

    private static void zip(ZipOutputStream out, File f, String base,
                            BufferedOutputStream bo, final ZipListener zipListener) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            if (fl == null) {
                return;
            }
            if (fl.length == 0) {
                out.putNextEntry(new ZipEntry(base + File.separator)); // 创建zip压缩进入点base
            }
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + File.separator + fl[i].getName(), bo, zipListener); // 递归遍历子文件夹
            }
            if (zipListener != null) {
                current++;
                zipListener.zipCurrent(current);
            }
        } else {
            out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
            System.out.println(base);
            FileInputStream in = new FileInputStream(f);
            BufferedInputStream bi = new BufferedInputStream(in);
            int b;
            while ((b = bi.read()) != -1) {
                bo.write(b); // 将字节流写入当前zip目录
            }
            bi.close();
            in.close(); // 输入流关闭
        }
    }
}
