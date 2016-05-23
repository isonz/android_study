package com.baseimageview.ison.baseimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView1 = (ImageView)this.findViewById(R.id.imageview1);
        //设置第一张图片的比例大小，宽度200，高度100。
        imageView1.setLayoutParams(new LinearLayout.LayoutParams(200, 100));
        setTitle("Height:" + imageView1.getLayoutParams().height + "--width:>>"+imageView1.getLayoutParams().width );

    }
}
