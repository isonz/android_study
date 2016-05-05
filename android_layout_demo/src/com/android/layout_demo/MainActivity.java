package com.android.layout_demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.R.anim;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.app.Activity;

public class MainActivity extends Activity implements OnClickListener, OnTouchListener, OnFocusChangeListener, android.view.View.OnKeyListener{

	private int value = 1; //用于改变按钮的大小
	private Button button1, button2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//mainLayoutDemo()
		//TextViewDemo();
		//editTextDemo();
		//radioDemo();
		//toggleButtonDemo();
		//checkBoxDemo();
		//seekbarDemo();
		imageViewBaseDemo();
		bottonDemo();
	}
	
	//------------------------------------------------------------------------------------------------------------- imageViewDemo
	public void imageViewBaseDemo() {
		setContentView(R.layout.imageview_base);
		ImageView imageView1 = (ImageView)this.findViewById(R.id.imageviewbase1);
		ImageView imageView2 = (ImageView)this.findViewById(R.id.imageviewbase2);
		//设置第一个图片的比例大小,表示宽度是200，宽度是100.
		imageView1.setLayoutParams(new LinearLayout.LayoutParams(200,100));
		setTitle("height:"+imageView1.getLayoutParams().height+"--width:"+imageView1.getLayoutParams().width);
	}
	//------------------------------------------------------------------------------------------------------------- imageViewDemo
		
	//------------------------------------------------------------------------------------------------------------- seekbarDemo
	private TextView textview1_seekbar, textview2_seekbar;
	public void seekbarDemo() {
		setContentView(R.layout.seekbar);
		textview1_seekbar = (TextView)this.findViewById(R.id.textview1_seekbar);
		textview2_seekbar = (TextView)this.findViewById(R.id.textview2_seekbar);
		SeekBar seekBar1 = (SeekBar)this.findViewById(R.id.seekbar1);
		SeekBar seekBar2 = (SeekBar)this.findViewById(R.id.seekbar2);
		
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			//拖动滑杆时触发
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				textview1_seekbar.setText("SEEKBAR1 停止拖动");
			}
			//从哪里开始拖动
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				textview1_seekbar.setText("SEEKBAR1 开始拖动");
			}
			//从哪里结束拖动
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				textview1_seekbar.setText("SEEKBAR1 的当前位置是：" + progress);
			}
		});
		seekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				textview2_seekbar.setText("SEEKBAR2 停止拖动");
			}
			//从哪里开始拖动
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				textview2_seekbar.setText("SEEKBAR2 开始拖动");
			}
			//从哪里结束拖动
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				textview2_seekbar.setText("SEEKBAR2 的当前位置是：" + progress);
			}
		});
	}
	//------------------------------------------------------------------------------------------------------------- END seekbarDemo
	
	//------------------------------------------------------------------------------------------------------------- checkBoxDemo
	private Button checkbox_button1;
	private List<CheckBox> checkBoxs = new ArrayList<CheckBox>();
	public void checkBoxDemo() {
		//setContentView(R.layout.checkbox);
		String[] checkboxText = new String[]{"qqqqqqq","ffffffffffff", "ggggggggggg","hhhhhhhhh"};
		LinearLayout linearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.checkbox_main, null);	//动态加载布局
		//给指定的CHECKBOX赋值
		for (int i = 0; i < checkboxText.length; i++) {
			//先获得 checkbox.xml对象
			CheckBox checkBox = (CheckBox) getLayoutInflater().inflate(R.layout.checkbox, null);
			checkBoxs.add(checkBox);
			checkBoxs.get(i).setText(checkboxText[i]);
			linearLayout.addView(checkBox, i);
		}
		setContentView(linearLayout);
		checkbox_button1 = (Button)this.findViewById(R.id.checkbox_button1);
		checkbox_button1.setOnClickListener(new View.OnClickListener() {
			private Builder setPositiveButton;

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String string = "";
				for (CheckBox checkBox:checkBoxs) {
					if(checkBox.isChecked()){
						string += checkBox.getText() + "\n";
					}
				}
				if("".equals(string)){
					string = "您还没有选中选项";
				}
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setMessage(string).setPositiveButton("关闭", null).show();
			}
		});
	}
	//------------------------------------------------------------------------------------------------------------- END checkBoxDemo
	
	//------------------------------------------------------------------------------------------------------------- toggleButtonDemo
	private ToggleButton toggleButton;
	private Button button11, button12, button13;
	public void toggleButtonDemo() {
		setContentView(R.layout.toggle);
		toggleButton = (ToggleButton)this.findViewById(R.id.togglebutton);
		final LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.mylayout);
		toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					linearLayout.setOrientation(1); //表示设置垂直布局
				}else{
					linearLayout.setOrientation(0); //表示设置水平布局 
				}
			}
		});
	}
	//------------------------------------------------------------------------------------------------------------- END toggleButtonDemo

	//-------------------------------------------------------------------------------------------------------------radioDemo
	private RadioGroup group;
	private Button button10;
	public void radioDemo() {
		setContentView(R.layout.radio);
		group = (RadioGroup)this.findViewById(R.id.sex);
		button10 = (Button)this.findViewById(R.id.button1);
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int len = group.getChildCount();
				String msgString = "";
				for (int i = 0; i < len; i++) {
					RadioButton radioButton = (RadioButton) group.getChildAt(i);
					if(radioButton.isChecked()){
						msgString = radioButton.getText().toString();
						break;
					}
				}
				Toast.makeText(MainActivity.this, msgString, 1).show();
			}
		});
	}
	//-------------------------------------------------------------------------------------------------------------END radioDemo
	
	//------------ main
	public void mainLayoutDemo() {
		setContentView(R.layout.activity_main);
	}
	//------------ end main
	
	//-------------------------------------------------------------------------------------------------------------editText Demo
	private EditText editText1, editText2, editText3, editText4, editText5;
	private Button button1, button2;
	private AutoCompleteTextView autocptextview1;
	private MultiAutoCompleteTextView muautocptextview1;
	
	public void editTextDemo() {
		setContentView(R.layout.edittext);
		editText1 = (EditText)this.findViewById(R.id.edittext1);
	//-------------------------------------------------------------------------------------------------------------Botton Demo
	public void bottonDemo() {
		setContentView(R.layout.botton);
		button1 = (Button)this.findViewById(R.id.button1);
		button2 = (Button)this.findViewById(R.id.button2);		// background is image
		

		editText2 = (EditText)this.findViewById(R.id.edittext2);
		editText3 = (EditText)this.findViewById(R.id.edittext3);
		editText4 = (EditText)this.findViewById(R.id.edittext4);
		editText5 = (EditText)this.findViewById(R.id.edittext5);
		button2 = (Button)this.findViewById(R.id.button2);
		
		//添加表情图片到EDITTEXT
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				int randomid = 1+new Random().nextInt(8);
				try {
					Field field = R.drawable.class.getDeclaredField("q"+randomid);
					int resourceId = Integer.parseInt(field.get(null).toString());
					//在安卓先生图片信息，必须使用BITMAP位图的对象来装载
					Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
					ImageSpan imageSpan = new ImageSpan(MainActivity.this, bitmap);
					SpannableString spannableString = new SpannableString("qq");
					spannableString.setSpan(imageSpan, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					editText1.append(spannableString);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		
		//判断EDITTEXT是否为空
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String value2 = editText2.getText().toString();
				if(value2 == null || value2.trim().equals("")){
					editText2.setError("请输入内容");
					return ;
				}
			}
		});
		
		//单个自动填充
		autocptextview1 = (AutoCompleteTextView)this.findViewById(R.id.autocptextview1);
		String[] autoStrings = new String[]{"aaaaaaa","aabbb","cccccc","ccvvvv","bbbbbb"};
		//第二个参数表示适配器的下拉风格
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, autoStrings);
		autocptextview1.setAdapter(adapter);
		
		//多个自动填充
		muautocptextview1 = (MultiAutoCompleteTextView)this.findViewById(R.id.muautocptextview1);
		muautocptextview1.setAdapter(adapter);
		muautocptextview1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer()); //完成对选项的拆分功能，以逗号进行拆分

		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button2.setOnTouchListener(this);
		button2.setOnFocusChangeListener(this);
		button2.setOnKeyListener(this);
		
		
	}
	//-------------------------------------------------------------------------------------------------------------END Botton Demo
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(KeyEvent.ACTION_DOWN == event.getAction()){
			v.setBackgroundResource(R.drawable.botton3);
		}else if(KeyEvent.ACTION_UP == event.getAction()){
			v.setBackgroundResource(R.drawable.botton2);
		}
		return false;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if(hasFocus){
			button2.setBackgroundResource(R.drawable.botton2);
		}else{
			button2.setBackgroundResource(R.drawable.botton1);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_UP){
			v.setBackgroundResource(R.drawable.botton1);
		}else if(event.getAction() == MotionEvent.ACTION_DOWN){
			v.setBackgroundResource(R.drawable.botton2);
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Button button = (Button)v;
		if(value == 1 && button.getWidth()==getWindowManager().getDefaultDisplay().getWidth()){
			value = -1;
		}else if(value == -1 && button.getWidth() < 100){
			value = 1;
		}
		button.setWidth(button.getWidth() + (int)(button.getWidth()*0.1)*value);
		button.setHeight(button.getHeight() + (int)(button.getHeight()*0.1)*value);
	}
	

}
