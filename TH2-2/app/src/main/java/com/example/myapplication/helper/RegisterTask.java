package com.example.myapplication.helper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.activities.main_actitivity;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
public class RegisterTask extends AsyncTask<Void, Void, String> {
    private ProgressBar progressBar;
    private String username, email, password;
    private Context context;
    private boolean check;

    public RegisterTask(ProgressBar progressBar, String username, String email, String password, Context context1) {
        this.progressBar = progressBar;
        this.username = username;
        this.email = email;
        this.password = password;
        this.context=context1;
        this.check=true;
    }

    public boolean isCheck() {
        return check;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... voids) {
        RequestHandler requestHandler = new RequestHandler();

        Log.i("Username: ", username);
        HashMap<String,String> params=new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("password", password);
        return requestHandler.sendPostRequest(urls.URL_REGISTER, params);
    }

    @Override
    protected void onPostExecute(String s) { // callback
        super.onPostExecute(s);
        progressBar.setVisibility(View.GONE);
        try {
            JSONObject obj = new JSONObject(s);

            if (!obj.getBoolean("error")) {
                Toast.makeText(this.context, obj.getString("message"), Toast.LENGTH_LONG).show();
                Intent intent =new Intent(this.context, main_actitivity.class);
                this.context.startActivity(intent);
            }
            else {
                Toast.makeText(this.context, obj.getString("message"), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(this.context, MainActivity.class);
                this.context.startActivity(intent);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
