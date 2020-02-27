package com.pmkproject.ex45fragmentpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Page3Fragment extends Fragment {

    ListView listView;
    ArrayList<String> datas=new ArrayList<>();
    Page3ListAdapter adapter;


    //여기는 부셔져도 다시 실행됬을때도 무조건1번만 실행하기 때문에 데이터를 관리하기에 적합하다고 생각
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //대량의 데이터 추가하기
        datas.add("aaa");
        datas.add("bbb");
        datas.add("ccc");
    }


    //뷰가 부셔졌다가 여기서부터 다시시작함 그래서 데이터를 여기서 관리하게되면 중첩으로 계속더해짐
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_page3,container,false);
    }


    //위의 onCreateView가 실행된 후에 자동 실행되는 메소드입니당
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView=view.findViewById(R.id.listview);
        adapter=new Page3ListAdapter(datas,getActivity());
        listView.setAdapter(adapter);

        //리스트뷰의 아이템을 클릭하면...
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getActivity(), datas.get(position), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
