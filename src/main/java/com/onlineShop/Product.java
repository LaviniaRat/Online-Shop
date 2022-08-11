package com.onlineShop;

import java.util.List;

public class Product {
    private int id;
    private String title;
    private String description;
    private List<String>images;
    private int price;
    private String currency;
    private String gender;
    private int categoryId;



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public int getPrice() {
        return price;
    }

    public String getGender() {
        return gender;
    }

    public String getCurrency() {
        return currency;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
