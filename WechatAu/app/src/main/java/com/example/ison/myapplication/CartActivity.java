package com.example.ison.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class CartActivity extends AppCompatActivity
{
    private Context context = this;
    private static final String TAG = "CartActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        Button navHomeBtn = (Button)findViewById(R.id.nav_home);
        Button navCartBtn = (Button)findViewById(R.id.nav_cart);
        Button navAccountBtn = (Button)findViewById(R.id.nav_account);

        try{
            navCartBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.color_black));
            navCartBtn.setTextColor(ContextCompat.getColor(context, R.color.color_red));
            navCartBtn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(context,R.drawable.cart_active), null , null);
        }catch (Resources.NotFoundException e){
            e.printStackTrace();
        }

        navHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                //intent.putExtra("name", "abc");
                startActivity(intent);
            }
        });
        navAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AccountActivity.class);
                //intent.putExtra("name", "abc");
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
