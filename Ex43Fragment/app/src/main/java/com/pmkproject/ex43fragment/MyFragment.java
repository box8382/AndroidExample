package com.pmkproject.ex43fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    TextView tv;
    Button btn,btn2;

    //Fragment가 보여줄 View(화면)을 만들어 내는 작업을 하는 메소드
    //마치 Activity에 onCreate() 같은 역활..

    //화면에 보여질때 자동으로 실행되는 콜백 메소드
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //보여줄 뷰객체를 생성(부풀리다)
        //[xml의 위치를 알려주는게됨],[container 는 붙일 곳의 사이즈를 알고있음], [boolean 변수는 지금붙일건지 나중에 붙일건지 판단]
        View view=inflater.inflate(R.layout.fragment_my, container, false);

        tv=view.findViewById(R.id.tv);
        btn=view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText("bbb");
            }
        });

        btn2=view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity의 TextView의 글씨 변경

                //이 Fragment를 보여주는 Activity를 얻어오기
                MainActivity activity= (MainActivity) getActivity();
                activity.tv.setText("!!!!!!!");
            }
        });


        //생성된 View를 리턴해주면 Activity에 보여짐 [그후 그값을 리턴해주면 연결완료]
        return view;
    }
}
