package com.example.etest;
/*
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //得到按钮实例
        Button hellobtn = (Button) findViewById(R.id.hellobutton);
        //设置监听按钮点击事件
        hellobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到textview实例
                TextView hellotv = (TextView) findViewById(R.id.hellotextView);
                //弹出Toast提示按钮被点击了
                Toast.makeText(MainActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
                //读取strings.xml定义的interact_message信息并写到textview上
                hellotv.setText(R.string.interact_message);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
*/

/*
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener
{
	Button btn;
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		btn = new Button(this);
		btn.setOnClickListener(this);
		updateTime();
		setContentView(btn);
	}
	
	public void onClick(View view)
	{
		updateTime();
	}
	
	private void updateTime()
	{
		btn.setText(new Date().toString());
	}
	
	
}
*/


import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity 
{
	WebView browser;
	ImageView image;
	ProgressBar probar;
	ProgressBar topprobar;
	TextView textview;
	int progressStatus = 0;
	Handler handler = new Handler();
	boolean doubleBackToExitPressedOnce = false;
	
    @SuppressLint("SetJavaScriptEnabled") @Override
    protected void onCreate(Bundle icicle) 
    {
    	super.onCreate(icicle);
    	requestWindowFeature(Window.FEATURE_PROGRESS);
    	//requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
    	//this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        image = (ImageView)findViewById(R.id.startImg);
        probar = (ProgressBar)findViewById(R.id.progressBar2);
        browser = (WebView)findViewById(R.id.webkit);
        //getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadUrl("http://m.ptp.cn/?cj=app");
        //browser.setInitialScale(1);
        //browser.getSettings().setBuiltInZoomControls(true);
        //browser.getSettings().setUseWideViewPort(true);
        browser.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress){
            	//MainActivity.this.setProgress(progress * 100);
            	TopProgressBar(progress);
                if(progress >= 100){
                	browser.setVisibility(View.VISIBLE);
                	image.setVisibility(View.INVISIBLE);
                	probar.setVisibility(View.INVISIBLE);
                }
            }
        });
        browser.setWebViewClient(new WebViewClient(){
        	public void onPageFinished(WebView view, String url) {
        		browser.setVisibility(View.VISIBLE);
            }
        });

        //browser.reload();
        //browser.goBack();
    }
    
    public void TopProgressBar(int progress)
    {
    	topprobar = (ProgressBar)findViewById(R.id.topprobar);
    	progressStatus = progress;
    	topprobar.setProgress(progressStatus);
    	/*
    	if(progress >= 100){
    		//topprobar.setVisibility(View.INVISIBLE);
    	}else{
    		//topprobar.setVisibility(View.VISIBLE);
	    	topprobar.setProgress(progressStatus);
    	}
    	*/
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
            case KeyEvent.KEYCODE_BACK:
                if(browser.canGoBack()){
                	browser.goBack();
                }else{
                    finish();
                }
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "再按一次返回后退应用", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;                       
            }
        }, 2000);
    } 
}

