package com.wisn.pm.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.wisn.pm.utils.ApkOperate;

/**
 * Created by 00301945 on 2016/9/13.
 */

public class AppInstallReceiver   extends BroadcastReceiver {
    ApkOperate apkutils=null;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(apkutils==null){
            apkutils=new ApkOperate();
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Toast.makeText(context, "安装成功"+packageName, Toast.LENGTH_LONG).show();
            apkutils.openApp(context ,packageName);
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Toast.makeText(context, "卸载成功"+packageName, Toast.LENGTH_LONG).show();
        }
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
            String packageName = intent.getData().getSchemeSpecificPart();
            Toast.makeText(context, "替换成功"+packageName, Toast.LENGTH_LONG).show();
            apkutils.openApp(context ,packageName);
        }
    }



}
