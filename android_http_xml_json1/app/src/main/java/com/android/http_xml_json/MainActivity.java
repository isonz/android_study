package com.android.http_xml_json;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn9;
	private String url="http://192.168.75.99:9091/json";
	private final static String TAG = "MainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn1Click();
		btn2Click();
		btn3Click();
		btn4Click();
		btn5Click();
		btn6Click();
		btn7Click();
		btn9Click();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//json signle
	private void btn1Click() {
		btn1 = (Button)this.findViewById(R.id.button1);
		btn1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				/*
				switch (v.getId()) {
				case R.id.button1:
					break;
				default:
					break;
				}
				*/
				//--android4.0以上需要另起线程 
				new Thread(new Runnable(){public void run(){
					String jsonString = HttpUtils.getJsonContent(url);
					Person person = Person.jsonPerson("person", jsonString);				
					Log.i(TAG, person.getName());
					Log.i(TAG, person.getSex());	
					Log.i(TAG, String.valueOf(person.getAge()));
				}}).start();
			}
		});
		
	}
	
	//json list
	private void btn2Click() {
		btn2 = (Button)this.findViewById(R.id.button2);
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//--android4.0以上需要另起线程 
				new Thread(new Runnable(){public void run(){
					String jsonString = HttpUtils.getJsonContent(url);
					List<Person> list = Person.jsonPersons("persons", jsonString);
					for(int i = 0; i < list.size(); i++){  
			            Log.i(TAG, "json list:"+list.get(i).getName() +"<-->"+list.get(i).getAge()+"<-->"+list.get(i).getSex());
			            //System.out.println(list.get(i));  
			        }
				}}).start();
			}
		});
	}
	
	// json map
	private void btn3Click() {
		btn3 = (Button)this.findViewById(R.id.button3);
		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//--android4.0以上需要另起线程 
				new Thread(new Runnable(){public void run(){
					String jsonString = HttpUtils.getJsonContent(url);
					List<Map<String, Object>> list = Person.listMaps("persons", jsonString);
					//Log.i(TAG, list.toString());
					for(int i = 0; i < list.size(); i++){  
			            //Log.i(TAG, list.get(i).toString());
						try {
							JSONObject obj = new JSONObject(list.get(i).toString());
							//System.out.println(obj.getString("name"));
							Log.i(TAG, "json map:"+obj.getString("name")+"<-->"+obj.getString("age")+"<-->"+obj.getString("sex"));
						} catch (JSONException e) {
							e.printStackTrace();
						}
			            //System.out.println(list.get(i));  
			        }
					
				}}).start();
			}
		});
	}
	
	//Gson One
	private void btn4Click() {
		btn4 = (Button)this.findViewById(R.id.button4);
		btn4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//--android4.0以上需要另起线程 
				new Thread(new Runnable(){public void run(){
					String jsonString = HttpUtils.getJsonContent(url+"?a=person");
					Person person = Person.gsonPerson(jsonString);
					Log.i(TAG,jsonString);
					Log.i(TAG, "gson one:"+ person.getName() +"<-->"+ person.getAge()+"<-->"+ person.getSex());
				}}).start();
			}
		});
	}
		
	//Gson list
	private void btn5Click() {
		btn5 = (Button)this.findViewById(R.id.button5);
		btn5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//--android4.0以上需要另起线程 
				new Thread(new Runnable(){public void run(){
					String jsonString = HttpUtils.getJsonContent(url+"?a=persons");
					List<Person> list = Person.gsonPersons(jsonString);
					Log.i(TAG,jsonString);
					for(int i = 0; i < list.size(); i++){  
						Log.i(TAG, "gson list:"+ list.get(i).getName() +"<-->"+list.get(i).getAge()+"<-->"+list.get(i).getSex());
			            //System.out.println(list.get(i));  
			        }
				}}).start();
			}
		});
	}
	
	//fastjson one
	private void btn6Click() {
		btn6 = (Button)this.findViewById(R.id.button6);
		btn6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//--android4.0以上需要另起线程 
				new Thread(new Runnable(){public void run(){
					String jsonString = HttpUtils.getJsonContent(url+"?a=person");
					Person person = Person.fastjsonPerson(jsonString, Person.class);
					Log.i(TAG,jsonString);
					Log.i(TAG, "fastjson one:"+ person.getName() +"<-->"+ person.getAge()+"<-->"+ person.getSex());
				}}).start();
			}
		});
	}
	
	//fastjson list
	private void btn7Click() {
		btn7 = (Button)this.findViewById(R.id.button7);
		btn7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//--android4.0以上需要另起线程 
				new Thread(new Runnable(){public void run(){
					String jsonString = HttpUtils.getJsonContent(url+"?a=persons");
					List<Person> list = Person.fastjsonPersons(jsonString, Person.class);
					Log.i(TAG,jsonString);
					for(int i = 0; i < list.size(); i++){  
						Log.i(TAG, "fastjson list:"+list.get(i).getName() +"<-->"+list.get(i).getAge()+"<-->"+list.get(i).getSex());
			            //System.out.println(list.get(i));  
			        }
				}}).start();
			}
		});
	}
		
	//PULL XML list
	private void btn9Click() {
		btn9 = (Button)this.findViewById(R.id.button9);
		btn9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//--android4.0以上需要另起线程 
				new Thread(new Runnable(){public void run(){
					InputStream inputStream = HttpUtils.getXML("http://192.168.75.99:9091/xml");					
					List<Person> list = null;
					try {
						//String xml = HttpUtils.changeInputStream4PHP(inputStream);
						//System.out.println(xml);  
						list = Person.pullXmlPersons(inputStream, "UTF-8");
						for(Person person : list){  
							Log.i(TAG, "Pull XML list:"+person.getId() +"<-->"+person.getName() +"<-->"+person.getAge());
				            //System.out.println(person.toString());  
				        }
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}}).start();
			}
		});
	}
	
}
