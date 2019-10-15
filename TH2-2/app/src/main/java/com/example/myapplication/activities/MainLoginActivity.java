package com.example.myapplication.activities;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.helper.LoginTask;

public class MainLoginActivity {
    private EditText usernameEdt, passwordEdt;
    private Context context;
    private ProgressBar progressBar;

    public MainLoginActivity(EditText usernameEdt, EditText passwordEdt, Context context, ProgressBar progressBar) {
        this.usernameEdt = usernameEdt;
        this.passwordEdt = passwordEdt;
        this.context=context;
        this.progressBar=progressBar;
        userLogin();
    }

    public EditText getUsernameEdt() {
        return usernameEdt;
    }

    public void setUsernameEdt(EditText usernameEdt) {
        this.usernameEdt = usernameEdt;
    }

    public EditText getPasswordEdt() {
        return passwordEdt;
    }

    public void setPasswordEdt(EditText passwordEdt) {
        this.passwordEdt = passwordEdt;
    }

    private void userLogin(){
        final String usernameStr=usernameEdt.getText().toString().trim();
        final String passwordStr=passwordEdt.getText().toString().trim();

        if(TextUtils.isEmpty(usernameStr)){
            usernameEdt.setError("Please enter username");
            usernameEdt.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(passwordStr)){
            passwordEdt.setError("Please enter password");
            passwordEdt.requestFocus();
            return;
        }
        LoginTask loginTask=new LoginTask(this.progressBar, usernameStr, passwordStr, this.context);
        loginTask.execute();
    }
}
