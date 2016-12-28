package com.wisn.pmlib.activity.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;
import com.wisn.pmlib.receiver.AlarmRecever;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by wisn on 2016/12/8.
 注意事项:
 * 1 因为我们是要实现的两个闹钟每天早八点和晚八点,它们是重复的.
 *   所以在PendingIntent.getBroadcast()很容易出错.导致早八点
 *   的闹钟无效.主要原因是因为该方法的第四个参数引起的.
 *   解决办法:为BroadcastReceiver设置两个action,在生成Intent
 *   的时候采用了不同的action,从而让两个闹钟都起作用.
 *   如果有更好的方式请留言指出,多谢.
 * 2 在calendar.set(Calendar.HOUR_OF_DAY, 8)方法的第二个参数
 *   表示小时,请按照24小时制式填写.不用管此时设备采用什么时间
 *   制式(12小时或24小时)
 */

public class AlarmActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Button setAlarmButton= (Button) findViewById(R.id.alarm_settime);
        Button setAlarmPollButton= (Button) findViewById(R.id.alarm_settime_poll);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmActivity.this, AlarmRecever.class);
                intent.putExtra("type", "单个的");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, 0);
                AlarmManager am = (AlarmManager) AlarmActivity.this.getSystemService(Context.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+5000, pendingIntent);
            }
        });
        setAlarmPollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmActivity.this, AlarmRecever.class);
                intent.putExtra("type", "重复的");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, 0);
                AlarmManager am = (AlarmManager) AlarmActivity.this.getSystemService(Context.ALARM_SERVICE);
//                am.set(AlarmManager.,System.currentTimeMillis()+5000, pendingIntent);
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 20);
                calendar.set(Calendar.MINUTE, 30);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                am.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_FIFTEEN_MINUTES,pendingIntent);
            }
        });
    }
}
