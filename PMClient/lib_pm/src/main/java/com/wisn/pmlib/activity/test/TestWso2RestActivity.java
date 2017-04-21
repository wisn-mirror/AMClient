package com.wisn.pmlib.activity.test;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;
import com.wisn.pmlib.utils.LogUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by wisn on 2017/4/17.
 */

public class TestWso2RestActivity extends BaseActivity {
    private TextView mTestResult;
    ScrollView sv;

//    get请求地址：http://10.0.34.252:9763/analytics/table_exists?table=TEST
//    header1: {"Content-Type":"application/json"}
//    header2: {"Authorization":"Basic YWRtaW46YWRtaW4="}
    public static final String mhttpUrl = "http://10.0.34.252:9763/analytics/table_exists?table=TEST";
    private String mDeviceId;
    //    public static final String mhttpUrl = "http://10.0.34.252:9763/analytics/tables/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mTestResult = (TextView) findViewById(R.id.testResult);
        sv= (ScrollView)findViewById(R.id.scroll_info);//获取scrollView组件
        postThread.start();

        mTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//        String result= getImei();
                        String result= getProcessCpuRate();
                        updateView(result);
                        LogUtils.e("result:", "" + result);

                    }
                }).start();
            }
        });

    }

    /**
     * 获取cpu状态
     * @return
     */
    private String getProcessCpuRate() {
        StringBuilder tv = new StringBuilder();
        int rate = 0;

        try {
            String Result;
            Process p;
            p = Runtime.getRuntime().exec("top -n 1 -m 1  -s cpu");

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((Result = br.readLine()) != null) {
                if (Result.trim().length() < 1) {
                    continue;
                } else {
                    String[] CPUusr = Result.split("%");
                    tv.append("USER:" + CPUusr[0] + "\n");
                    String[] CPUusage = CPUusr[0].split("User");
                    String[] SYSusage = CPUusr[1].split("System");
                    tv.append("CPU:" + CPUusage[1].trim() + " length:" + CPUusage[1].trim().length() + "\n");
                    tv.append("SYS:" + SYSusage[1].trim() + " length:" + SYSusage[1].trim().length() + "\n");

                    rate = Integer.parseInt(CPUusage[1].trim()) + Integer.parseInt(SYSusage[1].trim());
                    break;
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(rate + "");
        return tv.toString();
    }

    private String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mDeviceId = telephonyManager.getDeviceId();
        }
       return mDeviceId;
    }

    public void updateView(final String test) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTestResult.append(test);

            }
        });
    }
    private Thread postThread = new Thread() {
        public void run() {

            String result = "";
            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(mhttpUrl);
                connection = (HttpURLConnection) url.openConnection();
                // 设置请求方式
                connection.setRequestMethod("GET");
                // 设置编码格式
                connection.setRequestProperty("Charset", "UTF-8");
                // 传递自定义参数
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");
                 /*设置通用请求属性*/
                //告诉WEB服务器自己接受什么介质类型，*/* 表示任何类型，type/* 表示该类型下的所有子类型，type/sub-type。
                //connection.setRequestProperty("accept", "*/*");
               // connection.setRequestProperty("connection", "Keep-Alive");
                //浏览器表明自己的身份（是哪种浏览器）
//                connection.setRequestProperty("user-agent",
//                                              "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.8.1.14)");
                LogUtils.e("contentLength", "" + connection.getContentLength());
                LogUtils.e("contentEncoding", "" + connection.getContentEncoding());
                LogUtils.e("contentDate", "" + connection.getDate());
                LogUtils.e("getResponseCode", "" + connection.getResponseCode());
                //获取所有相应头字段
                Map<String, List<String>> map = connection.getHeaderFields();
                //遍历所有响应头字段
                for (String key : map.keySet()) {
                    LogUtils.i("getHeaderFields", "" + map.get(key));
                }
                //定义BufferReader输入流来读取URL的响应
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while((line = bufferedReader.readLine()) != null){
                    result += "\n" + line;
                    LogUtils.i(result);
                    updateView(result);
                    sv.fullScroll(ScrollView.FOCUS_DOWN);//滚动到底部
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                LogUtils.e("GET方式请求", "发送GET请求异常" + e);
                e.printStackTrace();
            } finally {
                if (null != bufferedReader) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }

        }
    };
}
