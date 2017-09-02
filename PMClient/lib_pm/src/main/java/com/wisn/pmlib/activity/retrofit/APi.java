package com.wisn.pmlib.activity.retrofit;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface APi {

//    @Headers({"Content-Type:application/json"})
    @POST("app/applogin")
    Call<Result> appLogin(@Body RequestBody requestBody);

    @POST("app/applogin")
    Call<Result> register(@Body RequestBody requestBody);

}
