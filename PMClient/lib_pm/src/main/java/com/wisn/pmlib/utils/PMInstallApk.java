package com.wisn.pmlib.utils;

import android.content.Context;
import android.os.SystemClock;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;


/**
 * <b>Create Date:</b> 2016/10/19<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class PMInstallApk {
    private static final String TAG ="PMInstallApk";
    private Context context;
    private File apkdir;
    File backupDir = null;
    ApkOperate apkutils = null;
    ApkOperate.ApkInformation apkFileInformation;

    public PMInstallApk() {
        apkutils = new ApkOperate();
    }

    /**
     * 开启安装监听的线程
     * 监听文件夹的目录在
     * 在内存卡的apk文件夹中
     * @param directory
     */
    public void startInstallApkThread(final String directory, final String appname) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        LogUtils.d(TAG ,"开始安装apk:" + directory);
                        apkdir = new File(directory);
                        if (!apkdir.exists()) {
                            apkdir.mkdir();
                            LogUtils.i(TAG+ "文件创建成功" + apkdir.getAbsolutePath());
                            return;
                        }
                        //每5秒检测一次
                        SystemClock.sleep(3000);
                        ergodicFile(apkdir,appname, false);
                        SystemClock.sleep(3000);
                        //检测apk是否安装，是否运行
                        if (apkFileInformation != null) {
                            if (apkutils.isInstalled(context, apkFileInformation.name)) {
                                //已经安装
                                LogUtils.d(TAG, "apk已经安装");
                                if (apkutils.isRunning(context, apkFileInformation.name)) {
                                    //已经运行了
                                    LogUtils.i(TAG, "apk已经运行了");
                                } else {
                                    LogUtils.i(TAG, "apk没有运行，启动apk正常运行");
                                    apkutils.openApp(context, apkFileInformation.name);
                                }
                            } else {
                                //启动安装
                                LogUtils.i(TAG, "apk没有安装，启动备份文件夹安装");
                                //  installApkRightVersion(apkdir.getAbsolutePath());
                            }
                        }
                    } finally {
                        context = null;
                        apkdir = null;
                        backupDir = null;
                        apkutils = null;
                        apkFileInformation = null;
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(TAG, "安装命令异常");
        }
    }

    /**
     * 遍历文件夹
     *
     * @param apkdir   遍历的文件夹
     * @param isBackup 是否是备份文件夹
     */
    public void ergodicFile(File apkdir, String appanme, boolean isBackup) {
        File apkFile=new File(apkdir, appanme);
        if(apkFile==null) {
            //Todo 安装失败
            return ;
        }
        boolean isAgent = false;
        try {
            apkFileInformation =
                    apkutils.getApkFileInformation(context.getPackageManager(),
                                                   apkFile.getAbsolutePath());
        } catch (Exception e) {
            LogUtils.e(TAG, e.toString());
        }
        if (apkFileInformation != null) {
            if (context.getPackageName().equalsIgnoreCase(apkFileInformation.name)) {
                isAgent = true;
            }
        }
        //是否安装备份
        if (isBackup) {
            //安装备份文件夹
            if (runRootCommand("system/bin/pm install  -r " + apkFile.getAbsolutePath(),
                               isAgent)) {
                //安装备份文件成功
                LogUtils.i(TAG,"安装备份文件成功");
            } else {
                //安装备份失败
                LogUtils.i(TAG,"安装备份失败");
            }

        } else {
            //安装正常的下载文件夹
            if (runRootCommand("system/bin/pm install  -r " + apkFile.getAbsolutePath(),
                               isAgent)) {
                //安装成功
                //安装成功 开始 备份文件
                backupApkVersion(apkdir.getAbsolutePath(),
                                 apkFile.getAbsolutePath(),apkFileInformation.name);
                LogUtils.i(TAG,"备份成功");
            } else {
                //安装失败 开始安装备份版本
                installApkRightVersion(apkdir.getAbsolutePath(),apkFileInformation.name);
                //安装失败
            }
            if(apkFile!=null){
                apkFile.delete();
                LogUtils.d(TAG,"文件删除成功！");
            }

        }
    }

    /**
     * 备份安装成功后的apk
     * @param path
     * @param apkPath
     */
    private  void backupApkVersion(String path, String apkPath, String apkFileInformationName) {
        backupDir = new File(path + File.separator + "backup" + File.separator + apkFileInformationName);
        if (!backupDir.exists()) {
            backupDir.mkdir();
            LogUtils.i(TAG, "备份文件创建成功" + backupDir.getAbsolutePath());
        }else{
            LogUtils.i(TAG,apkFileInformationName+"备份文件创建失败");
        }
        FileUtils.CopySdcardFile(apkPath, backupDir.getAbsolutePath() + File.separator + "update.apk");
    }

    /**
     * 安装正确的版本
     * @param path
     */
    private  void installApkRightVersion(String path, String apkFileInformationName) {
        //安装失败  检测版本是否存在，如果存在，启动，如果不存在，安装上一个稳定版本，并启动
        try {
            backupDir = new File(path + File.separator + "backup" + File.separator + apkFileInformationName);
            if(backupDir.exists()){
                ergodicFile(backupDir,"update.apk", true);
            }else{
                LogUtils.d(TAG,apkFileInformationName+"备份不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.d(TAG,"安装备份版本异常:"+e.getMessage());
        }
    }

    /**
     * 请求ROOT权限后执行命令
     * @param cmd
     * @param isAgent
     * @return
     */
    public boolean runRootCommand(String cmd, boolean isAgent) {
        Process process = null;
        DataOutputStream os = null;
        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            if (isAgent) {
                //如果是Agent本身要通过命令行的方式来启动
                os.writeBytes(cmd +
                              "\n" +
                              "  am start -n  org.wso2.emm.agent/org.wso2.emm.agent.AgentReceptionActivity" +
                              "\n");
            } else {
                //其他的app要通过app安装发送广播的方式启动
                os.writeBytes(cmd + "\n");
            }
            os.writeBytes("exit\n");
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            sb = new StringBuilder();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sb.append(temp + "\n");
                if ("Success".equalsIgnoreCase(temp)) {
                    LogUtils.i(TAG, cmd + "------命令运行结果----" + sb.toString());
                    return true;
                }
            }
            process.waitFor();
        } catch (Exception e) {
            LogUtils.e(TAG, cmd + "------命令运行异常：" + e.getMessage());
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
                if (br != null) {
                    br.close();
                }
                process.destroy();
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
