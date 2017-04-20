package com.ison.myapp;

import android.app.Dialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfoActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Dialog loadingDialog = Func.loadingDialog(this, "加载中。。。。。。");

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

}
