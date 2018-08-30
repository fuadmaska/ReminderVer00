package com.example.fuadmaska.myfeature;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuadmaska.myfeature.Model.UserLogin;
import com.google.gson.Gson;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout logpass, logemail;
    TextInputEditText tielogmail, tielogpass;
    Button btnlog;
    TextView tvlogforpas;
    private static final String email="fak23@gmail.com";
    private static final String pass="123";
    private String user,passwd;
    private ArrayList<UserLogin> data;
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginActivity.this.finish();
                    }
                }).setNegativeButton("No",null).show();
    }
    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        data = new ArrayList<>();
        logpass = findViewById(R.id.til_logpass);
        logemail = findViewById(R.id.til_logemail);
        logpass.setHintTextAppearance(R.style.TextInputLayoutHintText);
        logemail.setHintTextAppearance(R.style.TextInputLayoutHintText);


        btnlog = findViewById(R.id.btn_log);
        tvlogforpas = findViewById(R.id.tv_logforpass);
        tielogmail = findViewById(R.id.edt_logemail);
        tielogpass = findViewById(R.id.edt_logpass);

        ////////
        tielogpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user = tielogmail.getText().toString();
                passwd = tielogpass.getText().toString();
                    if(passwd.length()>0 && user.length()>0){
                        btnlog.setBackgroundResource(R.drawable.btn_login_red);
                    }else btnlog.setBackgroundResource(R.drawable.btn_login);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user = tielogmail.getText().toString();
                passwd = tielogpass.getText().toString();
                if(passwd.length()>0 && user.length()>0){
                    btnlog.setBackgroundResource(R.drawable.btn_login_red);
                }else btnlog.setBackgroundResource(R.drawable.btn_login);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                user = tielogmail.getText().toString();
                passwd = tielogpass.getText().toString();
                if(passwd.length()>0 && user.length()>0){
                    btnlog.setBackgroundResource(R.drawable.btn_login_red);
                }else btnlog.setBackgroundResource(R.drawable.btn_login);
            }
        });

        tielogmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user = tielogmail.getText().toString();
                passwd = tielogpass.getText().toString();
                if(passwd.length()>0 && user.length()>0){
                    btnlog.setBackgroundResource(R.drawable.btn_login_red);
                }else btnlog.setBackgroundResource(R.drawable.btn_login);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                user = tielogmail.getText().toString();
                passwd = tielogpass.getText().toString();
                if(passwd.length()>0 && user.length()>0){
                    btnlog.setBackgroundResource(R.drawable.btn_login_red);
                }else btnlog.setBackgroundResource(R.drawable.btn_login);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                user = tielogmail.getText().toString();
                passwd = tielogpass.getText().toString();
                if(passwd.length()>0 && user.length()>0){
                    btnlog.setBackgroundResource(R.drawable.btn_login_red);
                }else btnlog.setBackgroundResource(R.drawable.btn_login);
            }
        });



        /////
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=tielogmail.getText().toString();
                passwd=tielogpass.getText().toString();

                if(validate()==true){
                    data.add(new UserLogin(user,passwd));
                    save();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplication(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                    finish();

                }
                else {
                    Toast.makeText(getApplication(), "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvlogforpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("datalogin", json);
        editor.apply();

    }
    public boolean validate(){
        boolean cek = false;
        if (email.equals(user) && pass.equals(passwd)){
            cek=true;
        }
        return cek;
    }
}


