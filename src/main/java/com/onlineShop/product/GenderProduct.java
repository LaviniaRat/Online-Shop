package com.onlineShop.product;

import java.util.List;

public class GenderProduct {
    private int id;
    private String title;
    private String gender;
    private int price;
    private String currency;
    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGender() {
        return gender;
    }

    public int getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
