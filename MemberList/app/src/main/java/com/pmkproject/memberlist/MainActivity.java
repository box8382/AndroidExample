package com.pmkproject.memberlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

//    SearchView searchView;

    ListView listView;
    ArrayList<Member> members=new ArrayList<Member>();
    MyAdapter adapter;
    int img_nation;
    int img_gender;

    AlertDialog dialog;

    EditText dialog_name;
    RadioGroup dialog_gender;
    Spinner dialog_nation;

    Button dialog_add_member;
    Button dialog_canel;

    TextView noimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noimg=findViewById(R.id.listview_noimg);

        listView=findViewById(R.id.listview);
        LayoutInflater inflater=getLayoutInflater();
        adapter=new MyAdapter(members,inflater);
        listView.setEmptyView(noimg);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("삭제 하시겠습니까?");

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        members.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("취소",null);
                dialog=builder.create();
                dialog.show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_plus, menu);

//        MenuItem item=menu.findItem(R.id.menu_search);
//        searchView=(SearchView)item.getActionView();
//
//        searchView.setQueryHint("이름 검색");
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if(query.equals())
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_search:
                //돋보기 눌렀을때 반응
                Toast.makeText(MainActivity.this,"검색",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_plus:
                //플러스 버튼 눌렀을때 반응
//                Toast.makeText(MainActivity.this,"플러스",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

                LayoutInflater inflater=getLayoutInflater();
                View v=inflater.inflate(R.layout.dialog,null);

                dialog_name=v.findViewById(R.id.dialog_name);               //이름
                dialog_gender=v.findViewById(R.id.dialog_gender);           //성별
                dialog_nation=v.findViewById(R.id.dialog_nation);           //국가
                dialog_add_member=v.findViewById(R.id.dialog_add_member);   //확인버튼
                dialog_canel=v.findViewById(R.id.dialog_cancel);            //취소버튼

                builder.setView(v);
                dialog=builder.create();
                dialog.show();


                dialog_add_member.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name=dialog_name.getText().toString();
                        if(name.equals("")){
                            Toast.makeText(MainActivity.this,"이름을 입력하세요",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String nation=dialog_nation.getSelectedItem().toString();

                        for(int i=0;i<13;i++){
                            if(dialog_nation.getSelectedItemPosition()==i){
                                img_nation=R.drawable.flag_australia+i;
                            }
                        }

                        switch (dialog_gender.getCheckedRadioButtonId()) {
                            case R.id.Male:
                                img_gender = R.drawable.hippo;
                                break;
                            case R.id.Female:
                                img_gender = R.drawable.pig;
                                break;

                        }
                        //현재 시간을 밀리세컨드 단위로 구함
                        long now=System.currentTimeMillis();
                        //현재시간을 date 변수에 저장
                        Date date=new Date(now);
                        //시간을 나타낼 포맷을 정함
                        SimpleDateFormat sdfNow=new SimpleDateFormat("MM/dd  HH:mm:ss");
                        //nowDate 변수에 값을 저장



                        members.add(0,new Member(name,nation,img_nation,img_gender,sdfNow.format(date)));
                        adapter.notifyDataSetChanged();
                        dialog.cancel();

                    }
                });



                dialog_canel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
