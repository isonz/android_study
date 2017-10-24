package com.example.ison.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class BuyActivity extends AppCompatActivity
{
    private Context context = this;
    private static final String TAG = "BuyActivity";
    private LinearLayout markLayout;
    private LinearLayout inputName;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        ImageView returnBtn = (ImageView)findViewById(R.id.return_btn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        resources = this.getResources();

        inputName = (LinearLayout) findViewById(R.id.input_name);
        markLayout = (LinearLayout) findViewById(R.id.mark_layout);
        RelativeLayout orderMaker = (RelativeLayout) findViewById(R.id.order_maker);
        //点击输入用户名称地方显示蒙版和对话框
        orderMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
                markLayout.getLayoutParams().height = mainLayout.getHeight();
                markLayout.setVisibility(View.VISIBLE);
                inputName.setVisibility(View.VISIBLE);

                /*
                inputName.post(new Runnable(){
                    public void run(){
                        //获取对屏幕高度，使对话框在屏幕底部
                        DisplayMetrics dm = resources.getDisplayMetrics();
                        float density = dm.density;
                        int windowWidth = dm.widthPixels;
                        int windowHeight = dm.heightPixels;

                        //RelativeLayout为父级控件
                        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        lp.setMargins(0, windowHeight-inputName.getHeight(), 0, 0);
                        inputName.getLayoutParams().width=windowWidth;
                        inputName.setLayoutParams(lp);
                        //--- end 使对话框在屏幕底部
                    }
                });
                */
            }
        });


        //点击蒙版隐藏对话框和蒙版
        markLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputName.setVisibility(View.GONE);
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
