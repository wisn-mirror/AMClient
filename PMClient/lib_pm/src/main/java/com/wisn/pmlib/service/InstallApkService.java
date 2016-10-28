package com.wisn.pmlib.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.wisn.pmlib.utils.ApkOperate;
import com.wisn.pmlib.utils.FileUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Created by 00301945 on 2016/9/13.
 */

public class InstallApkService  extends Service {
    public  boolean   RunFlg=true;
    private String downloadPath;
    private File apkdir;
    File backupDir=null;
    ApkOperate apkutils;
    ApkOperate.ApkInformation apkFileInformation;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        apkutils=new ApkOperate();
        startInstallApkThread();
    }


    /**
     * 开启安装监听的线程
     * 监听文件夹的目录在
     * 在内存卡的apk文件夹中
     */
    public  void  startInstallApkThread(){
        downloadPath = Environment.getExternalStorageDirectory().getAbsoluteFile().getPath()+File.separator+"apk";
        log(downloadPath);
        apkdir = new File(downloadPath);
        if(!apkdir.exists()){
            apkdir.mkdir();
            log("文件创建成功"+ apkdir.getAbsolutePath());
        }
        new  Thread(new Runnable() {
            @Override
            public void run() {
                while(RunFlg){
                    //每5秒检测一次
                    SystemClock.sleep(5000);
                    ergodicFile(apkdir  ,false);
                    SystemClock.sleep(5000);
                    //检测apk是否安装，是否运行
                    if(apkFileInformation!=null){
                         if(apkutils.isInstalled(InstallApkService.this,apkFileInformation.name)){
                             //已经安装
                             log("apk已经安装");
                             if(apkutils.isRunning(InstallApkService.this,apkFileInformation.name)){
                                 //已经运行了
                                 log("apk已经运行了");
                             }else{
                                 log("apk没有运行，启动apk正常运行");
                                 apkutils.openApp(InstallApkService.this,apkFileInformation.name);
                             }
                         }else{
                             //启动安装
                             log("apk没有安装，启动备份文件夹安装");
                             installApkRightVersion(apkdir.getAbsolutePath());
                         }
                    }
                }
            }
        }).start();
    }

    /**
     * 遍历文件夹
     * @param apkdir  遍历的文件夹
     * @param isBackup  是否是备份文件夹
     */
    public  void  ergodicFile(File  apkdir  ,boolean isBackup){
        // 检测文件是否存在，如果存在  安装，如果不存在，跳过
        File[] files = apkdir.listFiles();
        if(files!=null){
            if(files.length>0){
                for(File  file:files){
                    try {
                        if(file!=null){
                            if("apk".equalsIgnoreCase(file.getName().substring(file.getName().lastIndexOf(".")+1))){
                                if(isBackup){
                                    //是安装备份文件夹
                                    if(runRootCommand("system/bin/pm install  -r "+file.getAbsolutePath())){
                                       //安装备份文件成功
                                    }else{
                                        //安装备份失败
                                    }
                                }else{
                                    //安装正常的下载文件夹
                                    if(runRootCommand("system/bin/pm install  -r "+file.getAbsolutePath())){
                                        //  安装成功 备份文件
                                        backupAPKVersion(apkdir.getAbsolutePath(),file.getAbsolutePath(),file.getName());
                                        apkFileInformation = apkutils.getApkFileInformation(getPackageManager(), file.getAbsolutePath());
                                        file.delete();
                                    }else{
                                        //安装失败
                                        installApkRightVersion(apkdir.getAbsolutePath());
                                    }
                                }

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public  void backupAPKVersion(String  path,String  apkPath,String  filename){
        backupDir = new File(path+File.separator+"backup");
        if(!backupDir.exists()){
            backupDir.mkdir();
            log("备份文件创建成功"+ backupDir.getAbsolutePath());
        }
        FileUtils.CopySdcardFile(apkPath, backupDir.getAbsolutePath() + File.separator + filename);
    }
    /**
     * 安装正确的版本
     */
    public  void installApkRightVersion(String  path){
        //安装失败  检测版本是否存在，如果存在，启动，如果不存在，安装上一个稳定版本，并启动
        try {
            backupDir = new File(path+File.separator+"backup");
            ergodicFile(backupDir,true );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 请求ROOT权限后执行命令
     * @param cmd   (pm install -r *.apk)
     * @return
     */
    public  boolean runRootCommand(String cmd) {
        Process process = null;
        DataOutputStream os = null;
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd+"\n");
            os.writeBytes("exit\n");
            show("运行成功");
            log("运行成功");
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            sb = new StringBuilder();
            String temp=null;
            while((temp = br.readLine())!=null){
                sb.append(temp+"\n");
                if("Success".equalsIgnoreCase(temp)){
                    log("------Success----"+sb.toString());
                    show("------Success----"+sb.toString());
                    return true;
                }
            }
            process.waitFor();
        } catch (Exception e) {
            log( "异常："+e.getMessage());
            show("异常："+e.getMessage());
        }
        finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
                if(br!=null){
                    br.close();
                }
                process.destroy();
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * toast
     * @param content
     */
    public void  show(final String   content  ){
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,content,Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    /**
     * log
     * @param content
     */
    public  void  log(String  content ){
        Log.e("installapk",content);
    }

}
