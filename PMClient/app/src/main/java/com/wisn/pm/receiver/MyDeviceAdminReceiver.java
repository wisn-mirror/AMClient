package com.wisn.pm.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * 管理者广播  、、用于开启系统最高的管理者的权限
 * @author wisn
 * @time 2016-3-11下午8:37:14
 */
public class MyDeviceAdminReceiver extends DeviceAdminReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}
}	
