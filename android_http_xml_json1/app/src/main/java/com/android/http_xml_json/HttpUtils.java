package com.android.http_xml_json;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtils {
	
	public static InputStream getXML(String url_path) {
		InputStream inputStream = null;
		HttpURLConnection connection = null;
		try {
			URL url = new URL(url_path);
			if (url != null) {
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Content-type", "text/xml");
				connection.setRequestProperty("Accept-Charset", "utf-8");
				connection.setRequestProperty("contentType", "utf-8");
				connection.setConnectTimeout(10000);
				connection.setReadTimeout(10000);

				if(connection.getResponseCode() == 200){
					inputStream = connection.getInputStream();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return inputStream;
	}

	public static String getJsonContent(String url_path){
		HttpURLConnection connection = null;
		try {
			URL url = new URL(url_path);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-type", "text/html");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("contentType", "utf-8");
			connection.setConnectTimeout(10000);
			connection.setReadTimeout(10000);

			if(connection.getResponseCode() == 200){
				return changeInputStream4PHP(connection.getInputStream());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return "";
	}
	
	@SuppressWarnings("unused")
	private static String changeInputStream(final InputStream inputStream) throws IOException{
		String jsonString = "";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] data = new byte[1024];
		try {
			while ((len = inputStream.read()) !=-1) {
				outputStream.write(data, 0, len);
			}
			jsonString = new String(outputStream.toByteArray());
			inputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jsonString;
	}
	
	public static String changeInputStream4PHP(InputStream inStream) throws IOException{
		String jsonString = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "utf8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";  
        while ((line = in.readLine()) != null){  
          buffer.append(line);  
        }
        jsonString = buffer.toString();       
        return jsonString;
	}
	
}
