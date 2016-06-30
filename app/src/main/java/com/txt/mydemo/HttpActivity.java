package com.txt.mydemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by txt on 2016/4/29.
 */
public class HttpActivity extends Activity {

    private String tag = HttpActivity.class.getSimpleName();
    private String mUrl = "http://cloud.bmob.cn/0906a62b462a3082/";
    private String mMethod = "getMemberBySex";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
    }

    public void urlConnectionGet(View view){
        //1、获得需要访问的server地址、方法、及参数键值
        final String serverAddress = mUrl + mMethod + "?" + "sex=" + "boy";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //2、构造一个url对象
                    URL url = new URL(serverAddress);
                    //3、通过url对象获得HttpUrlConnection对象
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //4、调用HttpURLConnection.connect()方法连接server
                    conn.connect();
                    //5、调用HttpURLConnection.respondCode()方法，根据返回码判断server返回是否正确
                    if(HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                        //6、调用HttpURLConnection.getInputStream()方法读取server返回的内容
                        InputStream is = conn.getInputStream();
                        //包装流：字节流->转换字符流（处理流）->缓冲字符流（处理流）
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        StringBuffer sb = new StringBuffer();
                        String readLine = "";
                        while((readLine=br.readLine())!=null){
                            sb.append(readLine);
                        }
                        //7、关闭流、调用HttpURLConnection.disconnect()方法断开连接
                        is.close();
                        br.close();
                        conn.disconnect();
                        Log.i(tag,"get:"+sb.toString());
                    }else{
                        Log.e(tag,"get:"+ "failed!");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void urlConnectionPost(View view){
        final String postAddress = mUrl + mMethod;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(postAddress);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setUseCaches(false);
                    //设置请求头
                    //设置编码集
                    conn.setRequestProperty("Accept-Charset", "UTF-8");
                    //设置content-type
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.connect();
                    //将需要发送的参数信息写给server
                    OutputStream os = conn.getOutputStream();
                    //包装流：数据输出流（字符处理流）
                    DataOutputStream dos = new DataOutputStream(os);
                    String content = "sex=" + "boy";
                    dos.writeBytes(content);
                    dos.flush();
                    os.flush();
                    dos.close();
                    os.close();

                    //获取服务器中的数据
                    if(HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                        InputStream is = conn.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        StringBuffer sb = new StringBuffer();
                        String readLine = "";
                        while ((readLine=br.readLine())!=null){
                            sb.append(readLine);
                        }
                        is.close();
                        br.close();
                        conn.disconnect();
                        Log.i(tag,"post:"+sb.toString());
                    }else{
                        Log.e(tag,"post:failed!");
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
