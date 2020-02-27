package com.pmkproject.ex59rssfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Item> items=new ArrayList<>();

    MyAdapter adapter;

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler);

        adapter=new MyAdapter(items,this);
        recyclerView.setAdapter(adapter);

        //리사이클러의 배치관리자 설정
        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        //대량의 데이터 추가작업
        readRss();

        //스와이프 갱신 기능
        refreshLayout=findViewById(R.id.layout_swipe);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Toast.makeText(MainActivity.this, "aa", Toast.LENGTH_SHORT).show();
                items.clear();
                adapter.notifyDataSetChanged();

                readRss();
            }
        });

    }//onCreate Method..


    //rss xml문서를 읽어와서 파싱하는 작업 메소드

    void readRss(){

        try {
            //한경닷컴의 경로 주소를 저장할 url
            URL url=new URL("http://rss.hankyung.com/new/news_main.xml");

            //스트림의 연결작업은 반드시 인터넷 퍼미션을 쓴다는것을 Manifest에 써줘야댐

            //스트림 연결하여 데이터 읽어오기
            //NewWork작업은 반드시 별도의 Thread만 할 수 있음
            //별도의 Thread 객체 생성
            RssFeedTask task=new RssFeedTask();
            task.execute(url); //doInBackground()메소드가 발동[Thread의 start()와 같은 역활]


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }//readRss Method..

    //이너클릭스
    //AsyncTast 첫번쨰 제네릭 : execute 메소드의 파마리터로 보낸 값의 참조형이다
    //AsyncTast 세번째 제네릭 : doInBackground 의 리턴값이 onPostExecute의 파라미터의 참조형이다
    class RssFeedTask extends AsyncTask<URL, Void, String>{

        //Thread의 run() 메소드와 같은 역활
        @Override
        //뒤에 ... 은 배열과 같은것 여러개를 전달받을수 있다
        protected String doInBackground(URL... urls) {
            //전달받은 URL 객체 이다
            URL url=urls[0];

            //해임달(URL)에게 무지개 로드(stream) 열기
            try {
                InputStream is=url.openStream();

                //읽어온 xml를 파싱(분석)해주는 객체 생성
                XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                XmlPullParser xpp=factory.newPullParser();

                //이 방식으로 해도됨
//                InputStreamReader reader=new InputStreamReader(is);
//                xpp.setInput(reader);

                //utf-8은 한글도 읽어오기 위한 인코딩 방식
                xpp.setInput(is,"utf-8");

                int eventType=xpp.getEventType();
                Item item=null;
                String tagName=null;

                while (eventType!=XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            tagName=xpp.getName();
                            if(tagName.equals("item")){
                                item=new Item();

                            }else if(tagName.equals("title")){
                                xpp.next();
                                if(item!=null) item.setTitle(xpp.getText());

                            }else if(tagName.equals("link")){
                                xpp.next();
                                if(item!=null) item.setLink(xpp.getText());

                            }else if(tagName.equals("description")){
                                xpp.next();
                                if(item!=null) item.setDesc(xpp.getText());

                            }else if(tagName.equals("image")){
                                xpp.next();
                                if(item!=null) item.setImgurl(xpp.getText());

                            }else if(tagName.equals("pubDate")){
                                xpp.next();
                                if(item!=null) item.setDate(xpp.getText());
                            }

                            break;
                        case XmlPullParser.TEXT:
                            break;
                        case XmlPullParser.END_TAG:
                            tagName=xpp.getName();
                            if(tagName.equals("item")){
                                //읽어온 기사 한개를 대량의 데이터에 추가작업
                                items.add(item);
                                item=null;

                                //리사이클러의 아답터에게 데이터가 변경되었다는것을 통지
                                //UI변경작업을 다른곳에서 하고싶다면..
                                publishProgress(); //onProgressUpdate() 라는 메소드 실행

                            }
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                    }

                    eventType=xpp.next();

                    //억지로 시간걸리도록.. (원래하면안됌)
                }//while


                //파싱작업이 완료되었을때
                //토스트 보이기.. 단, 별도 스레드는 UI작업이 불가
                //그래서 runOnUIThread()를 이용했었음
                //이 UI작업을 하는 별도의 메소드로 결과를 리턴하는 방식을 사용



            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return "파싱종료";
        }//doInBackground Method..


        //doInBackground() 작업 도중에
        //publishProgress()라는 메소드를
        //호출하면 자동으로 실행되는 메소드
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            //이 곳에서도 UI변경 작업이 가능함!!
            adapter.notifyItemInserted(items.size());
        }

        //doInBackground Method가 종료된 후
        //UI작업을 위해 자동 실행되는 메소드임
        //runOnUIThread()와 비슷한 역활
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //스와이프 아이콘 제거
            refreshLayout.setRefreshing(false);

            //리사이클러에서 보여주는 데이터를 가진
            //Adapter에게 데이터가 변경되었다고 공지

            //adapter.notifyDataSetChanged();


            //이 메소드 안에서는 UI변경작업 가능
            Toast.makeText(MainActivity.this, s+":"+items.size(), Toast.LENGTH_SHORT).show();

        }
    }//ResFeedTast Method..

}//MainActivity class..
