package com.pmkproject.ex73alarmmanagertest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //알람관리자 소환
        alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);




    }

    public void clickBtn(View view) {
        //10초 후에 알람 설정..(10초 후에 AlarmActivity를 실행!)

        //먼저 알람에 설정한 PendingIntent생성
        Intent intent=new Intent(this, AlarmActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,10,intent,PendingIntent.FLAG_UPDATE_CURRENT);


        //알람매니저에게 알람 설정

        //Marshmallow 버전부터 Doz(낮잠)모드가 생김
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(1000*10),pendingIntent);
        }else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(1000*10),pendingIntent);
        }

    }

    public void clickBtn2(View view) {
        //반복알람 : 10초후에 처음알람, 20초마다 반복알람

        //20초마다 발동할 Broadcast Receiver를 PendingIntent로 생성
        Intent intent=new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,20,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //애석하게 KitKet이후 버전부터는 Repeat 기능이 없음
        //그래서 반복알람을 하려면 행운의 편지 기법을 도입할 겁니다..? (뭔소리여)
        //첫 알람 설정(10초 후에)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(1000*10),pendingIntent);
        }else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(1000*10),pendingIntent);
        }


    }

    public void clickBtn3(View view) {
        //알람 종료

        //알람매니저에 보류되어 있는 PendingIntent를 cancel하면 됨
        Intent intent=new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,20,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);

    }

    public void clickBtn4(View view) {

        //특정 날짜에 알람 설정하기

        //날짜선택 다이얼로그 보이기
        GregorianCalendar calendar=new GregorianCalendar(Locale.KOREA);
        DatePickerDialog dialog=new DatePickerDialog(this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    //멤버변수
    int Year,Month,Day;
    int Hour,Min;

    //날짜선택 리스너
    DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            //Toast.makeText(MainActivity.this, year+", "+(month+1)+", "+day, Toast.LENGTH_SHORT).show();
            //선택한 날짜 저장
            Year=year;
            Month=month;
            Day=day;

            //시간 선택 다이얼로그 보이기
            GregorianCalendar calendar=new GregorianCalendar(Locale.KOREA);
            TimePickerDialog dialog=new TimePickerDialog(MainActivity.this,timeSetListener,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
            dialog.show();
        }
    };

    //시간 선택 리스너
    TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            Toast.makeText(MainActivity.this, hour+", "+minute, Toast.LENGTH_SHORT).show();
            Hour=hour;
            Min=minute;

            //선택한 날짜와 시간으로 알람 설정
            GregorianCalendar calendar=new GregorianCalendar(Year,Month,Day,Hour,Min);

            //알람시간에 AlarmActivity 실행되도록
            Intent intent=new Intent(MainActivity.this,AlarmActivity.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,50,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            }else{
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            }
        }
    };



}
