package com.shiliu.dragon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void userTest(View view) {
        Intent intent =new Intent(MainActivity.this, UserActivity.class);
        startActivity(intent);
    }

    public void contentTest(View view) {
        Intent intent =new Intent(MainActivity.this, ContentActivity.class);
        startActivity(intent);
    }

    public void auditTest(View view) {
        Intent intent =new Intent(MainActivity.this, AuditActivity.class);
        startActivity(intent);
    }

    public void schoolTest(View view) {
        Intent intent =new Intent(MainActivity.this, SchoolActivity.class);
        startActivity(intent);
    }


}
