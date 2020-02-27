package com.pmkproject.ex78httprequesttest;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText etName,etMsg;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.et_name);
        etMsg=findViewById(R.id.et_msg);
        tv=findViewById(R.id.tv);
    }

    //Get 방식 데이터 전송 (단점 : 데이터가 노출됨(보안에 취약함), 장점 : 속도가 빠름 )
    public void clickGet(View view) {
        //서버에 name , message 를 전송하도록
        //(서버를 쓴다는건 인터넷작업을 해야하기 때문에 Manifest에 인터넷퍼미션을 받아야함)
        //네트워크 작업은 반드시 별도의 Thread
        new Thread(){
            @Override
            public void run() {
                String name=etName.getText().toString();
                String msg=etMsg.getText().toString();

                //Get 방식의 데이터를 보낼 서버주소
                String serverUrl="http://boxoun.dothome.co.kr/Android/getTest.php";

                //Get방식은 보낼데이터(name, msg)를 URL뒤에 붙여서 보내는 방식
                //한글은 인터넷 URL에 사용 불가!! 그래서 한글을 utf-8 인코딩
                try {
                    name= URLEncoder.encode(name,"utf-8");
                    msg=URLEncoder.encode(msg,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String getUrl=serverUrl+"?name="+name+"&msg="+msg;
                //서버와 연결작업 시작!
                try {
                    URL url=new URL(getUrl);
                    //URL객체는 데이터를 읽어들이기 위한 InputSteam을 여는 것만 가능하고 데이터를 서버로 보낼수는 없음
                    //즉, OutputStream을 열수는 없음.

                    //HTTP 통신 규약에 따라 데이터를 주고 받는 역활을 수행하는 객체를 새롭게 얻어오기!
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");//반드시 대문자로 써야됨!!
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false); //캐쉬 저장할건지 여부 : false (구글권장 [성능저하])

                    //보낼 데이터가 있다면 여기서 OutputStream을 통해 전송
                    //Get방식으로 이미 URL에 데이터를 붙여서 보냈기에 필요가 없는 작업

                    //getTest.php로부터 echo된 결과Data를 읽어와서 보여주기
                    InputStream is=connection.getInputStream(); //스트림 연결 근데 바이트단위로 줌
                    InputStreamReader isr=new InputStreamReader(is); //바이트 단위로 주는것을 문자단위로 바꿔줌
                    BufferedReader reader=new BufferedReader(isr); //문자 단위를 한줄 단위로 바꾸게 하기위함?()

                    final StringBuffer buffer=new StringBuffer();
                    String line=reader.readLine();
                    while(line!=null){
                        buffer.append(line+"\n");
                        line=reader.readLine();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(buffer.toString());
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    //Post 방식 데이터 전송 (장점 : 데이터가 노출되지않음(보안이 강화됨), 단점 : Get방식보다 속도가 느림)
    public void clickPost(View view) {
        new  Thread(){
            @Override
            public void run() {
                String name=etName.getText().toString();
                String msg=etMsg.getText().toString();

                String serverUrl="http://boxoun.dothome.co.kr/Android/postTest.php";
                try {
                    URL url=new URL(serverUrl);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);

                    //보낼 데이터
                    String query="name="+name+"&msg="+msg;
                    OutputStream os=connection.getOutputStream();
                    OutputStreamWriter writer=new OutputStreamWriter(os);

                    writer.write(query,0,query.length());
                    writer.flush();
                    writer.close();

                    //postTest.php로 부터 echo결과 받기
                    InputStream is=connection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);
                    BufferedReader reader=new BufferedReader(isr);

                    final StringBuffer buffer=new StringBuffer();
                    String line=reader.readLine();
                    while (line!=null){
                        buffer.append(line+"\n");
                        line=reader.readLine();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(buffer.toString());
                        }
                    });



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
