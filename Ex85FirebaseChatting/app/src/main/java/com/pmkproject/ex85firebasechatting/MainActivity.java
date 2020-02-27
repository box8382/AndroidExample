package com.pmkproject.ex85firebasechatting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    CircleImageView ivProfile;

    Uri imgUri=null; //선택한 프로필 이미지 경로 Uri

    boolean isFirst=true; //앱을 처음 실행한것인가?
    boolean isChange=false; //프로필을 변경한 적이 있는가?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName=findViewById(R.id.et_name);
        ivProfile=findViewById(R.id.iv_profile);

        //폰에 저장되어있는 프로필 읽어오기
        loadData();
        if(G.nickname!=null){
            etName.setText(G.nickname);
            Picasso.get().load(G.profileUrl).into(ivProfile);

            //처음이 아니라 즉, 이미 접속한 적이있다
            isFirst=false;
        }

        //동적퍼미션 읽기 쓰기 권한(Glide쓰려면 특정폰에서는 동적퍼미션 받아야함 [단, picasso는 안받아도됨])
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
//            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
//                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},5);
//            }
//        }

    }

    public void clickImg(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    if(data!=null) {
                        imgUri=data.getData();
                        //Glide.with(this).load(imgUri).into(ivProfile);

                        //Glide는 이미지를 읽어와서 보여줄 때 내 device의 외장메모리에 접근하는 퍼미션이 요구됨.(퍼미션이없으면 이미지가 보이지 않음)
                        //Glide를 사용할 때는 동적 퍼미션 필요함.
                        //Picasso 라이브러리는 퍼미션 없어도 됨.
                        Picasso.get().load(imgUri).into(ivProfile);

                        //변경된 데이터가 있다
                        isChange=true;
                    }
                }
                break;
        }
    }

    public void clickBtn(View view) {
        //바꾼것도 없고, 처음접속도 아니라면 바로 채팅으로 전환 (즉 기존에 데이터를 그냥 쓰겠다는 뜻)
        if(!isChange && !isFirst){
            //ChatActivity로 전환
            Intent intent=new Intent(this, ChatActivity.class);
            startActivity(intent);
            finish();
        }else{
            //save 작업 (화면 전환 포함)
            saveData();
        }

    }

    void saveData(){
        //이미지를 선택하지 않았거나 닉네임을 작성하지 않았다면 못들어옴
        if(imgUri==null || etName.getText().toString().length()==0) return;

        //EditText의 닉네임 가져오기
        G.nickname=etName.getText().toString();

        //Firebase storage 에 이미지 저장 하기 위해 파일명 만들기(날짜를 기반으로)
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName=sdf.format(new Date())+".png";

        //Firebase storage에 저장하기
        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance();
        final StorageReference imgRef=firebaseStorage.getReference("profileImages/"+fileName);

        //파일 업로드
        UploadTask uploadTask = imgRef.putFile(imgUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //이미지 업로드가 성공되었으므로..
                //곧바로 Firebase storage의 이미지 파일 다운로드 URL을 얻어오기
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //파라미터로 firebase 저장소에 저장되어있는 이미지에 대한 다운로드 주소(URL)을 문자열로 얻어오기
                        G.profileUrl=uri.toString();
                        Toast.makeText(MainActivity.this, "프로필저장 완료", Toast.LENGTH_SHORT).show();
                        //1. Firebase Database에 nickName, profileUrl을 저장
                        //Firebase DB 관리자 객체 소환
                        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                        //'profiles' 라는 이름으로 자식노드 참조객체 얻어오기
                        DatabaseReference profileRef=firebaseDatabase.getReference("profiles");

                        //닉네임을 key식별자로 하고 프로필 이미지의 주소를 값으로 저장
                        DatabaseReference nickRef=profileRef.child(G.nickname);
                        nickRef.setValue(G.profileUrl);


                        //2. 내 phone에 nickName, profileUrl을 저장
                        SharedPreferences preferences=getSharedPreferences("account",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();

                        editor.putString("nickName",G.nickname);
                        editor.putString("profileUrl",G.profileUrl);

                        editor.commit();

                        //저장이 완료되었으니 ChatActivity로 전환
                        Intent intent=new Intent(MainActivity.this,ChatActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });


            }
        });


    }

    //내 폰에 저장되어있는
    //프로필 정보 읽어오기
    void loadData(){
        SharedPreferences preferences=getSharedPreferences("account",MODE_PRIVATE);
        G.nickname=preferences.getString("nickName",null);
        G.profileUrl=preferences.getString("profileUrl",null);
    }
}
