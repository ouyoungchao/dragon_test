package com.shiliu.dragon;

import okhttp3.OkHttpClient;

public class HttpClient {
    private static OkHttpClient client = new OkHttpClient();

    public static OkHttpClient getClient(){
        return client;
    }

}
