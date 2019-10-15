package com.example.lab2.activities;

import android.content.Intent;
import android.os.Bundle;
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

public class register extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = register.this;

    private EditText email;
    private EditText username;
    private EditText password;
    private Button register;
    private Button back;
    private SQLiteConnector sqLiteConnector;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();
        initView();
        initListener();
        initObjects();
    }

    private void initView(){
        email = findViewById(R.id.email);
        username=findViewById(R.id.username2);
        password=findViewById(R.id.password2);
        register=findViewById(R.id.register2);
        back=findViewById(R.id.back);
    }

    private void initListener(){
        register.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void initObjects(){
        sqLiteConnector = new SQLiteConnector(this);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register2:
                postDataToSQLite();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Backing", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(activity, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void postDataToSQLite() {
        Toast.makeText(getApplicationContext(), "Info....", Toast.LENGTH_LONG).show();
        if(!sqLiteConnector.checkUser(username.getText().toString().trim(), password.getText().toString().trim())){
            user.setEmail(email.getText().toString().trim());
            user.setUsername(username.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());

            sqLiteConnector.addUser(user);

            Toast.makeText(getApplicationContext(), "Successful to resgister....", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(activity, LoginActivity.class);
            emptyInput();
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), "Error in resgistering ...", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(activity, MainActivity.class);
            emptyInput();
            startActivity(intent);
        }

    }

    private void emptyInput() {
        email.setText("");
        username.setText("");
        password.setText("");
    }
}
