package com.pmkproject.ex19actionviewmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText actionViewEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar,menu);

        //ActionView 안에 있는 EditText 찾아오기
        final MenuItem menuItem=menu.findItem(R.id.menu_action);
        View v=menuItem.getActionView();
        actionViewEditText=v.findViewById(R.id.actionview_et);

        //EditText 의 소프트키보드에 있는 돋보기 버튼 (:imeOptions="actionSearch")
        //을 클릭하는 것을 듣는 리스너 버튼 추가
        actionViewEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                if(actionID==EditorInfo.IME_ACTION_SEARCH){
                    Toast.makeText(MainActivity.this,actionViewEditText.getText().toString(),Toast.LENGTH_SHORT).show();

                    menuItem.collapseActionView();

                    return true;
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void clickBtn(View view) {
        //기존 액션바를 덮어버리는 액션모드

        //액션모드를 시작하기 : 액션바위치에 메뉴가 보여짐
        startActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater().inflate(R.menu.actionmode,menu);

                //액션모드에 추가로 줄 수 있는 설정들
                actionMode.setTitle("Actionmode");
                actionMode.setSubtitle("this is action mode");

                return true; //true 가 아니면 액션모드가 발동하지 않음.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_aa:
                        Toast.makeText(MainActivity.this,"aa",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_bb:
                        Toast.makeText(MainActivity.this,"bb",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_cc:
                        Toast.makeText(MainActivity.this,"cc",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {

            }
        });

    }
}
