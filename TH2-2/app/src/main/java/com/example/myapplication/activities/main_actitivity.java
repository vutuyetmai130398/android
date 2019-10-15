package com.example.myapplication.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;


public class main_actitivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText editTextUsername, editTextPass;
    private Button loginBtn;
    private Button signupBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        editTextUsername=findViewById(R.id.username);
        editTextPass=findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        signupBtn = (Button) findViewById(R.id.register);
        progressBar = findViewById(R.id.progressBar2);
        initListener();
    }

    private void initListener() {
        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                Toast.makeText(getApplicationContext(), "Registering...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(main_actitivity.this, MainRegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                Toast.makeText(getApplicationContext(), "Logining....", Toast.LENGTH_LONG).show();
                MainLoginActivity mainLoginActivity=new MainLoginActivity(this.editTextUsername,this.editTextPass, main_actitivity.this, this.progressBar);
                break;
        }

    }
}
