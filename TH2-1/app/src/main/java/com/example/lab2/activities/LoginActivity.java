package com.example.lab2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2.MainActivity;
import com.example.lab2.R;
import com.example.lab2.database.SQLiteConnector;
import com.example.lab2.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private final AppCompatActivity activity = LoginActivity.this;

    private EditText usernameEdt;
    private EditText passwordEdt;
    private Button loginBtn;
    private Button registerBtn;
    private SQLiteConnector sqLiteConnector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        getSupportActionBar().hide();
        
        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        Log.e(TAG, "We're in initViews");
        usernameEdt =  findViewById(R.id.username);
        passwordEdt = findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.login);
        registerBtn = (Button) findViewById(R.id.register);

    }

    private void initListeners(){
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }

    private void initObjects(){
        sqLiteConnector = new SQLiteConnector(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                verifyFromSQLite();
                break;
            case R.id.register:
                Toast.makeText(getApplicationContext(), "Registering ...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(activity, register.class);
                startActivity(intent);
                break;
        }
    }

    private void verifyFromSQLite() {
        if(sqLiteConnector.checkUser(usernameEdt.getText().toString().trim(),
                User.md5(passwordEdt.getText().toString().trim()))) {
            Toast.makeText(getApplicationContext(), "Successful ...", Toast.LENGTH_LONG).show();
            emptyInput();
        }
        else {
            Toast.makeText(getApplicationContext(), "Fail ...", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(activity, MainActivity.class);
            emptyInput();
            startActivity(intent);
        }
    }

    private void emptyInput() {
        usernameEdt.setText("");
        passwordEdt.setText("");
    }


}
