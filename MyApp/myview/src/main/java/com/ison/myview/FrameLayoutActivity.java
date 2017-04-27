package com.ison.myview;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.graphics.drawable.Drawable;

import com.ison.myview.entity.MeziView;


public class FrameLayoutActivity extends AppCompatActivity
{
    //初始化变量,帧布局
    FrameLayout frame = null;
    private boolean isBackPressed = false;



    //自定义一个用于定时更新UI界面的handler类对象
    Handler handler = new Handler()
    {
        int i = 0;
        @Override
        public void handleMessage(Message msg) {
            //判断信息是否为本应用发出的
            if(msg.what == 0x123)
            {
                i++;
                move(i % 8 );
            }
            super.handleMessage(msg);
        }
    };

    //定义走路时切换图片的方法
    void move(int i)
    {
        Drawable a = getResources().getDrawable(R.drawable.s_1);
        Drawable b = getResources().getDrawable(R.drawable.s_2);
        Drawable c = getResources().getDrawable(R.drawable.s_3);
        Drawable d = getResources().getDrawable(R.drawable.s_4);
        Drawable e = getResources().getDrawable(R.drawable.s_5);
        Drawable f = getResources().getDrawable(R.drawable.s_6);
        Drawable g = getResources().getDrawable(R.drawable.s_7);
        Drawable h = getResources().getDrawable(R.drawable.s_8);
        //通过setForeground来设置前景图像
        switch(i)
        {
            case 0:
                frame.setForeground(a);
                break;
            case 1:
                frame.setForeground(b);
                break;
            case 2:
                frame.setForeground(c);
                break;
            case 3:
                frame.setForeground(d);
                break;
            case 4:
                frame.setForeground(e);
                break;
            case 5:
                frame.setForeground(f);
                break;
            case 6:
                frame.setForeground(g);
                break;
            case 7:
                frame.setForeground(h);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //========== 动画
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        frame = (FrameLayout) findViewById(R.id.myframe);
        //定义一个定时器对象,定时发送信息给handler
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                //发送一条空信息来通知系统改变前景图片
                handler.sendEmptyMessage(0x123);
            }
        }, 0,170);



        //========== 跟随
        final MeziView mezi = new MeziView(FrameLayoutActivity.this);
        //为我们的萌妹子添加触摸事件监听器
        mezi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //设置妹子显示的位置
                mezi.bitmapX = event.getX() - 350;      //不同的图片尺寸值不同，取图中心。
                mezi.bitmapY = event.getY() - 450;
                //调用重绘方法
                mezi.invalidate();
                return true;
            }
        });
        frame.addView(mezi);

    }


    //Activity创建或者从后台重新回到前台时被调用
    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.left_out, R.anim.origin);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isBackPressed = true;
        overridePendingTransition(0, R.anim.right_in);
    }

}
