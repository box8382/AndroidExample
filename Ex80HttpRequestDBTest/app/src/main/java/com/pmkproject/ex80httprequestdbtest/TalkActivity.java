package com.pmkproject.ex80httprequestdbtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TalkActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<TalkItem> datas = new ArrayList<>();
    TalkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);

        //데이터를 서버에서 불러오는 작업
        loadDB();

        listView = findViewById(R.id.listview);
        adapter = new TalkAdapter(getLayoutInflater(), datas);
        listView.setAdapter(adapter);

    }

    void loadDB() {

        //Volley library 사용 가능
        //이 예제에서는 전통적인 기법으로..
        new Thread() {
            @Override
            public void run() {
                String serverUri = "http://boxoun.dothome.co.kr/Android/loadDB.php";

                try {
                    URL url = new URL(serverUri);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    final StringBuffer buffer = new StringBuffer();
                    String line = reader.readLine();
                    while (line != null) {
                        buffer.append(line + "\n");
                        line = reader.readLine();
                    }

//                    //읽어오는 작업이 성공했는지 확인 작업
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            new AlertDialog.Builder(TalkActivity.this).setMessage(buffer.toString()).create().show();
//                        }
//                    });
                    //대량의 데이터 초기화
                    datas.clear();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });

                    //읽어온 데이터 문자열에서 row(레코드) 별로 분리하여 배열로 반환
                    String[] rows = buffer.toString().split(";");

                    for (String row : rows) {
                        //한줄 데이터 에서 한 칸씩 분리
                        String[] item = row.split("&");
                        if (item.length != 5) continue;

                        int no = Integer.parseInt(item[0]);
                        String name = item[1];
                        String msg = item[2];
                        String imgPath = "http://boxoun.dothome.co.kr/Android" + item[3];
                        String date = item[4];

                        //대량의 데이터 ArrayList에 추가
                        datas.add(new TalkItem(no, name, msg, imgPath, date));

                        //리스트뷰 갱신
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();
    }
}
