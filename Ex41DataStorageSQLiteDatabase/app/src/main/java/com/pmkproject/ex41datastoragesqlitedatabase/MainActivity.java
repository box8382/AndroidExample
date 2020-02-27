package com.pmkproject.ex41datastoragesqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etEmail;

    String dbName="Data.db";        //database 파일명
    String tableName="member";      //표 이름

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName=findViewById(R.id.et_name);
        etAge=findViewById(R.id.et_age);
        etEmail=findViewById(R.id.et_email);

        //dbName 으로 데이터베이스 파일 생성 또는 열기
        db =this.openOrCreateDatabase(dbName,MODE_PRIVATE,null);

        //만들어진 DB파일에 "member" 라는 이름으로 테이블 생성 작업 수행
        db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+"(num integer primary key autoincrement, name text, age integer, email text);");

    }

    public void clickInsert(View view) {

        String name=etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String email=etEmail.getText().toString();

        //데이터 베이스에 데이터를 삽입하는(insert) 하는 쿼리문 실행
        db.execSQL("INSERT INTO "+tableName+"(name, age, email) VALUES('"+name+"','"+age+"','"+email+"')");

        etName.setText("");
        etAge.setText("");
        etEmail.setText("");
    }

    public void clickSelectAll(View view) {
//        db.execSQL("select * from "+tableName);
        Cursor cursor=db.rawQuery("select * from "+tableName, null); //두번쨰 파라미터 : 검색조건
        //Cursor 객체 : 결과 table 을 참조하는 객체
        //Cursor 객체를 통해 결과 table의 값들을
        //읽어오는 것임
        if(cursor==null) return;

        StringBuffer buffer=new StringBuffer();

        while(cursor.moveToNext()){
            int num=cursor.getInt(0);
            String name=cursor.getString(1);
            int age=cursor.getInt(2);
            String email=cursor.getString(3);

            buffer.append(num+",  "+name+",  "+age+",  "+email+"\n");
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(buffer.toString()).setPositiveButton("OK",null).create().show();
    }

    public void clickSelectByName(View view) {
        String name=etName.getText().toString();

        Cursor cursor=db.rawQuery("select name, age from "+tableName+" where name=?", new String[]{name});
        if(cursor==null) return;

        //참고 : 총 레코드 수 (줄, 행(row) 수)
        int rowNum=cursor.getCount(); //데이터의 행 수

        StringBuffer buffer=new StringBuffer();

        while(cursor.moveToNext()){
            String na=cursor.getString(1);
            int age=cursor.getInt(2);

            buffer.append(na+"  "+age+"\n");
        }

        Toast.makeText(this, buffer.toString(), Toast.LENGTH_SHORT).show();
    }

    public void clickUpdate(View view) {
        String name=etName.getText().toString();
        db.execSQL("update "+tableName+" set age=30, email='ss@ss.com' where name=?", new String[]{name});
    }

    public void clickDelete(View view) {
        String name= etName.getText().toString();
        db.execSQL("delete from "+tableName+" where name=?",new String[]{name});
    }
}
