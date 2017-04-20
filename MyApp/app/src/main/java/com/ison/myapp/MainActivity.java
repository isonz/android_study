package com.ison.myapp;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    private Context context = this;
    private int param = 1;
    List<Map<String, Object>> list;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        /* @1
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.myarray,android.R.layout.simple_list_item_multiple_choice );
        ListView list_test = (ListView) findViewById(R.id.list_test1);
        list_test.setAdapter(adapter);
        */


        /* @2
        //要显示的数据
        String[] strs = {"基神","B神","翔神","曹神","J神"};
        //创建ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, strs);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        ListView list_test = (ListView) findViewById(R.id.list_test1);
        list_test.setAdapter(adapter);
        */


        /* @3
        String[] names = new String[]{"A神", "基神", "曹神", "A神", "基神", "曹神", "A神", "基神", "曹神","A神", "基神", "曹神"};
        String[] says = new String[]{"无形被黑，最为致命", "大神好厉害~", "我将带头~","无形被黑，最为致命", "大神好厉害~", "我将带头~","无形被黑，最为致命", "大神好厉害~", "我将带头~","无形被黑，最为致命", "大神好厉害~", "我将带头~"};
        int[] imgIds = new int[]{R.mipmap.touxiang1, R.mipmap.touxiang2, R.mipmap.touxiang3,R.mipmap.touxiang1, R.mipmap.touxiang2, R.mipmap.touxiang3,R.mipmap.touxiang1, R.mipmap.touxiang2, R.mipmap.touxiang3,R.mipmap.touxiang1, R.mipmap.touxiang2, R.mipmap.touxiang3};

        List<Map<String, Object>> listitem = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> showitem = new HashMap<String, Object>();
            showitem.put("touxiang", imgIds[i]);
            showitem.put("name", names[i]);
            showitem.put("says", says[i]);
            listitem.add(showitem);
        }

        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), listitem, R.layout.list_item, new String[]{"touxiang", "name", "says"}, new int[]{R.id.imgtou, R.id.name, R.id.says});
        ListView listView = (ListView) findViewById(R.id.list_test);
        listView.setAdapter(myAdapter);
        */

        /*
        Button btn = (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserInfoActivity.class);
                startActivity(intent);
            }
        });
        */

        String jsonstr = "[{'id':'1021','name':'AAAAA','say':'无形被黑，最为致命','touxiang':2130903046},{'id':'1032','name':'BBBBB','say':'大神好厉害~','touxiang':2130903047},{'id':'1043','name':'CCCCC','say':'我将带头~','touxiang':2130903048},{'id':'1054','name':'DDDDD','say':'无形被黑，最为致命~','touxiang':2130903046},{'id':'1065','name':'EEEEE','say':'大神好厉害~','touxiang':2130903047},{'id':'1076','name':'FFFFF','say':'我将带头~','touxiang':2130903048},{'id':'1087','name':'GGGGG','say':'无形被黑，最为致命~','touxiang':2130903046},{'id':'1098','name':'HHHHH','say':'大神好厉害~','touxiang':2130903047},{'id':'1109','name':'IIIII','say':'我将带头~','touxiang':2130903048},{'id':'1110','name':'JJJJJ','say':'无形被黑，最为致命~','touxiang':2130903046},{'id':'1121','name':'KKKKK','say':'大神好厉害~','touxiang':2130903047},{'id':'1132','name':'LLLLL','say':'我将带头~','touxiang':2130903048}]";
        try {
            jsonArray = new JSONArray(jsonstr);
        }catch (JSONException e){
            e.printStackTrace();
        }

        list = Func.jsonArry2ListMap(jsonArray);
        Log.i("list", list.toString());

        String[] titles = new String[]{"name", "says", "touxiang"};
        int[] viewid = new int[]{R.id.name, R.id.says, R.id.imgtou};

        //创建一个simpleAdapter
        SimpleAdapter myAdapter = new SimpleAdapter(getApplicationContext(), list, R.layout.list_item, titles, viewid);
        ListView listView = (ListView) findViewById(R.id.list_test);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, UserInfoActivity.class);
                try {
                    intent.putExtra("json", jsonArray.get(position).toString());
                    Log.i("json", jsonArray.get(position).toString());
                }catch (JSONException e){
                    e.printStackTrace();
                }
                startActivity(intent);
            }
        });

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

}
