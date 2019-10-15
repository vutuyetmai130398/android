package com.example.myapplication.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.helper.RegisterTask;


public class MainRegisterActivity extends AppCompatActivity{
    private EditText username;
    private EditText email;
    private EditText password;
    private Button register;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init(){
        username = findViewById(R.id.username2);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password2);
        register=findViewById(R.id.register2);
        progressBar=findViewById(R.id.progressBar);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser(){
        final String _username = username.getText().toString().trim();
        final String _password = password.getText().toString().trim();
        final String _email = email.getText().toString().trim();

        //validations
        if (TextUtils.isEmpty(_username)) {
            username.setError("Please enter username");
            username.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(_email)) {
            email.setError("Please enter your email");
            email.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(_email).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(_password)) {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }
        RegisterTask registerUSer = new RegisterTask(progressBar,_username, _email, _password, MainRegisterActivity.this);
        registerUSer.execute(); // asynchronous + callback
    }
}
