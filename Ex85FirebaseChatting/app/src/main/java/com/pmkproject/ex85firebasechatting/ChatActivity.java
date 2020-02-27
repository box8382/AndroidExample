package com.pmkproject.ex85firebasechatting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    ListView listView;
    EditText et;

    ArrayList<MessageItem> messageItems=new ArrayList<>();
    ChatAdapter adapter;

    //FirebaseDatabase 관리 객체 참조변수
    FirebaseDatabase firebaseDatabase;
    //'chat' 노드에 참조객체 참조변수
    DatabaseReference chatRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //제목줄 제목글씨를 닉네임으로(또는 채팅방)
        getSupportActionBar().setTitle(G.nickname);

        listView=findViewById(R.id.listview);
        et=findViewById(R.id.et);
        adapter=new ChatAdapter(messageItems,getLayoutInflater());
        listView.setAdapter(adapter);

        //Firebase DB관리객체와 'chat' 노드 참조객체 얻어오기
        firebaseDatabase=FirebaseDatabase.getInstance();
        chatRef=firebaseDatabase.getReference("chat");

        //firebaseDB에서 채팅메세지들 실기간 읽어오기
        //'chat'노드에 저장되어 있는 데이터들을 읽어오기
        //chatRef에 데이터가 변경되는것을 듣는 리스터 추가!
        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //새로 추가된 데이터(값 : MessageItem 객체 ) 가져오기
                MessageItem messageItem=dataSnapshot.getValue(MessageItem.class);

                //새로운 메세지를 리스트뷰에 추가하기 위해 ArrayList에 추가
                messageItems.add(messageItem);

                //리스트뷰를 갱신
                adapter.notifyDataSetChanged();
                listView.setSelection(messageItems.size()-1); //리스트뷰에 마지막위치 총사이즈-1 해야 마지막으로 스크롤을 잡음
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void clickSend(View view) {

        //firstbase DB에 저장할 값들(닉네임, 메세지, 프로필이미지URL, 작성시간)
        String nickName=G.nickname;
        String message=et.getText().toString();
        String profileUrl=G.profileUrl;

        //메세지 작성 시간 문자열로..
        Calendar calendar=Calendar.getInstance(); //현재시간을 가지고 있는 객체
        String time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);

        //firebase DB 에 저장할 값(MessageItem객체) 설정
        MessageItem messageItem=new MessageItem(nickName,message,time,profileUrl);

        //'chat' 노드에 MessageItem 객체를 통째로 추가(push)
        chatRef.push().setValue(messageItem);

        //EditText에 있는 글씨 지우기
        et.setText("");

        //소프트키패드를 안보이도록..
        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        //처음시작할 때 EditText가 다른 뷰들보다
        //우선시되어 포커스를 받아버림
        //즉, 시작부터 소프트키패드가 올라와있음!

        //그게 싫으면 다른 뷰가 포커스를 가지도록 하면됨
        //즉, EditText를 감싼 Layout에게 포커스를 가지도록..! 속성을 추가!




    }
}
