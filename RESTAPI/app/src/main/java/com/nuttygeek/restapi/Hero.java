package com.nuttygeek.restapi;

public class Hero {

    String name;
    String imageUrl;

   public  Hero(String name, String url){
        this.name = name;
        this.imageUrl = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullString(){
       return this.name + "\n" + this.imageUrl;
    }
}
