package com.wisn.pmlib.activity.retrofit;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface APi {
//
//    @Headers({"apikey:81bf9da930c7f9825a3c3383f1d8d766" ,"Content-Type:application/json"})
//    @GET("world/world")
//    Call<News> getNews(@Query("num") String num,@Query("page")String page);
//
//    @FormUrlEncoded
//    @Headers({"apikey:81bf9da930c7f9825a3c3383f1d8d766" ,"Content-Type:application/json"})
//    @POST("world/world")
//    Call<News> postNews(@Field("num") String num, @Field("page")String page);
//
//    @Headers({"apikey:81bf9da930c7f9825a3c3383f1d8d766" ,"Content-Type:application/json"})
//    @GET("{type}/{type}")
//    Call<News> tiYu(@Path("type") String type, @Query("num") String num,@Query("page")String page);
//
//    @Headers({"apikey:81bf9da930c7f9825a3c3383f1d8d766" ,"Content-Type:application/json"})
//    @GET("{type1}/{type2}")
//    Call<News> tiYu(@Path("type1") String type1,@Path("type2") String type2,  @Query("num") String num,@Query("page")String page);
//
//    @FormUrlEncoded
//    @Headers({"apikey:81bf9da930c7f9825a3c3383f1d8d766" ,"Content-Type:application/json"})
//    @POST("keji/keji")
//    Call<News> keji(@Query("num") String num,@Query("page")String page);


//    @Headers({"Content-Type:application/json"})
    @POST("app/applogin")
    Call<Result> appLogin(@Body RequestBody requestBody);
    @POST("app/applogin")
    Call<Result> register(@Body RequestBody requestBody);

}
