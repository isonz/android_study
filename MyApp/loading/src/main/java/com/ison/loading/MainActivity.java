package com.ison.loading;

import android.app.Dialog;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import com.ison.loading.utils.zlib.Func;

public class MainActivity extends AppCompatActivity
{
    private Dialog mDialog;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Func.closeDialog(mDialog);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialog = Func.loadingDialog(this, "加载中...");

        //Func.closeDialog(loadingDialog);
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }
}
