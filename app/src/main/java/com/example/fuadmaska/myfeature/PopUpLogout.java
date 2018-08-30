package com.example.fuadmaska.myfeature;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.fuadmaska.myfeature.Model.UserLogin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PopUpLogout extends Dialog {

    private Button btnYes,btnNo;
    private TextView tvtitle,tvKeterangan;
    private Context context;
    private Intent intent ;
    public PopUpLogout(@NonNull Context context,Intent intent) {
        super(context);
        this.context = context;
        this.intent = intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pop_up_logout);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        initKomponen();
    }

    public void initKomponen(){
        btnYes = findViewById(R.id.btn_yes);
        btnNo = findViewById(R.id.btn_no);
        tvtitle = findViewById(R.id.tv_title);
        tvKeterangan = findViewById(R.id.tv_keterangan);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaddata();
                Intent intent = new Intent(context,LoginActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
    public void loaddata() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

    }
}
