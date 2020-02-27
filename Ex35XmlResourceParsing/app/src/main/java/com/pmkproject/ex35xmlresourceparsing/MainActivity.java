package com.pmkproject.ex35xmlresourceparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
    }

    public void clickBtn(View view) {
        //Resources폴더 안에 있는 Xml문서를 읽어와서 분석(parse)하는 작업 수행

        //res폴더 관리자 객체 소환
        Resources res=this.getResources();

        //창고관리자로부터 parser객체(분석가) 얻어오기
        XmlResourceParser xrp=res.getXml(R.xml.movies);

        StringBuffer buffer=new StringBuffer();

//        buffer.append("aaa");
//        buffer.append("bbb");
//        buffer.append("ccc");

//        String s=buffer.toString();
        //위와같이하면 덫붙이는게 똑같지만 마지막에만 String을 만들기때문에 메모리관리가 효율적이다

        //Xml parser에게 xml문서를 분석하도록 코딩

        try {
            xrp.next();
            int eventType=xrp.getEventType();

            String tagName; //태그이름
            String text;    //내용글씨

            while(eventType!=XmlResourceParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlResourceParser.START_DOCUMENT:
                        buffer.append("xml 파싱 시작합니다..\n\n");
                        break;
                    case XmlResourceParser.START_TAG:
                        tagName=xrp.getName(); //태그문의 이름을 얻어오기
                        if(tagName.equals("no")) buffer.append("번호 : ");
                        else if(tagName.equals("title")) buffer.append("제목 : ");
                        else if(tagName.equals("genre")) buffer.append("장르 : ");
                        break;
                    case XmlResourceParser.TEXT:
                        text=xrp.getText();
                        buffer.append(text);
                        break;
                    case XmlResourceParser.END_TAG:
                        tagName=xrp.getName();
                        if(tagName.equals("no")) buffer.append("\n");
                        else if(tagName.equals("title")) buffer.append("\n");
                        else if(tagName.equals("genre")) buffer.append("\n");
                        else if(tagName.equals("item")) buffer.append("\n");
                        break;
                    case XmlResourceParser.END_DOCUMENT:
                        break;

                }//switch

                eventType=xrp.next();
            }

            buffer.append("파싱 종료....");

            tv.setText(buffer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }
}
