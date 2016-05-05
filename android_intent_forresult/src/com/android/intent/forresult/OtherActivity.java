package com.android.intent.forresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OtherActivity extends Activity {

	private Button btn1;
	private TextView textView1;
	private EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
		btn1 = (Button)this.findViewById(R.id.button2);
		textView1 = (TextView)this.findViewById(R.id.msg);
		editText = (EditText)this.findViewById(R.id.three);
		Intent intent = getIntent();
		int a = intent.getIntExtra("a", 0);
		int b = intent.getIntExtra("b", 0);
		textView1.setText(a +"+"+ b+"=?");
		
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				int three = Integer.parseInt(editText.getText().toString());
				intent.putExtra("three", three);
				setResult(2, intent);
				finish();//结束当前的ACTIVITY的生命周期。
			}
		});
	}
}
