package com.example.myapplication.helper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.SuccessfulPage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginTask extends AsyncTask<Void, Void, String> {
    private ProgressBar progressBar;
    private String usernameStr, paswordStr;
    private Context context;

    public LoginTask(ProgressBar progressBar, String usernameStr, String paswordStr, Context context) {
        this.progressBar = progressBar;
        this.usernameStr = usernameStr;
        this.paswordStr = paswordStr;
        this.context=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.setVisibility(View.GONE);
        try {
            JSONObject obj = new JSONObject(s);

            if (!obj.getBoolean("error")) {
                Toast.makeText(this.context, obj.getString("message"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.context, SuccessfulPage.class);
                this.context.startActivity(intent);
            }
            else {
                Toast.makeText(this.context, obj.getString("message"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this.context, MainActivity.class);
                this.context.startActivity(intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        RequestHandler requestHandler = new RequestHandler();

        HashMap<String, String> params = new HashMap<>();
        params.put("username", usernameStr);
        params.put("password", paswordStr);

        return requestHandler.sendPostRequest(urls.URL_LOGIN, params);
    }
}
