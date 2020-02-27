package com.pmkproject.ex55recyclerview2;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends RecyclerView.Adapter {

    ArrayList<Item> datas;
    Context context;

    public MyAdapter(ArrayList<Item> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.recycler_item,parent,false);

        VH holder=new VH(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //방법1.
//        VH vh=(VH)holder;
//        vh.cirIcon.setImageResource(datas.get(position).icon);
//        vh.tvName.setText(datas.get(position).name);
//        vh.tvMsg.setText(datas.get(position).msg);
//        vh.ivImg.setImageResource(datas.get(position).img);


        //방법2.
        VH vh=(VH)holder;
        Item item=datas.get(position);
        vh.tvName.setText(item.name);
        vh.tvMsg.setText(item.msg);
        //이미지가 너무크면 OOM(Out of memory)에러 발생
        //이를 방지하기 위해 Library(Picasso, Glide) 사용
        Glide.with(context).load(item.icon).into(vh.cirIcon);
        Glide.with(context).load(item.img).into(vh.ivImg);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    //이너 클래스 : 아이템뷰를 보관하는 클래스
    class VH extends RecyclerView.ViewHolder{

        CircleImageView cirIcon;
        TextView tvName;
        TextView tvMsg;
        ImageView ivImg;

        public VH(@NonNull View itemView) {
            super(itemView);

            cirIcon=itemView.findViewById(R.id.iv_icon);
            tvName=itemView.findViewById(R.id.tv_name);
            tvMsg=itemView.findViewById(R.id.tv_msg);
            ivImg=itemView.findViewById(R.id.iv_img);

            //아이템뷰 클릭 리스너
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getLayoutPosition();
                    //Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();

                    //상세화면에 넘겨줄 데이터들
                    String name=datas.get(position).name;
                    int imgId=datas.get(position).img;

                    //아이템의 상세 화면(DetailActivity)로 전환
                    Intent intent=new Intent(context,DetailActivity.class);
                    intent.putExtra("Name",name);
                    intent.putExtra("Img",imgId);

                    //액티비티 전환시 효과주기 (API21 버전 이상에서만 가능)
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                        ActivityOptions opts=ActivityOptions.makeSceneTransitionAnimation((MainActivity)context,new Pair<View, String>(cirIcon,"IMG"));

                        context.startActivity(intent,opts.toBundle());
                    }else{
                        context.startActivity(intent);
                    }

                }
            });
        }
    }

}
