package com.ison.myapp;

import com.ison.myapp.utils.zlib.*;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class UserInfoActivity extends AppCompatActivity
{
    private static final String TAG = "UserInfoActivity";
    private int param = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


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
        //Log.i("jstr-----------------:", jstr);
        //Log.i("id-----------------:", id+"");


        TextView textview = (TextView) findViewById(R.id.info_id);
        textview.setText(String.valueOf(id));
        EditText editTextName = (EditText) findViewById(R.id.info_name);
        editTextName.setText(name);
        EditText editTextSay = (EditText) findViewById(R.id.info_say);
        editTextSay.setText(say);
        ImageView imageView = (ImageView) findViewById(R.id.info_touxiang);
        imageView.setImageResource(touxiang);


        String url = "http://dev.placentin.com/test/json.php";
        String result = HttpRequest.getUrl(url);
        Log.i("result -----------------:", result);

        Func.closeDialog();
    }


    //Activity创建或者从后台重新回到前台时被调用
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart called.-----");

    }

    //Activity从后台重新回到前台时被调用
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart called.-----");
    }

    //Activity创建或者从被覆盖、后台重新回到前台时被调用
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume called.-----");

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
        Log.i(TAG, "onPause called.-----");
        //有可能在执行完onPause或onStop后,系统资源紧张将Activity杀死,所以有必要在此保存持久数据
    }

    //退出当前Activity或者跳转到新Activity时被调用
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop called.-----");
    }

    //退出当前Activity时被调用,调用之后Activity就结束了
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestory called.-----");
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

}
