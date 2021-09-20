package com.shiliu.dragon;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class HttpClient {
    private static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).callTimeout(50,TimeUnit.SECONDS).build();

    public static OkHttpClient getClient(){
        return client;
    }

}
