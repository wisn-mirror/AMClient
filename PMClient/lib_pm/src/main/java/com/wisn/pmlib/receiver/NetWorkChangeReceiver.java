package com.wisn.pmlib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by wisn on 2016/12/12.
 * 网络状态变化的广播
 */

public class NetWorkChangeReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager
                manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        Toast.makeText(context, "mobile:" + mobileInfo.isConnected() + "\n" + "wifi:" + wifiInfo.isConnected()
                                + "\n" + "active:" + activeInfo.getTypeName(), Toast.LENGTH_SHORT).show();
        //如果无网络连接activeInfo为null
    }
}
