package com.example.efraimsproject;

import android.graphics.Bitmap;

public class Products {
    private long id;
    private String name;
    private Bitmap pic;
    private String description;
    private int price;


    public Products(long id , String name, Bitmap pic, String description, int price) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.description = description;
        this.price = price;
    }

    public Products(String name, Bitmap pic, String description, int price) {
        this.description = description;
        this.pic = pic;
        this.name = name;
        this.price = price;
    }

    public Products(){
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

