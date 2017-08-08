package com.wisn.pmlib.activity.retrofit;

import android.os.Bundle;

import com.wisn.pmlib.R;
import com.wisn.pmlib.activity.base.BaseActivity;
import com.wisn.pmlib.utils.LogUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        final String
                request =
                "{\"code\":200,\"reason\":{},\"content\":{\"device_imei\":\"132343241322\",\"device_imsi\":\"\",\"device_isrooted\":0,\"device_macaddress\":\"\",\"device_manufacturer\":\"\",\"device_model\":\"\",\"device_name\":\"\",\"device_networkoperatorname\":\"\",\"device_osname\":\"\",\"device_osversion\":\"\",\"id\":0,\"passWord\":\"nihao\",\"userName\":\"234231\"}}";
        final WssModel wssModel = new WssModel();
        for (int i = 0; i < 100; i++) {
            Call<Result> resultCall = wssModel.requestAsy(request, new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    LogUtils.e(System.currentTimeMillis() + " Asy:" + response.body().toString());
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    LogUtils.e(t.getMessage());

                }
            });
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        Call<Result> resultCall1 = wssModel.requestSync(request, Result.class);
                        Response<Result> execute = resultCall1.execute();
                        LogUtils.e(System.currentTimeMillis() + " sync:" + execute.body().toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
