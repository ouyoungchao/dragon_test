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
        Content content = new Content("3d814e9435274e758c799f9b1fae0d2b","hello content","测试");
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
        Request request = new Request.Builder().url(URL + "/dragon/content/5ff3e2cd9b2f4c4cacac61f8ac26343c").addHeader("token", UserActivity.TOKEN).method("POST", FormBody.create(MediaType.parse("application/json; charset=utf-8"), "")).build();
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
        comments.setContentId("d3119e25701d45a1a3ade01143a89d25");
        comments.setUserId("c09e5d8265ab4ea49a8a4f6cea8e14dd");
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

    public void replyComment(View view) {
        Comments comments = new Comments();
        comments.setContentId("0614c3dcca9440f9a124838b8dd09d63");
        comments.setUserId("c09e5d8265ab4ea49a8a4f6cea8e14dd");
        comments.setMessage("谢谢认同");
        comments.setComment(true);
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

    public void star(View view) {
        RequestBody requestBody = new FormBody.Builder()
                .add("contentId", "c6b7968c9a7642a4af970d2b43247505")
                .build();
        Request request = new Request.Builder().url(URL + "/dragon/content/star").addHeader("token", UserActivity.TOKEN)
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User star failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = new String(response.body().bytes(), "UTF-8");
                Log.i(TAG, "User star success " + body);
            }
        });
    }

    public void cancelStar(View view) {
        RequestBody requestBody = new FormBody.Builder()
                .add("contentId", "c6b7968c9a7642a4af970d2b43247505")
                .build();
        Request request = new Request.Builder().url(URL + "/dragon/content/cancelStar").addHeader("token", UserActivity.TOKEN)
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User add fans failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = new String(response.body().bytes(), "UTF-8");
                Log.i(TAG, "User add fans success " + body);
            }
        });
    }

    public void queryMessages(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/messages").addHeader("token", UserActivity.TOKEN).method("POST", FormBody.create(MediaType.parse("application/json; charset=utf-8"), "")).build();
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
}
