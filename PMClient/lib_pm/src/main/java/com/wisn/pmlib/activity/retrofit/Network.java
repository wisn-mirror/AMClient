package com.wisn.pmlib.activity.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Network {
    private String Url="http://10.0.34.152:8080/WSS/";
    private static volatile Network mInstance;
    private APi mApi;

    private Network(){

    }

    public static Network getInstance(){
        if(mInstance==null){
            synchronized (Network.class){
                if(mInstance==null){
                    mInstance=new Network();
                }
            }
        }
        return mInstance;
    }

    public APi getApi(){
        if(mApi==null){
            synchronized (Network.class){
                if(mApi==null){
                    Retrofit retrofit = new Retrofit.Builder()
                            //使用自定义的mGsonConverterFactory
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(Url)
                            .build();
                    mApi = retrofit.create(APi.class);
                }
            }

        }

        return mApi;

    }
}
