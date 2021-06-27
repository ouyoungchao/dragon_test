package com.shiliu.dragon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.shiliu.dragon.model.Content;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AuditActivity extends AppCompatActivity {
    private static final String TAG = "AuditActivity";

    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audit);
        String address = this.getString(R.string.dev_address);
        URL = "http://" + address;
    }

    public void studentTest(View view) {
        File file1 = new File("/sdcard/oyc.jpg");
        File file2 = new File("/sdcard/test.jpg");
        RequestBody fileBody1 = RequestBody.create(MediaType.parse("image/png"), file1);
        RequestBody fileBody2 = RequestBody.create(MediaType.parse("image/png"), file2);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file",file1.getName(),fileBody1)
                .addFormDataPart("file",file2.getName(),fileBody2)
                .build();
        Request request = new Request.Builder().addHeader("token",UserActivity.TOKEN).post(requestBody).url(URL+"/dragon/authentication/student")
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
}
