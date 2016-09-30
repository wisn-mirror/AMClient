package com.wisn.pm.activity;

import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.wisn.pm.R;
import com.wisn.pm.receiver.MyDeviceAdminReceiver;
import com.wisn.pm.service.InstallApkService;
import com.wisn.pm.utils.ToastUtils;
import com.wisn.pm.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "de.tavendo.test1";
    private TextView finish;
    private TextView install_Listener;
    private TextView manager;
    private TextView remove_manager;
    private TextView websocket;
    private TextView websocket_send;
    private TextView location;
    private TextView location_ring;
    private TextView getlog;
    private TextView getTemperature;
    private EditText chatip;
    private ComponentName com;
    private DevicePolicyManager deviceManager;
    private WebSocketConnection mConnection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finish = (TextView) findViewById(R.id.finish);
        install_Listener = (TextView) findViewById(R.id.install_Listener);
        manager = (TextView) findViewById(R.id.manager);
        remove_manager = (TextView) findViewById(R.id.remove_manager);
        websocket = (TextView) findViewById(R.id.websocket);
        websocket_send = (TextView) findViewById(R.id.websocket_send);
        location = (TextView) findViewById(R.id.location);
        getTemperature = (TextView) findViewById(R.id.getTemperature);
        location_ring = (TextView) findViewById(R.id.location_ring);
        getlog = (TextView) findViewById(R.id.getlog);
        chatip = (EditText) findViewById(R.id.chatip);

        checkService();
        finish.setOnClickListener(this);
        remove_manager.setOnClickListener(this);
        manager.setOnClickListener(this);
        websocket.setOnClickListener(this);
        websocket_send.setOnClickListener(this);
        location.setOnClickListener(this);
        location_ring.setOnClickListener(this);
        getlog.setOnClickListener(this);
        getTemperature.setOnClickListener(this);
        getTemperature.setText(getSensor());

        initLocation();
        startActivity(new Intent(this,BlurActivity.class));
    }

    /**
     * 检测服务是否在运行
     */
    private void checkService() {
        if (mConnection == null) {
            mConnection = new WebSocketConnection();
        }
        deviceManager = (DevicePolicyManager) this
                .getSystemService(this.DEVICE_POLICY_SERVICE);
        // 中间人，用于判断是否开启管理者权限的
        com = new ComponentName(this, MyDeviceAdminReceiver.class);
        if (!isWorked()) {
            install_Listener.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    install_Listener.setText("已经开始监听文件夹");
                    startService(new Intent(MainActivity.this, InstallApkService.class));
                }
            });
        } else {
            install_Listener.setText("已经开始监听文件夹");
        }
    }

    public boolean isWorked() {
        ActivityManager myManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(40);
        for (int i = 0; i < runningService.size(); i++) {
            System.out.println("packagename:" + runningService.get(i).service.getClassName().toString());
            if (runningService.get(i).service.getClassName().toString().equals("com.install.pminstall.InstallApkService")) {
                return true;
            }
        }
        return false;
    }

    public void open_websocket(final TextView tv) {
        // final String wsuri = "ws://10.128.230.59:8080/websocket01/chat.ws?username=wisn";
        final String wsuri = "ws://" + chatip.getText().toString().trim() + ":8080/PM/chat.ws?username=wisn11";
        try {
            mConnection.connect(wsuri, new WebSocketHandler() {
                @Override
                public void onOpen() {
                    Log.d(TAG, "Status: Connected to " + wsuri);
                    mConnection.sendTextMessage("发送请求" + System.currentTimeMillis());
                    ToastUtils.show(MainActivity.this, "建立连接成功！");
                }

                @Override
                public void onTextMessage(final String payload) {
                    Log.d(TAG, "Got echo: " + payload);
                    ToastUtils.show(MainActivity.this, "长链接返回" + payload);
                    tv.setText(String.valueOf(payload));
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.d(TAG, "Connection lost.");
                }

                @Override
                public void onBinaryMessage(byte[] payload) {
                    super.onBinaryMessage(payload);
                }

                @Override
                public void onRawTextMessage(byte[] payload) {
                    super.onRawTextMessage(payload);
                }
            });
        } catch (WebSocketException e) {
            Log.d(TAG, e.toString());
        }
    }

    @Override
    public void onClick(View view) {
        if (view == remove_manager) {
            boolean admin = deviceManager.isAdminActive(com);
            if (admin) {
                deviceManager.removeActiveAdmin(com);
                ToastUtils.show(MainActivity.this, "移除管理者权限成功！");
            } else {
                ToastUtils.show(MainActivity.this, "未注册管理者权限");
            }
        } else if (view == manager) {
            Intent openadmin = new Intent(
                    DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            openadmin.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, com);
            // 如果只是普通开启 就不需要索要结果
            startActivity(openadmin);
        } else if (view == websocket) {
            //websocket建立连接
            if (TextUtils.isEmpty(chatip.getText().toString().trim())) {
                ToastUtils.show(MainActivity.this, "input   IP");
                return;
            }
            if (mConnection != null) {
                if (mConnection.isConnected()) {
                    ToastUtils.show(MainActivity.this, "已经建立连接！");
                } else {
                    open_websocket(websocket);
                }
            } else {
                mConnection = new WebSocketConnection();
                open_websocket(websocket);
            }
        } else if (view == websocket_send) {
            //发送websocket消息
            try {
                if (mConnection != null && mConnection.isConnected()) {
                    mConnection.sendTextMessage("发送请求" + System.currentTimeMillis());
                } else {
                    mConnection = new WebSocketConnection();
                    mConnection.sendTextMessage("发送请求" + System.currentTimeMillis());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (view == location) {
            //定位
            startLocation();
            location.setText("定位中");
            location.setOnClickListener(null);
        } else if (view == location_ring) {
            //设置位置监听
          ToastUtils.show(MainActivity.this,"未实现");
        } else if (view == getlog) {
            //获取日志，开始断点续传
            ToastUtils.show(MainActivity.this,"未实现");
        } else if (view == getTemperature) {
            //获取温度
            /*获取系统服务（SENSOR_SERVICE）返回一个SensorManager对象*/
            mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            /*通过SensorManager获取相应的（温度传感器）Sensor类型对象*/
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
            if (mSensor == null) {
                getTemperature.setText("没有温度传感器");
                return;
            }
            boolean isSuccess = mSensorManager.registerListener(mSensorEventListener, mSensor
                    , SensorManager.SENSOR_DELAY_NORMAL);
            if (isSuccess) {
                getTemperature.setText("已经开始监听了");
                getTemperature.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mSensorManager.unregisterListener(mSensorEventListener, mSensor);
                    }
                });
            } else {
                getTemperature.setOnClickListener(this);
            }

        }else  if(view==finish){
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //地图定位资源销毁
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    /*声明一个SensorEventListener对象用于侦听Sensor事件，并重载onSensorChanged方法*/
    private final SensorEventListener mSensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_TEMPERATURE) {
                /*温度传感器返回当前的温度，单位是摄氏度（°C）。*/
                float temperature = event.values[0];
                getTemperature.setText(String.valueOf(temperature) + "°C");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }
    };
    private SensorManager mSensorManager = null;
    private Sensor mSensor = null;

    //////////////////////////////定位模块//////////////////////////////////////////
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        //设置定位参数
        locationClient.setLocationOption(getDefaultOption());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }
    /**
     * 开始定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void startLocation(){
        //根据控件的选择，重新设置定位参数
        resetOption();
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    // 根据控件的选择，重新设置定位参数
    private void resetOption() {
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位
         * 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(true);
        // 设置是否开启缓存
        locationOption.setLocationCacheEnable(true);
        //设置是否等待设备wifi刷新，如果设置为true,会自动变为单次定位，持续定位时不要使用
        //locationOption.setOnceLocationLatest(false);
        // 设置发送定位请求的时间间隔,最小值为1000，如果小于1000，按照1000算
        locationOption.setInterval(Long.valueOf(1000));
        // 设置网络请求超时时间
        locationOption.setHttpTimeOut(Long.valueOf(30000));
    }
    /**
     * 停止定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void stopLocation(){
        // 停止定位
        locationClient.stopLocation();
    }
    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            if (null != loc) {
                //解析定位结果
                String result = MapUtils.getLocationStr(loc);
                location.setText(result);
            } else {
                location.setText("定位失败，loc is null");
            }
        }
    };

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是ture
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        return mOption;
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 获取所有的传感器
     *
     * @return
     */
    public String getSensor() {
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> allSensors = sm.getSensorList(Sensor.TYPE_ALL);// 获得传感器列表
        StringBuffer str = new StringBuffer();
        str.append("该手机有" + allSensors.size() + "个传感器,分别是:\n");
        for (int i = 0; i < allSensors.size(); i++) {
            Sensor s = allSensors.get(i);
            str.append("设备名称:" + s.getName() + "\n");
            str.append("设备版本:" + s.getVersion() + "\n");
            str.append("通用类型号:" + s.getType() + "\n");
            str.append("设备商名称:" + s.getVendor() + "\n");
            str.append("传感器功耗:" + s.getPower() + "\n");
            str.append("传感器分辨率:" + s.getResolution() + "\n");
            str.append("传感器最大量程:" + s.getMaximumRange() + "\n");
            switch (s.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    str.append(i + "加速度传感器");
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    str.append(i + "陀螺仪传感器");
                    break;
                case Sensor.TYPE_LIGHT:
                    str.append(i + "环境光线传感器");
                    break;
                case Sensor.TYPE_MAGNETIC_FIELD:
                    str.append(i + "电磁场传感器");
                    break;
                case Sensor.TYPE_ORIENTATION:
                    str.append(i + "方向传感器");
                    break;
                case Sensor.TYPE_PRESSURE:
                    str.append(i + "压力传感器");
                    break;
                case Sensor.TYPE_PROXIMITY:
                    str.append(i + "距离传感器");
                    break;
                case Sensor.TYPE_TEMPERATURE:
                    str.append(i + "温度传感器");
                    break;
                default:
                    str.append(i + "未知传感器");
                    break;
            }
        }
        return str.toString();
    }

}
