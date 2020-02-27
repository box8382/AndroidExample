package com.pmkproject.ex82jsonhttprequestdbtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Item> items=new ArrayList<>();
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler);
        adapter=new ItemAdapter(items,this);
        recyclerView.setAdapter(adapter);

        //리사이클러뷰의 레이아웃 매니저 설정
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void clickLoad(View view) {

        //서버의 loadDBtoJson.php파일에 접속하여 (DB데이터들) 결과받기
        //Volley+ 라이브러리 사용

        //결과를 JsonArray받을 것이므로..
        //StringRequest가 아니라 ...
        //JsonArrayRequest 를 이용할 것임

        String serverUrl="http://boxoun.dothome.co.kr/Android/loadDBtoJson.php";
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                //파라미터로 응답받은 결과 JsonArray 를 분석

                items.clear();
                adapter.notifyDataSetChanged();

                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject=response.getJSONObject(i);
                        int no=Integer.parseInt(jsonObject.getString("no"));
                        String name=jsonObject.getString("name");
                        String msg=jsonObject.getString("message");
                        String imgPath=jsonObject.getString("imgPath");
                        String date=jsonObject.getString("date");

                        //이미지 경로의 경우 서버IP가 제외된 주소(upload/xxxxx.jpg) 바로 사용 불가
                        imgPath="http://boxoun.dothome.co.kr/Android/"+imgPath;

                        items.add(0,new Item(no,name,msg,imgPath,date));

                        adapter.notifyItemInserted(0);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "에러발생!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
