package com.shiliu.dragon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.shiliu.dragon.R;
import com.shiliu.dragon.model.Comments;
import com.shiliu.dragon.model.Content;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ContentActivity extends AppCompatActivity {
    private static final String TAG = "ContentActivity";

    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        String address = this.getString(R.string.dev_address);
        URL = "http://" + address;
    }


    public void publishContent(View view) {
        File file1 = new File("/sdcard/oyc.jpg");
        File file2 = new File("/sdcard/test.jpg");
        RequestBody fileBody1 = RequestBody.create(MediaType.parse("image/png"), file1);
        RequestBody fileBody2 = RequestBody.create(MediaType.parse("image/png"), file2);
        Content content = new Content("3d814e9435274e758c799f9b1fae0d2b","hello content");
        Gson gson = new Gson();
        String json = gson.toJson(content);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",file1.getName(),fileBody1)
                .addFormDataPart("file",file2.getName(),fileBody2)
                .addFormDataPart("content",json).build();
        Request request = new Request.Builder().addHeader("token",UserActivity.TOKEN).post(requestBody).url(URL+"/dragon/content/publish")
                .build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ",e );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: "+new String(response.body().bytes()));
            }
        });
    }

    public void queryContentById(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/content/19cdc96a200144469af7eb751e63393a").addHeader("token", UserActivity.TOKEN).method("POST", FormBody.create(MediaType.parse("application/json; charset=utf-8"), "")).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User query failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "Content query success " + new String(response.body().bytes(), "UTF-8"));

            }
        });
    }

    public void queryContent(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/content/conditionQuery").addHeader("token", UserActivity.TOKEN).method("POST", FormBody.create(MediaType.parse("application/json; charset=utf-8"), "")).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User query failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "Content query success " + new String(response.body().bytes(), "UTF-8"));

            }
        });
    }

    public void addComment(View view) {
        Comments comments = new Comments();
        comments.setContentId("19cdc96a200144469af7eb751e63393a");
        comments.setUserId("3d814e9435274e758c799f9b1fae0d2b");
        comments.setMessage("帖子真赞");
        Gson gson = new Gson();
        String json = gson.toJson(comments);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(URL + "/dragon/content/addComment").addHeader("token", UserActivity.TOKEN).method("POST",requestBody ).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Add comments failed ", e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "Add comments success " + new String(response.body().bytes(), "UTF-8"));

            }
        });
    }
}
