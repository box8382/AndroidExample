package com.pmkproject.ex24listviewcustom;

public class Member {
    int imgID;      //이미지(저장경로 id)
    String name;    //이름  (전현무)
    String nation;  //국적  (대한민국)

    //생성(new) 할때 자동으로 실행되는 메소드
    //생성자 메소드
    public Member(int imgID,String name,String nation){
        //멤버변수에 값 대입
        this.imgID=imgID;
        this.name=name;
        this.nation=nation;
    }
}
