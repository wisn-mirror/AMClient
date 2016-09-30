package com.wisn.pm.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

/**
 * <b>Create Date:</b> 2016/9/21<br>
 * <b>Author:</b> Wisn(吴贻顺)<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */

public class ApkOperate {
    /**
     * 获取apk文件的基本信息
     * @param pm
     * @param filePath
     * @return
     */
    public      ApkInformation   getApkFileInformation(PackageManager pm  , String filePath){
        PackageInfo packageInfo = pm.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if(packageInfo!=null){
            return  new  ApkInformation(packageInfo.packageName,packageInfo.sharedUserId,packageInfo.versionName,packageInfo.versionCode);
        }else{
            return  null;
        }
    }
    public    class  ApkInformation{
        public String name;
        public String uid;
        public String vname;
        public  int code;
        public ApkInformation(String name, String uid, String vname, int code) {
            this.name = name;
            this.uid = uid;
            this.vname = vname;
            this.code = code;
        }
    }
    /**
     * 判断应用是否已安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public   boolean isInstalled(Context context, String packageName) {
        boolean hasInstalled = false;
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> list = pm
               // .getInstalledPackages(PackageManager.PERMISSION_GRANTED);
                .getInstalledPackages(0);
        for (PackageInfo p : list) {
            if (packageName != null && packageName.equals(p.packageName)) {
                hasInstalled = true;
                break;
            }
        }
        return hasInstalled;
    }

    /**
     * 判断应用是否正在运行
     *
     * @param context
     * @param packageName
     * @return
     */
    public   boolean isRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : list) {
            String processName = appProcess.processName;
            if (processName != null && processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }
    /**
     * 启动一个app
     * @param context
     * @param packageName
     */
    public  void openApp(Context  context  ,String packageName) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(packageName, 0);
            PackageManager packageManager = context.getPackageManager();
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(pi.packageName);
            List<ResolveInfo> apps =packageManager.queryIntentActivities(resolveIntent, 0);
            ResolveInfo ri = apps.iterator().next();
            if (ri != null ) {
                String packageName_new = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName cn = new ComponentName(packageName_new, className);
                intent.setComponent(cn);
                context.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
