package com.shiliu.dragon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.shiliu.dragon.model.School;
import com.shiliu.dragon.model.SchoolModifyModel;
import com.shiliu.dragon.model.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SchoolActivity extends AppCompatActivity {
    private static final String TAG = "SchoolActivity";

    private String URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        String address = this.getString(R.string.dev_address);
        URL = "http://" + address;
    }

    public void addSchool(View view) {
        School school = new School("cqupt", "重庆邮电大学","https://www.cqupt.edu", null);
        Gson gson = new Gson();
        String json = gson.toJson(school);

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(URL + "/dragon/school/add").addHeader("token", UserActivity.TOKEN)
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Add school failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "Add school success " + response.body().string());
            }
        });
    }

    public void querySchoolByName(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/school/cqupt").addHeader("token", UserActivity.TOKEN).method("POST", FormBody.create(MediaType.parse("application/json; charset=utf-8"), "")).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "School query failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "School query success " + new String(response.body().bytes(), "UTF-8"));

            }
        });
    }

    public void querySchool(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/school/query?offset=0&pageSize=3").addHeader("token", UserActivity.TOKEN).method("POST", FormBody.create(MediaType.parse("application/json; charset=utf-8"), "")).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "School query failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "School query success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }

    public void modifySchool(View view) {
        SchoolModifyModel modifyModel = new SchoolModifyModel();
        modifyModel.setName("cqupt");
        modifyModel.addFild("description","my school");
        Gson gson = new Gson();
        String json = gson.toJson(modifyModel);
        Log.i(TAG, "updataSchool: "+json);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(URL + "/dragon/school/modify").addHeader("token", UserActivity.TOKEN).method("POST", requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "School query failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "School query success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }
}
