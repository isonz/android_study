package com.android.http_xml_json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Person {

	private int id;
	private String name;
	private int age;
	private String sex;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public static void main(String[] args) {
		Person person = new Person();
		person.setName("ison.zhang");
		person.setAge(45);
		person.setSex("男");
		createJson(person);
	}
	
	/*
	 * json = {"person":{"name":"\u5f20\u73c5","age":27,"sex":"\u7537"},"partment":{"name":"\u670d\u52a1\u90e8","num":6},"persons":[{"name":"ison","age":23,"sex":"\u7537"},{"name":"katie","age":33,"sex":"\u5973"},{"name":"chen","age":45,"sex":"\u7537"},{"name":"yan","age":21,"sex":"\u5973"}],"partments":[{"name":"\u7535\u5546\u90e8","num":6},{"name":"\u54c1\u724c\u90e8","num":5},{"name":"\u5e02\u573a\u90e8","num":4},{"name":"\u4eba\u4e8b\u90e8","num":3}]}
	 */
	// json single
	public static Person jsonPerson(String key, String jsonString) {
		Person person = new Person();
		try {
			JSONObject obj = new JSONObject(jsonString);
			JSONObject personObject = obj.getJSONObject(key);
			person.setName(personObject.getString("name"));
			person.setAge(personObject.getInt("age"));
			person.setSex(personObject.getString("sex"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return person;
	}
	
	//json list 
	public static List<Person> jsonPersons(String key, String jsonString) {
		List<Person> list = new ArrayList<Person>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Person person = new Person();
				person.setName(jsonObject2.getString("name"));
				person.setAge(jsonObject2.getInt("age"));
				person.setSex(jsonObject2.getString("sex"));
				list.add(person);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	//json map
	public static List<Map<String, Object>> listMaps(String key, String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();
				Iterator<String> iterator = jsonObject2.keys();
				while (iterator.hasNext()) {
					String json_key = iterator.next();
					Object json_value = jsonObject2.get(json_key);
					if(json_value == null){
						json_value = "";
					}
					map.put(json_key, json_value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	//gson one
	public static Person gsonPerson(String jsonString) {
		Person person = new Person();
		try {
			Gson gson = new Gson();
			person = gson.fromJson(jsonString, new TypeToken<Person>(){}.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return person;
	}
		
	//gson list
	public static List<Person> gsonPersons(String jsonString) {
		List<Person> list = new ArrayList<Person>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(jsonString, new TypeToken<List<Person>>(){}.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	//fastjson one
	public static <T> T fastjsonPerson(String jsonString, Class<T> cls) {
		T t = null;
		try {
			t = JSON.parseObject(jsonString, cls);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return t;
	}
		
	//fastjson list
	public static <T> List<T> fastjsonPersons(String jsonString, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		try {
			list = JSON.parseArray(jsonString, cls);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	//xml='<persons><person id="1"><name>ison</name><age>23</age><sex>男</sex></person><person id="2"><name>katie</name><age>33</age><sex>女</sex></person><person id="3"><name>chen</name><age>45</age><sex>男</sex></person><person id="4"><name>yan</name><age>21</age><sex>女</sex></person></persons>';
	//pull xml list
	public static List<Person> pullXmlPersons(InputStream inputStream,  String encode) throws XmlPullParserException, IOException {
		List<Person> list = null;
		Person person = null;	//装载解析每一个person节点的内容
		
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inputStream, encode);
		
		//获得事件的类型
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				list = new ArrayList<Person>();
				break;
			case XmlPullParser.START_TAG:
				if ("person".equals(parser.getName())) {
					person = new Person();
					int id = Integer.parseInt(parser.getAttributeValue(null, "id"));
					person.setId(id);
				}else if("name".equals(parser.getName())){
					String name = parser.nextText();	//获取该节点的内容
					person.setName(name);
				}else if("age".equals(parser.getName())){
					int age = Integer.parseInt(parser.nextText());
					person.setAge(age);
				}
				break;
			case XmlPullParser.END_TAG:
				if ("person".equals(parser.getName())) {
					list.add(person);
					person = null;
				}
				break;
			default:
				break;
			}
			eventType = parser.next();
		}	
		return list;
	}
	
	//创建JSON字符串
	public static String createJsonString(String key, Object value){
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	//通过GSON创建JSON字符串
	public static String createJson(Object obj){
		Gson gson = new Gson();
		String gsonString = gson.toJson(obj);
		return gsonString;
	}
	
	
}
