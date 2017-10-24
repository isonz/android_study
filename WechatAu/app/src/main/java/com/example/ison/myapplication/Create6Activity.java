package com.example.ison.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class Create6Activity extends AppCompatActivity
{
    private Context context = this;
    private static final String TAG = "Create6Activity";

    private LinearLayout markLayout;
    private ImageView share_image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create6);

        ImageView returnBtn = (ImageView)findViewById(R.id.return_btn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        Button backBtn = (Button)findViewById(R.id.create1_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button nextBtn = (Button)findViewById(R.id.create1_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CartActivity.class);
                startActivity(intent);
            }
        });



        share_image = (ImageView) findViewById(R.id.share_image);
        markLayout = (LinearLayout) findViewById(R.id.mark_layout);
        Button shareBtn = (Button) findViewById(R.id.share_btn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
                markLayout.getLayoutParams().height = mainLayout.getHeight();
                markLayout.setVisibility(View.VISIBLE);
                share_image.setVisibility(View.VISIBLE);
            }
        });
        markLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share_image.setVisibility(View.GONE);
                markLayout.setVisibility(View.GONE);
            }
        });

    }


    //Activity被覆盖到下面或者锁屏时被调用
    @Override
    protected void onPause() {
        super.onPause();
        //overridePendingTransition(R.anim.right_in, R.anim.origin);
        Log.i(TAG, "onPause called.");
        //有可能在执行完onPause或onStop后,系统资源紧张将Activity杀死,所以有必要在此保存持久数据
    }

}
