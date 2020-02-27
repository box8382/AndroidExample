package com.pmkproject.ex14dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //다이얼로그의 항목으로 보여질 글씨들
    String[] items={"Apple","Banana","Orange"};
    boolean[] checkedItems=new boolean[]{true,false,true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {

        //AlertDialog 보이기

        //AlertDialog 를 만들어주는 건축가(Builder)객체 생성
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        //건축가에게 원하는 작업 요청
        builder.setTitle("다이얼로그");
        builder.setIcon(android.R.drawable.ic_dialog_map);
//        builder.setMessage("프로그램을 종료하시겠습니까?");

        //메세지 영역의 다른 형태 사용해보기
        //1) List(항목)형태 메세지 영역 설정
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//
//                //두번째 파라미터 which : 항목의 인덱스번호 가장 위 항목이 0번임
//                Toast t=Toast.makeText(MainActivity.this,items[which],Toast.LENGTH_SHORT);
//                t.show();
//
//            }
//        });
        //2) 라디오 버튼이 있는 항목형태의 설정
//        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                Toast t=Toast.makeText(MainActivity.this,items[which],Toast.LENGTH_SHORT);
//                t.show();
//            }
//        });

        //3) 체크박스버튼이 있는 항목형태의 설정
//        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which, boolean checked) {
//                checkedItems[which]=checked;
//            }
//        });

        //커스텀뷰로 메세지 영역 설정하기
        //API 21버전 이상에서만 가능한 코드
        //builder.setView(R.layout.dialog);

        //layout 폴더안에 있는 dialog.xml 문서를
        //Java 의 View 객체로 만들어(inflate:부풀리다)주는 LinearLayoutInflater 객체를 Context 로 부터 얻어오기
        LayoutInflater inflater=getLayoutInflater();
        View v=inflater.inflate(R.layout.dialog,null);
        builder.setView(v);

        //만들어진 뷰 안에 있는 EditText, TextView 찾아오기
        et=v.findViewById(R.id.et);
        tv=v.findViewById(R.id.tv);

        //positive 와 negative 의 차이는 놓여지는 순서만 다른것
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast 보이기
                Toast t=Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT);
                t.show();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast t=Toast.makeText(MainActivity.this,"CANCEL",Toast.LENGTH_SHORT);
                t.show();
            }
        });
        //건축가(Builder) 에게 AlertDialog 만들어 달라고 요청
        //builder 에 .show()를 해도 똑같이 보이는데 아래처럼 하는이유를 모르겠음
        AlertDialog dialog=builder.create();

        //다이얼로그의 바깥쪽을 터치하였을 때 다이얼로그가 꺼지지 않도록..
        dialog.setCanceledOnTouchOutside(false);

        //뒤로가기 버튼을 클릭해도 꺼지지 않도록 하려면..
        dialog.setCancelable(false);

        //다이얼로그를 화면에 보이기!!
        dialog.show();

    }

    //멤버변수
    EditText et;
    TextView tv;

    public void clickDialogBtn(View view) {
        String s=et.getText().toString();
        tv.setText(s);
    }
}
