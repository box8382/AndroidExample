package com.pmkproject.memberlist;

public class Member {
    String name;
    String nation;
    int img_nation;
    int img_gender;
    String clock;

    public Member(String name,String nation,int img_nation,int img_gender,String clock){
        this.name=name;
        this.nation=nation;
        this.img_nation=img_nation;
        this.img_gender=img_gender;
        this.clock=clock;
    }
}
