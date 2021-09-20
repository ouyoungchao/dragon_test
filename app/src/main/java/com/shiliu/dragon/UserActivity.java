package com.shiliu.dragon;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.shiliu.dragon.model.AuthResponse;
import com.shiliu.dragon.model.User;
import com.shiliu.dragon.model.UserModifyModel;

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

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "UserActivity";

    private String URL;

    public static String TOKEN = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1
        );
        setContentView(R.layout.activity_user);
        String address = this.getString(R.string.dev_address);
        URL = "http://" + address;
    }

    /**
     * 获取验证码
     *
     * @param view
     */
    public void getSms(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/code/sms?mobile=8617817098736").build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    public void getUASms(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/code/sms?mobile=447745889086").build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: " + response.body().string());
            }
        });
    }

    public void registerUser(View view) {
        User user = new User("8617817098736", "Oyc@1234", "Oyc@1234", "hunan", "ouyang", "cqupt", "12345678", "bioinfo", "776405", "1", null);
        Gson gson = new Gson();
        String json = gson.toJson(user);

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(URL + "/dragon/user/register")
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User register failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "User register success " + response.body().string());
            }
        });
    }

    public void customerLogin(View view) {
        RequestBody requestBody = new FormBody.Builder()
                .add("mobile", "8617817098736")
                .add("smsCode", "651606")
                .build();
        Request request = new Request.Builder().url(URL + "/dragon/login/customer")
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Customer login failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = new String(response.body().bytes());
                Log.e(TAG, "Customer login success " + body);
                getToken(body);
            }
        });
    }

    public void loginByMobile(View view) {
        RequestBody requestBody = new FormBody.Builder()
                .add("mobile", "8617817098736")
                .add("smsCode", "912266")
                .build();
        Request request = new Request.Builder().url(URL + "/dragon/authentication/mobile")
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User register failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = new String(response.body().bytes());
                Log.e(TAG, "User register success " + body);
                getToken(body);
            }
        });
    }

    private void getToken(String response) {
        Gson gson = new Gson();
        AuthResponse authResponse = gson.fromJson(response, AuthResponse.class);
        if (authResponse != null) {
            TOKEN = authResponse.getTokenId();
        }
    }

    public void queryUserById(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/user/ea52214f14124e12ba12facc15aab313").addHeader("token", TOKEN).method("POST", FormBody.create(MediaType.parse("application/json; charset=utf-8"), "")).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User query failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "User query success " + new String(response.body().bytes(), "UTF-8"));

            }
        });
    }

    public void queryUserByErrorId(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/user/f2ded8a1cb7a41969f8eb3e9684f185a").addHeader("token", TOKEN).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User query failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "User query success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }

    public void visitorLogin(View view) {
        RequestBody requestBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder().url(URL + "/dragon/login/visitor")
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Visitor login failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = new String(response.body().bytes(), "UTF-8");
                Log.i(TAG, "Visitor login success " + body);
                getToken(body);
            }
        });
    }

    public void loginByUser(View view) {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "admin")
                .build();
        Request request = new Request.Builder().url(URL + "/dragon/authentication/user")
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User register failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = new String(response.body().bytes(), "UTF-8");
                Log.i(TAG, "User register success " + body);
                getToken(body);
            }
        });
    }

    public void queyUsers(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/user?offset=0&pageSize=3").addHeader("token", TOKEN).method("POST", FormBody.create(MediaType.parse("application/json; charset=utf-8"), "")).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User query failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "User query success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }

    public void queyUsersByCondition(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/user/conditionQuery?offset=0&pageSize=3&origin=hunan").addHeader("token", TOKEN).method("POST", FormBody.create(MediaType.parse("application/json; charset=utf-8"), "")).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User query failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "User query success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }


    public void updataUser(View view) {
        UserModifyModel userModifyModel = new UserModifyModel();
        userModifyModel.addFild("userName", "ouyangchao666");
        userModifyModel.addFild("mobile", "8613012345678");
        Gson gson = new Gson();
        String json = gson.toJson(userModifyModel);
        Log.i(TAG, "updataUser: "+json);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(URL + "/dragon/user/modify").addHeader("token", TOKEN)
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User modify failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "User Modify success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }

    public void updataName(View view) {
        UserModifyModel userModifyModel = new UserModifyModel();
        userModifyModel.addFild("userName", "ouyangchao哈哈哈");
        Gson gson = new Gson();
        String json = gson.toJson(userModifyModel);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(URL + "/dragon/user/modify").addHeader("token", TOKEN)
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User modify failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "User Modify success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }

    public void updataMobile(View view) {
        UserModifyModel userModifyModel = new UserModifyModel();
        userModifyModel.addFild("mobile", "8612300000000");
        Gson gson = new Gson();
        String json = gson.toJson(userModifyModel);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(URL + "/dragon/user/modify").addHeader("token", TOKEN)
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User modify failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "User Modify success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }

    public void updataPwd(View view) {
        UserModifyModel userModifyModel = new UserModifyModel();
        userModifyModel.addFild("password", "8699999999999");
        Gson gson = new Gson();
        String json = gson.toJson(userModifyModel);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder().url(URL + "/dragon/user/modify").addHeader("token", TOKEN)
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User modify failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "User Modify success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }

    public void uploadPortrait(View view) {
        File file = new File("/sdcard/test.jpg");
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody multipartBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), requestBody).build();
        Request request = new Request.Builder().post(multipartBody).url(URL + "/dragon/user/portrait").addHeader("token", TOKEN).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }

    public void userLogout(View view) {
        RequestBody requestBody = new FormBody.Builder().build();
        Request request = new Request.Builder().url(URL + "/dragon/authentication/logout")
                .post(requestBody).addHeader("token", TOKEN).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User logout failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "User logout success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }

    public void queryHotUser(View view) {
        Request request = new Request.Builder().url(URL + "/dragon/user/hotUsers?offset=0&pageSize=3&durations=10").addHeader("token", TOKEN).method("POST", FormBody.create(MediaType.parse("application/json; charset=utf-8"), "")).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User query failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "User query success " + new String(response.body().bytes(), "UTF-8"));
            }
        });
    }

    public void addFans(View view){
        RequestBody requestBody = new FormBody.Builder()
                .add("userId", "e66ca6ff5ec449ac8c9e74c9c84293f0")
                .build();
        Request request = new Request.Builder().url(URL + "/dragon/fans/follow").addHeader("token", TOKEN)
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

    public void deleteFans(View view){
        RequestBody requestBody = new FormBody.Builder()
                .add("userId", "e66ca6ff5ec449ac8c9e74c9c84293f0")
                .build();
        Request request = new Request.Builder().url(URL + "/dragon/fans/cancelFollow").addHeader("token", TOKEN)
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

    public void queryFans(View view){
        RequestBody requestBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder().url(URL + "/dragon/fans/queryFans").addHeader("token", TOKEN)
                .post(requestBody).build();
        HttpClient.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "User query fans failed ", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = new String(response.body().bytes(), "UTF-8");
                Log.i(TAG, "User query fans success " + body);
            }
        });
    }
}
