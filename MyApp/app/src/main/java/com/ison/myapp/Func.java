package com.ison.myapp;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Func
{
    public static List<Map<String, Object>> jsonArry2ListMap(JSONArray jsonArray){
        List<Map<String, Object>> list = null;
        try {
            JSONObject jsonObj ;
            list = new ArrayList<Map<String,Object>>();
            for(int i = 0 ; i < jsonArray.length() ; i ++){
                jsonObj = (JSONObject)jsonArray.get(i);
                list.add(json2Map(jsonObj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, Object> json2Map(JSONObject json){
        try {
            Iterator<String> keyIter= json.keys();
            String key;
            Object value ;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = json.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Dialog loadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v
                .findViewById(R.id.dialog_loading_view);// 加载布局
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.MyDialogStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        /** *将显示Dialog的方法封装在这里面 */
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);
        loadingDialog.show();

        return loadingDialog;
    }
    /** * 关闭dialog * * @param mDialogUtils */
    public static void closeDialog(Dialog mDialogUtils) {
        if (mDialogUtils != null && mDialogUtils.isShowing()) {
            mDialogUtils.dismiss();
        }
    }

    public static void main(String args[])
    {
        //

    }


}
