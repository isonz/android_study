package com.ison.myapp;

import com.ison.myapp.utils.zlib.*;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class UserInfoActivity extends AppCompatActivity
{
    RestTemplate restTemplate = new RestTemplate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        Dialog loadingDialog = Func.loadingDialog(this, "加载中...");

        String url = "http://auth.ptp.cn/public/checkLogin";
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        new InternetLinkTask().execute(url);
        //Log.i("result -----------------:", result);



        Bundle bd = getIntent().getExtras();
        String jstr = bd.getString("json");
        long id=0l;
        String name = "";
        String say="";
        int touxiang=0;
        try {
            JSONObject json = new JSONObject(jstr);
            id =  Long.parseLong(json.getString("id"));
            name = json.getString("name");
            say = json.getString("say");
            touxiang = Integer.parseInt(json.getString("touxiang"));
        }catch (JSONException e){
            e.printStackTrace();
        }
        Log.i("jstr-----------------:", jstr);
        Log.i("id-----------------:", id+"");


        TextView textview = (TextView) findViewById(R.id.info_id);
        textview.setText(String.valueOf(id));
        EditText editTextName = (EditText) findViewById(R.id.info_name);
        editTextName.setText(name);
        EditText editTextSay = (EditText) findViewById(R.id.info_say);
        editTextSay.setText(say);
        ImageView imageView = (ImageView) findViewById(R.id.info_touxiang);
        imageView.setImageResource(touxiang);

        //SystemClock.sleep(3000);
        //Func.closeDialog(loadingDialog);
    }


    /** 线程类，用于异步执行web访问任务 **/
    class InternetLinkTask extends AsyncTask<String,Void,String> {

        protected String doInBackground(String... params) {
            String result = restTemplate.getForObject(params[0], String.class, "Android");
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}
