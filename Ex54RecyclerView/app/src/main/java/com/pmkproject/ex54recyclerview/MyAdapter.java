package com.pmkproject.ex54recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {

    ArrayList<String> datas;
    Context context;

    public MyAdapter(ArrayList<String> datas, Context context) {
        this.datas = datas;
        this.context=context;
    }

    //이 메소드가 실행된다면 재활용할 뷰가 없다는 뜻임
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //이 메소드안에서 리사이클러 뷰가 보여줄 뷰를 만들어 내서 ViewHolder를 리턴함(BaseAdapter 에 getView() 와 비슷함)

        LayoutInflater inflater=LayoutInflater.from(context);

        View itemView=inflater.inflate(R.layout.listview_item,parent,false);

        //itemView를 보관하는 ViewHolder 객체 생성
        VH holder=new VH(itemView);

        return holder;
    }

    //아이템 뷰의 데이터들 연결하는 메소드 : 항목마다 실행됨
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //아이템뷰를 보관하고 있는 VH객체를 다운 캐스팅
        VH vh=(VH)holder;

        String s=datas.get(position);
        vh.tv.setText(s);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //이너 클래스
    class VH extends RecyclerView.ViewHolder{

        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);

            tv=itemView.findViewById(R.id.tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //클릭된 아이템뷰가 리사이클러뷰에서
                    //몇번째 index(position)인지 알아내기
                    int position=getLayoutPosition();

                    Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }


}
