package com.pmkproject.ex59rssfeed;

public class Item {

    String title;
    String link;
    String desc;
    String imgurl;
    String date;

    public Item() {
    }

    public Item(String title, String link, String desc, String imgurl, String date) {
        this.title = title;
        this.link = link;
        this.desc = desc;
        this.imgurl = imgurl;
        this.date = date;
    }


    //Getter & Setter Method...

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
