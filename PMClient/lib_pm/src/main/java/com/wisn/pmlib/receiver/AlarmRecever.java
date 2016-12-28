package com.wisn.pmlib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by wisn on 2016/12/28.
 */

public class AlarmRecever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String  type=intent.getStringExtra("type");
        Toast.makeText(context, "收到了......."+type, Toast.LENGTH_SHORT).show();
    }
}
