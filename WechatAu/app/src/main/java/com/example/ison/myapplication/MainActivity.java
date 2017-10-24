package com.example.ison.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{
    private Context context = this;
    private static final String TAG = "MainActivity";
    private int param = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button navHomeBtn = (Button)findViewById(R.id.nav_home);
        Button navCartBtn = (Button)findViewById(R.id.nav_cart);
        Button navAccountBtn = (Button)findViewById(R.id.nav_account);

        try{
            navHomeBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.color_black));
            navHomeBtn.setTextColor(ContextCompat.getColor(context, R.color.color_red));
            navHomeBtn.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(context,R.drawable.home_active), null , null);
        }catch (Resources.NotFoundException e){
            e.printStackTrace();
        }


        navCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CartActivity.class);
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


        Button homeSearchBtn = (Button)findViewById(R.id.home_search_btn);
        homeSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrdersActivity.class);
                startActivity(intent);
            }
        });


        ImageView createImage = (ImageView)findViewById(R.id.create_image);
        Button createBtn = (Button)findViewById(R.id.home_product_create);
        createImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CreateActivity.class);
                startActivity(intent);
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CreateActivity.class);
                startActivity(intent);
            }
        });


        TextView textText = (TextView)findViewById(R.id.test_text);
        textText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TestActivity.class);
                startActivity(intent);
            }
        });

        //handler.postDelayed(updateTimer, 20000);  //設定Delay的時間
    }


    //Activity创建或者从后台重新回到前台时被调用
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart called.");
    }

    //Activity从后台重新回到前台时被调用
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart called.");
    }

    //Activity创建或者从被覆盖、后台重新回到前台时被调用
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called.");
    }

    //Activity窗口获得或失去焦点时被调用,在onResume之后或onPause之后
    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i(TAG, "onWindowFocusChanged called.");
    }*/

    //Activity被覆盖到下面或者锁屏时被调用
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.left_out, R.anim.origin);
        Log.i(TAG, "onPause called.");
        //有可能在执行完onPause或onStop后,系统资源紧张将Activity杀死,所以有必要在此保存持久数据
    }

    //退出当前Activity或者跳转到新Activity时被调用
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called.");
    }

    //退出当前Activity时被调用,调用之后Activity就结束了
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestory called.");
    }

    /**
     * Activity被系统杀死时被调用.
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死.
     * 另外,当跳转到其他Activity或者按Home键回到主屏时该方法也会被调用,系统是为了保存当前View组件的状态.
     * 在onPause之前被调用.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("param", param);
        Log.i(TAG, "onSaveInstanceState called. put param: " + param);
        super.onSaveInstanceState(outState);
    }

    /**
     * Activity被系统杀死后再重建时被调用.
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死,用户又启动该Activity.
     * 这两种情况下onRestoreInstanceState都会被调用,在onStart之后.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        param = savedInstanceState.getInt("param");
        Log.i(TAG, "onRestoreInstanceState called. get param: " + param);
        super.onRestoreInstanceState(savedInstanceState);
    }

    private Runnable updateTimer = new Runnable() {
        public void run() {

        }
    };

}
