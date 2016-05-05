package com.android.intent.forresult;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button btn1;
	private final static int REQUESTCODE = 1;
	private EditText one, two, result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button)this.findViewById(R.id.button1);
		one = (EditText)this.findViewById(R.id.one);
		two = (EditText)this.findViewById(R.id.two);
		result = (EditText)this.findViewById(R.id.result);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int a = Integer.parseInt(one.getText().toString());
				int b = Integer.parseInt(two.getText().toString());
				Intent intent = new Intent(MainActivity.this, OtherActivity.class);
				intent.putExtra("a", a);
				intent.putExtra("b", b);
				startActivityForResult(intent, REQUESTCODE); //表示可以返回结果
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == 2){	//2与OtherActivity的setResult方法中的标志位一样
			if(requestCode == REQUESTCODE){
				int three = data.getIntExtra("three", 0);
				result.setText(String.valueOf(three));
			}
		}
	}
	
	
}
