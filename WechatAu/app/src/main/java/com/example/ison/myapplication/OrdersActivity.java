package com.example.ison.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class OrdersActivity extends AppCompatActivity
{
    private Context context = this;
    private static final String TAG = "AccountActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ImageView returnBtn = (ImageView)findViewById(R.id.return_btn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        View orderInfo1 = (View)findViewById(R.id.order_list_1_info);
        View orderInfo2 = (View)findViewById(R.id.order_list_2_info);
        Button orderInfoBtn1 = (Button)findViewById(R.id.order_list_1_info_btn);
        Button orderInfoBtn2 = (Button)findViewById(R.id.order_list_2_info_btn);
        orderInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrdersInfoActivity.class);
                startActivity(intent);
            }
        });
        orderInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrdersInfoActivity.class);
                startActivity(intent);
            }
        });
        orderInfoBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrdersInfoActivity.class);
                startActivity(intent);
            }
        });
        orderInfoBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrdersInfoActivity.class);
                startActivity(intent);
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
