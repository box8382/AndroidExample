package com.pmkproject.ex44fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_my,container,false);

        //혹시 프레그먼트를 만들때 전달된 Argument 가 있다면?
        Bundle bundle=getArguments();

        String name=bundle.getString("Name","익명");
        int age=bundle.getInt("Age",0);


        Toast.makeText(getActivity(),name+", "+age,Toast.LENGTH_SHORT).show();


        return view;
    }
}
