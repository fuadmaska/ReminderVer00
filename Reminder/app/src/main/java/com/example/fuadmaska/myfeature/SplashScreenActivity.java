package com.example.fuadmaska.myfeature;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.fuadmaska.myfeature.Model.DataReminder;
import com.example.fuadmaska.myfeature.Model.UserLogin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {
    private ArrayList<UserLogin> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loaddata();
                if (data.size()== 0) {
                    Intent pindah = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(pindah);
                    finish();
                }
                else{
                    Intent pindah = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(pindah);
                    finish();
                }

            }
        }, 3000);

    }
    public void loaddata() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("datalogin", null);
        Type type = new TypeToken<ArrayList<UserLogin>>() {
        }.getType();
        data = gson.fromJson(json, type);

        if (data == null) {
            data = new ArrayList<>();
        }
    }
}
