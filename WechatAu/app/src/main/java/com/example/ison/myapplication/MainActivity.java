package com.example.ison.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private Context context = this;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //handler.postDelayed(updateTimer, 20000);  //設定Delay的時間
    }

    private Runnable updateTimer = new Runnable() {
        public void run() {

            //Intent intent = new Intent(context, HomeActivity.class);
            //startActivity(intent);
        }
    };

}
