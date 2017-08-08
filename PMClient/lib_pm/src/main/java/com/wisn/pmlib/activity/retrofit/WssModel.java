package com.wisn.pmlib.activity.retrofit;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wisn on 2017/8/7.
 */

public class WssModel {

    /**
     * 异步
     * @param loginInfo
     * @param callback
     * @param <T>
     * @return
     */
    public <T> Call<T> requestAsy(String loginInfo , Callback<T> callback) {
        APi api = Network.getInstance().getApi();
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), loginInfo);
        Call<T> call = (Call<T>) api.appLogin(body);
        call.enqueue(callback);
        return call;
    }

    /**
     * 同步
     * @param loginInfo
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> Call<T> requestSync(String loginInfo,Class<T> cls ) throws IOException {
        APi api = Network.getInstance().getApi();
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), loginInfo);
        Call<T> call = (Call<T>) api.appLogin(body);
        return call;
    }
}
